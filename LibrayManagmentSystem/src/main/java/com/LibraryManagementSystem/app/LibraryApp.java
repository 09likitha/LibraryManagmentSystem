package com.LibraryManagementSystem.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.LibraryManagementSystem.model.Book;
import com.LibraryManagementSystem.model.Member;
import com.LibraryManagementSystem.storage.LibraryDataStore;
import com.LibraryManagementSystem.service.BookService;
import com.LibraryManagementSystem.service.LibraryService;
import com.LibraryManagementSystem.concurrency.BorrowBookTask;
import com.LibraryManagementSystem.concurrency.ReturnBookTask;

public class LibraryApp {

    public static void main(String[] args) throws Exception {

        LibraryDataStore dataStore = new LibraryDataStore();

        BookService bookService = new BookService(dataStore);
        LibraryService libraryService = new LibraryService(dataStore);

        // Add books and members
        Book book1 = new Book(1, "Java Programming");

        bookService.addBook(book1);
        libraryService.addMember(new Member(101, "Likitha"));
        libraryService.addMember(new Member(102, "Priya"));

        System.out.println("Library Management System Started!");

        // Thread and Runnable example
        Runnable displayBooks = () -> {
            System.out.println("\nAvailable Books:");

            for (Book book : bookService.getAllBooks()) {
                System.out.println(book);
            }
        };

        Thread displayThread = new Thread(displayBooks);
        displayThread.start();
        displayThread.join();

        // ExecutorService and Callable example
        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        try {
            System.out.println("\nConcurrent Borrowing:");

            Future<Boolean> firstBorrow =
                    executor.submit(
                            new BorrowBookTask(libraryService, 1, 101));

            Future<Boolean> secondBorrow =
                    executor.submit(
                            new BorrowBookTask(libraryService, 1, 102));

            boolean firstSuccess = firstBorrow.get();
            boolean secondSuccess = secondBorrow.get();

            System.out.println("\nBorrowing Results:");
            System.out.println("Member 101: " + firstSuccess);
            System.out.println("Member 102: " + secondSuccess);

            // Return the book to the member who borrowed it
            if (firstSuccess) {
                Future<Boolean> returnResult =
                        executor.submit(
                                new ReturnBookTask(libraryService, 1, 101));

                System.out.println(
                        "Return result: " + returnResult.get());

            } else if (secondSuccess) {
                Future<Boolean> returnResult =
                        executor.submit(
                                new ReturnBookTask(libraryService, 1, 102));

                System.out.println(
                        "Return result: " + returnResult.get());
            }

            System.out.println("\nFinal Book Status:");

            for (Book book : bookService.getAllBooks()) {
                System.out.println(book);
            }

            System.out.println("\nCurrent Loans:");

            for (var loan : libraryService.getAllLoans()) {
                System.out.println(loan);
            }

        } finally {
            executor.shutdown();
        }

        System.out.println("\nLibrary Application Finished!");
    }
}
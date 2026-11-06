package com.LibraryManagementSystem.concurrency;

import java.util.concurrent.Callable;

import com.LibraryManagementSystem.service.LibraryService;

public class BorrowBookTask implements Callable<Boolean> {

    private final LibraryService libraryService;
    private final int bookId;
    private final int memberId;

    public BorrowBookTask(
            LibraryService libraryService,
            int bookId,
            int memberId) {

        this.libraryService = libraryService;
        this.bookId = bookId;
        this.memberId = memberId;
    }

    @Override
    public Boolean call() {
        boolean success =
                libraryService.borrowBook(bookId, memberId);

        System.out.println(
                Thread.currentThread().getName()
                + " - Borrow book " + bookId
                + ": " + (success ? "Successful" : "Failed"));

        return success;
    }
}

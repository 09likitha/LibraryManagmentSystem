package com.LibraryManagementSystem.concurrency;

import java.util.concurrent.Callable;

import com.LibraryManagementSystem.service.LibraryService;

public class ReturnBookTask implements Callable<Boolean> {

    private final LibraryService libraryService;
    private final int bookId;
    private final int memberId;

    public ReturnBookTask(
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
                libraryService.returnBook(bookId, memberId);

        System.out.println(
                Thread.currentThread().getName()
                + " - Return book " + bookId
                + ": " + (success ? "Successful" : "Failed"));

        return success;
    }
}
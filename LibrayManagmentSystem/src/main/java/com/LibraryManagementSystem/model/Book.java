package com.LibraryManagementSystem.model;

public class Book {
    private final int bookId;
    private final String title;
    private boolean available = true;

    public Book(int bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public synchronized boolean isAvailable() {
        return available;
    }

    public synchronized boolean borrow() {
        if (!available) {
            return false;
        }

        available = false;
        return true;
    }

    public synchronized void returnBook() {
        available = true;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId
                + ", Title: " + title
                + ", Available: " + isAvailable();
    }
}
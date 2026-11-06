package com.LibraryManagementSystem.service;

import java.util.List;

import com.LibraryManagementSystem.model.Book;
import com.LibraryManagementSystem.storage.LibraryDataStore;

public class BookService {

    private final LibraryDataStore dataStore;

    public BookService(LibraryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addBook(Book book) {
        dataStore.addBook(book);
    }

    public Book findBook(int bookId) {
        return dataStore.findBook(bookId);
    }

    public List<Book> getAllBooks() {
        return dataStore.getBooks();
    }
}



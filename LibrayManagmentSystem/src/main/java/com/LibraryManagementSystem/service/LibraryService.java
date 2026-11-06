package com.LibraryManagementSystem.service;

import java.util.List;

import com.LibraryManagementSystem.model.Book;
import com.LibraryManagementSystem.model.Member;
import com.LibraryManagementSystem.model.Loan;
import com.LibraryManagementSystem.storage.LibraryDataStore;

public class LibraryService {

    private final LibraryDataStore dataStore;

    public LibraryService(LibraryDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addMember(Member member) {
        dataStore.addMember(member);
    }

    public boolean borrowBook(int bookId, int memberId) {
        Book book = dataStore.findBook(bookId);
        Member member = dataStore.findMember(memberId);

        if (book == null || member == null) {
            return false;
        }

        return dataStore.borrowBook(book, member);
    }

    public boolean returnBook(int bookId, int memberId) {
        return dataStore.returnBook(bookId, memberId);
    }

    public List<Loan> getAllLoans() {
        return dataStore.getLoans();
    }
}

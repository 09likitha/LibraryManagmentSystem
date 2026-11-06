
package com.LibraryManagementSystem.storage;

import java.util.ArrayList;
import java.util.List;

import com.LibraryManagementSystem.model.Book;
import com.LibraryManagementSystem.model.Member;
import com.LibraryManagementSystem.model.Loan;

public class LibraryDataStore {

    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();

    public synchronized void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
    }

    public synchronized void addMember(Member member) {
        if (member != null) {
            members.add(member);
        }
    }

    public synchronized Book findBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public synchronized Member findMember(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public synchronized boolean borrowBook(Book book, Member member) {
        if (book == null || member == null) {
            return false;
        }

        if (!books.contains(book) || !members.contains(member)) {
            return false;
        }

        if (!book.borrow()) {
            return false;
        }

        loans.add(new Loan(book, member));
        return true;
    }

    public synchronized boolean returnBook(int bookId, int memberId) {
        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);

            if (loan.getBook().getBookId() == bookId
                    && loan.getMember().getMemberId() == memberId) {

                loan.getBook().returnBook();
                loans.remove(i);
                return true;
            }
        }

        return false;
    }

    public synchronized List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public synchronized List<Loan> getLoans() {
        return new ArrayList<>(loans);
    }
}

package com.LibraryManagementSystem.model;

public class Loan {
	private Book book;
    private Member member;

    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }
}




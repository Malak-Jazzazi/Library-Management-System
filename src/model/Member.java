package src.model;

import src.exception.BookAlreadyBorrowedException;
import src.exception.BookNotBorrowedException;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) throws BookAlreadyBorrowedException {
        if(book.getIsBorrowed()){
            throw new BookAlreadyBorrowedException("Book " + book.getTitle() + " is already Borrowed");
        }
        book.borrow();
        borrowedBooks.add(book);
        System.out.println("Member " + name + " has Borrowed Book " + book.getTitle() + " Successfully");
    }

    public void returnBook(Book book) throws BookNotBorrowedException {
        if(!book.getIsBorrowed()){
            throw new BookNotBorrowedException("Book is not borrowed");
        }
        book.returnBook();
        borrowedBooks.remove(book);
        System.out.println("Member " + name + " has UnBorrowed Book " + book.getTitle() + " Successfully");
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}

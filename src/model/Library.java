package src.model;

import src.exception.BookAlreadyBorrowedException;
import src.exception.BookNotBorrowedException;
import src.exception.BookNotFoundException;
import src.exception.MemberNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {

    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addBook(Book book){
        books.add(book);
        System.out.println("Book " + book.getTitle() + " Was added to the library Successfully");
    }

    public void removeBook(Book book) throws BookNotFoundException {
        boolean isRemoved = false;
        if(!book.getIsBorrowed()){
            isRemoved = books.remove(book);
        }else{
            System.out.println("Book " + book.getTitle() +" Can't be removed because it is already borrowed");
        }
        if(!isRemoved && !book.getIsBorrowed()){
            throw new BookNotFoundException("Book " + book.getTitle() + " is not found");
        }
    }

    public void registerMember(Member member){
        members.add(member);
        System.out.println("Member " + member.getName() + " Was registered to the library Successfully");
    }

    public void unregisterMember(Member member) throws BookNotFoundException {
        boolean isRemoved  = members.remove(member);
        if(!isRemoved){
            throw new BookNotFoundException("Member " + member.getName() + "is not found");
        }else{
            System.out.println("Member " + member.getName() + " Was unregistered the library Successfully");
        }
    }

    public Book findBookByTitle(String title) throws BookNotFoundException {
        return books.stream().filter(book -> Objects.equals(book.getTitle(), title))
                .findFirst()
                .orElseThrow( () ->  new BookNotFoundException("Book with title : "+ title + " is not found"));

    }

    public Book findBookByAuthor(String author) throws BookNotFoundException {
        return books.stream().filter(book -> Objects.equals(book.getAuthor(), author))
                .findFirst()
                .orElseThrow( () ->  new BookNotFoundException("Book with author : "+ author + " is not found"));
    }

    public void borrowBook(int memberId , int bookId) throws BookAlreadyBorrowedException, BookNotFoundException, MemberNotFoundException {
        Book book = books.stream()
                .filter(b -> Objects.equals(b.getId(), bookId))
                .findFirst()
                .orElseThrow( () ->  new BookNotFoundException("Book with id : "+ bookId + " is not found"));


        Member member = members.stream().filter(m -> Objects.equals(m.getId(), memberId))
                .findFirst()
                .orElseThrow( () ->  new MemberNotFoundException("Member with id : "+ memberId + " is not found"));


        member.borrowBook(book);

    }

    public void returnBook(int memberId , int bookId) throws BookNotBorrowedException, MemberNotFoundException, BookNotFoundException {
        Book book = books.stream()
                .filter(b -> Objects.equals(b.getId(), bookId))
                .findFirst()
                .orElseThrow( () ->  new BookNotFoundException("Book with id : "+ bookId + " is not found"));


        Member member = members.stream().filter(m -> Objects.equals(m.getId(), memberId))
                .findFirst()
                .orElseThrow( () ->  new MemberNotFoundException("Member with id : "+ memberId + " is not found"));

        member.returnBook(book);

    }
}

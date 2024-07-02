package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    }

    public void removeBook(Book book) throws BookNotFoundException {
        Boolean isRemoved = books.remove(book);
        if(!isRemoved){
            throw new BookNotFoundException("Book " + book.getTitle() + "is not found");
        }
    }

    public void registerMember(Member member){
        members.add(member);
    }

    public void unregisterMember(Member member) throws BookNotFoundException {
        boolean isRemoved  = members.remove(member);
        if(!isRemoved){
            throw new BookNotFoundException("Member " + member.getName() + "is not found");
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

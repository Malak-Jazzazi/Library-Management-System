package src;

import src.exception.BookAlreadyBorrowedException;
import src.exception.BookNotFoundException;
import src.exception.MemberNotFoundException;
import src.model.Book;
import src.model.Library;
import src.model.Member;

public class Main {
    public static void main(String[] args) throws BookAlreadyBorrowedException, BookNotFoundException, MemberNotFoundException {

        Book book1 = new Book(1 , "star wars" , "jim"  , "1890" , "1895");
        Book book2 = new Book(2 , "cooking" , "ali"  , "2000" , "2016");
        Book book3 = new Book(3 , "heads first" , "tom"  , "1900" , "1998");

        Member member1 = new Member(1 , "Malak" , "Mjazzazi98@gmail.com");
        Member member2 = new Member(2 , "zaid" , "Zjazzazi01@gmail.com");

        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.registerMember(member1);
        library.registerMember(member2);

        try {
            library.borrowBook(1 ,1);
            library.borrowBook(1 ,2);
            library.borrowBook(2 ,2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            library.removeBook(book1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

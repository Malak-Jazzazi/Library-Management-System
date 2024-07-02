package src;

public class Book extends LibraryItem implements Borrowable{
    private final int id;
    private final String title;
    private final String author;
    private final String genre;
    private final String year;
    private boolean isBorrowed;

    public Book(int id, String title, String author, String genre, String year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.isBorrowed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void borrow(){
        this.isBorrowed = true;
    }

    public void returnBook(){
        this.isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                ", isBorrowed='" + isBorrowed + '\'' +
                '}';
    }

    @Override
    String getType() {
        return "Book";
    }
}

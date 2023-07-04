package com.coderscampus.ShelfApp.Domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bookshelves")
public class Bookshelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookshelf_id")
    private Integer bookshelfId;

    private String bookshelfName;

    @ManyToMany
    @JoinTable(name = "bookshelves_books",joinColumns = @JoinColumn(name = "bookshelf_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Integer getBookshelfId() {
        return bookshelfId;
    }

    public void setBookshelfId(Integer bookshelfId) {
        this.bookshelfId = bookshelfId;
    }

    public String getBookshelfName() {
        return bookshelfName;
    }

    public void setBookshelfName(String bookshelfName) {
        this.bookshelfName = bookshelfName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

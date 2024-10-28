package org.JDBCHex.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private String author;
    private Date publishedDate;
    private String isbn;
    public Book(int id, String title, String author, Date date, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedDate = date;
        this.isbn = isbn;
    }
    public String ToString() {
                return id + " " + title + " " + author + " " + publishedDate.toString() + " " + isbn;
    }
}

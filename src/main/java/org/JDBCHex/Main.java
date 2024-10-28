package org.JDBCHex;

import org.JDBCHex.db.DatabaseManager;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        db.connect();
        db.addReader("Arkady", "pb487732@gmail.com");
        db.addReader("Petr", "abra@gmail.com");
        db.getAllReaders();
        db.deleteReader(1);
        db.getAllReaders();
        db.addBook("toshika", "Arkady", "lalalalal", new Date());
        db.addBook("aaaa", "aaaa", "aaaaa", new Date());
        db.getAllBooks();
        db.removeBook(1);
        db.close();
    }
}
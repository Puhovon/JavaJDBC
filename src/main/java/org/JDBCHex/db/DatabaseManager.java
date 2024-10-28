package org.JDBCHex.db;

import org.JDBCHex.models.Book;
import org.JDBCHex.models.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/library_db";
    static final String USER = "postgres";
    static final String PASS = "123456";

    static final String GET_ALL_BOOKS = "SELECT * FROM books";
    static final String GET_BOOK_BY_TITLE = "SELECT * FROM books WHERE name = ?";
    static final String GET_ALL_READERS = "SELECT * FROM readers";
    static final String GET_READERS_BY_EMAIL = "SELECT * FROM readers WHERE email = ?";
    static final String REMOVE_READER = "DELETE FROM readers WHERE id = ?";
    static final String REMOVE_BOOK = "DELETE FROM books WHERE id = ?";
    static final String ADD_BOOK = "INSERT INTO  books(name, author, publishedDate, isbn) VALUES (?, ?, ?, ?)";
    static final String ADD_READER = "INSERT INTO readers (name, email) VALUES (?, ?)";

    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database");
            var a = getAllBooks();
            a.forEach(b -> System.out.println(b.ToString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(GET_ALL_BOOKS)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("name");
                String author = resultSet.getString("author");
                Date publishedDate = resultSet.getDate("publishedDate");
                String isbn = resultSet.getString("isbn");
                Book book = new Book(id, title, author, publishedDate, isbn);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("---------------- Books ----------------");
        books.forEach(b -> System.out.println(b.ToString()));
        System.out.println("---------------------------------------");

        return books;
    }

    public Book findBookByTitle(String name) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BOOK_BY_TITLE)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String author = resultSet.getString("author");
                    Date publishedDate = resultSet.getDate("publishedDate");
                    String isbn = resultSet.getString("isbn");
                    return new Book(id, name, author, publishedDate, isbn);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBook(String name, String author, String isbn, Date publishedDate) {

        try (PreparedStatement statement = connection.prepareStatement(ADD_BOOK)){
            statement.setString(1, name);
            statement.setString(2, author);
            statement.setDate(3, new java.sql.Date( publishedDate.getTime()));
            statement.setString(4, isbn);

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeBook(int id) {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_BOOK)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addReader(String name, String email) {
        try (PreparedStatement statement = connection.prepareStatement(ADD_READER)) {
            statement.setString(1, name);
            statement.setString(2, email);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reader> getAllReaders() {
        List<Reader> readers = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_READERS)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                Reader reader = new Reader(id, name, email);
                readers.add(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("---------------- Readers ----------------");
        readers.forEach(r -> System.out.println(r.ToString()));
        System.out.println("-----------------------------------------");

        return readers;
    }

    public Reader findReaderByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement(GET_READERS_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                return new Reader(id, name, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteReader(int id) {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_READER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

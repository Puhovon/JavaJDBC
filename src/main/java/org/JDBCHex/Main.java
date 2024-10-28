package org.JDBCHex;

import org.JDBCHex.db.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();
        db.connect();
        db.close();
    }
}
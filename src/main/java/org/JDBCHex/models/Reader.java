package org.JDBCHex.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Reader {
    private int id;
    private String name;
    private String email;
    public Reader(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String ToString() {
        return id + " " + name + " " + email;
    }
}

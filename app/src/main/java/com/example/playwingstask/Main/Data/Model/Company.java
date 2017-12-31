package com.example.playwingstask.Main.Data.Model;

/**
 * Created by KiyoungLee on 2017-12-31.
 */

public class Company {
    private int id;
    private String name;
    private String color;
    private String code;
    private String lastModifed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLastModifed() {
        return lastModifed;
    }

    public void setLastModifed(String lastModifed) {
        this.lastModifed = lastModifed;
    }
}

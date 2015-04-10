package de.rodemerk.vokabeltrainer.database;

/**
 * Created by Laurent Rodemerk on 27.03.2015.
 */
public class Unit {

    private int id;
    private String unit;
    private String author;
    private String language;

    public Unit() {
    }

    public Unit(String unit, String author, String language) {

        this.unit = unit;
        this.author = author;
        this.language = language;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return id + ". " + unit + ", " + language + " (" + author + ")";
    }

}

package de.rodemerk.vokabeltrainer.database;

/**
 * Created by Laurent Rodemerk on 06.04.2015.
 */
public class Language {

    private String language;
    private int id;

    public Language(){}
    public Language(String language){
        this.language=language;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return id+". "+language;
    }
}

package de.rodemerk.vokabeltrainer.database;

/**
 * Created by Laurent Rodemerk on 21.03.2015.
 */
public class Vocable {

    private int id;
    private String primaryForm;
    private String grammarForm;
    private String translation;
    private String unit;

    public Vocable() {
    }

    public Vocable(String primaryForm, String grammarForm, String translation, String unit) {
        this.primaryForm = primaryForm;
        this.grammarForm = grammarForm;
        this.translation = translation;
        this.unit = unit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPrimaryForm(String primaryForm) {
        this.primaryForm = primaryForm;
    }

    public String getPrimaryForm() {
        return primaryForm;
    }

    public void setGrammarForm(String grammarForm) {
        this.grammarForm = grammarForm;
    }

    public String getGrammarForm() {
        return grammarForm;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return id + ". " + primaryForm + " - " + grammarForm + " - " + translation + " (" + unit + ")";
    }
}

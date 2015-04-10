package de.rodemerk.vokabeltrainer.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";

    private static final String TABLE_VOCABULARY = "vocabulary";
    private static final String KEY_VOCABULARY_ID = "id";
    private static final String KEY_VOCABULARY_PRIMARY_FORM = "primaryForm";
    private static final String KEY_VOCABULARY_GRAMMAR_FORM = "grammarForm";
    private static final String KEY_VOCABULARY_TRANSLATION = "translation";
    private static final String KEY_VOCABULARY_UNIT = "unit";

    private static final String[] VOCABULARY_COLUMNS = {KEY_VOCABULARY_ID, KEY_VOCABULARY_PRIMARY_FORM,
            KEY_VOCABULARY_GRAMMAR_FORM, KEY_VOCABULARY_TRANSLATION, KEY_VOCABULARY_UNIT};

    private static final String TABLE_UNIT = "unit";
    private static final String KEY_UNIT_ID = "id";
    private static final String KEY_UNIT_UNIT = "unit";
    private static final String KEY_UNIT_AUTHOR = "author";
    private static final String KEY_UNIT_LANGUAGE = "language";

    private static final String[] UNIT_COLUMNS = {KEY_UNIT_ID, KEY_UNIT_UNIT, KEY_UNIT_AUTHOR,
            KEY_UNIT_LANGUAGE};

    private static final String TABLE_LANGUAGE = "language";
    private static final String KEY_LANGUAGE_ID = "id";
    private static final String KEY_LANGUAGE_LANGUAGE = "language";

    private static final String[] LANGUAGE_COLUMNS = {KEY_LANGUAGE_ID, KEY_LANGUAGE_LANGUAGE};



    public MySQLiteOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_VOCABULARY = "create table " + TABLE_VOCABULARY + " ( " + KEY_VOCABULARY_ID +
                " integer primary key autoincrement, " + KEY_VOCABULARY_PRIMARY_FORM + " text, " +
                KEY_VOCABULARY_GRAMMAR_FORM + " text, " + KEY_VOCABULARY_TRANSLATION + " text, " +
                KEY_VOCABULARY_UNIT + " text)";

        String CREATE_TABLE_UNIT = "create table " + TABLE_UNIT + " ( "
                + KEY_UNIT_ID + " integer primary key autoincrement, " + KEY_UNIT_UNIT + " text, "
                + KEY_UNIT_AUTHOR + " text, " + KEY_UNIT_LANGUAGE + " text)";

        String CREATE_TABLE_LANGUAGE = "create table " + TABLE_LANGUAGE + " ( " + KEY_LANGUAGE_ID
                + " integer primary key autoincrement, " + KEY_LANGUAGE_LANGUAGE + " text)";

        db.execSQL(CREATE_TABLE_VOCABULARY);
        db.execSQL(CREATE_TABLE_UNIT);
        db.execSQL(CREATE_TABLE_LANGUAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String drop = "drop table if exists ";

        db.execSQL(drop + TABLE_VOCABULARY);
        db.execSQL(drop + TABLE_UNIT);
        db.execSQL(drop + TABLE_LANGUAGE);

        this.onCreate(db);
    }


    // ---------------------------------------------------------------------------------------------

    public void addVocable(Vocable vocable) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_VOCABULARY_PRIMARY_FORM, vocable.getPrimaryForm());
        values.put(KEY_VOCABULARY_GRAMMAR_FORM, vocable.getGrammarForm());
        values.put(KEY_VOCABULARY_TRANSLATION, vocable.getTranslation());
        values.put(KEY_VOCABULARY_UNIT, vocable.getUnit());

        db.insert(TABLE_VOCABULARY, null, values);

        db.close();
    }

    public void addUnit(Unit unit) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_UNIT_UNIT, unit.getUnit());
        values.put(KEY_UNIT_AUTHOR, unit.getAuthor());
        values.put(KEY_UNIT_LANGUAGE, unit.getLanguage());

        db.insert(TABLE_UNIT, null, values);

        db.close();

    }

    public void addLanguage(Language language) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_LANGUAGE_LANGUAGE, language.getLanguage());

        db.insert(TABLE_LANGUAGE, null, values);
        db.close();
    }

    public Vocable getVocable(int id) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_VOCABULARY, VOCABULARY_COLUMNS, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Vocable vocable = new Vocable();
        vocable.setId(Integer.parseInt(cursor.getString(0)));
        vocable.setPrimaryForm(cursor.getString(1));
        vocable.setGrammarForm(cursor.getString(2));
        vocable.setTranslation(cursor.getString(3));
        vocable.setUnit(cursor.getString(4));

        return vocable;

    }

    public Unit getUnit(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_UNIT, UNIT_COLUMNS, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Unit unit = new Unit();
        unit.setId(Integer.parseInt(cursor.getString(0)));
        unit.setUnit(cursor.getString(1));
        unit.setAuthor(cursor.getString(2));
        unit.setLanguage(cursor.getString(3));

        return unit;
    }

    public Language getLanguage(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_LANGUAGE, LANGUAGE_COLUMNS, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Language language = new Language();
        language.setId(Integer.parseInt(cursor.getString(0)));
        language.setLanguage(cursor.getString(1));

        return language;

    }

    public Language getLanguage(String language) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_LANGUAGE, LANGUAGE_COLUMNS, KEY_LANGUAGE_LANGUAGE + " = ?",
                new String[]{String.valueOf(language)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Language l = new Language();
        l.setId(Integer.parseInt(cursor.getString(0)));
        l.setLanguage(cursor.getString(1));

        return l;

    }


    public List<Vocable> getAllVocables(String unit) {
        List<Vocable> vocabulary = new LinkedList<Vocable>();
        String query = "select * from " + TABLE_VOCABULARY + " where " + KEY_VOCABULARY_UNIT + " =?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{unit});

        Vocable vocable = null;

        if (cursor.moveToFirst()) {
            do {

                vocable = new Vocable();
                vocable.setId(Integer.parseInt(cursor.getString(0)));
                vocable.setPrimaryForm(cursor.getString(1));
                vocable.setGrammarForm(cursor.getString(2));
                vocable.setTranslation(cursor.getString(3));
                vocable.setUnit(cursor.getString(4));

                vocabulary.add(vocable);

            } while (cursor.moveToNext());
        }

        return vocabulary;
    }

    public List<Unit> getAllUnits(String language) {
        List<Unit> units = new LinkedList<Unit>();
        String query = "select * from " + TABLE_UNIT + " where " + KEY_UNIT_LANGUAGE + " =?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{language});

        Unit unit = null;

        if (cursor.moveToFirst()) {
            do {

                unit = new Unit();
                unit.setId(Integer.parseInt(cursor.getString(0)));
                unit.setUnit(cursor.getString(1));
                unit.setAuthor(cursor.getString(2));
                unit.setLanguage(cursor.getString(3));


                units.add(unit);

            } while (cursor.moveToNext());
        }

        return units;
    }


    public List<Vocable> getAllVocables() {
        List<Vocable> vocabulary = new LinkedList<Vocable>();
        String query = "select * from " + TABLE_VOCABULARY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Vocable vocable = null;

        if (cursor.moveToFirst()) {
            do {

                vocable = new Vocable();
                vocable.setId(Integer.parseInt(cursor.getString(0)));
                vocable.setPrimaryForm(cursor.getString(1));
                vocable.setGrammarForm(cursor.getString(2));
                vocable.setTranslation(cursor.getString(3));
                vocable.setUnit(cursor.getString(4));

                vocabulary.add(vocable);

            } while (cursor.moveToNext());
        }

        return vocabulary;
    }

    public List<Unit> getALlUnits() {

        List<Unit> units = new LinkedList<Unit>();
        String query = "select * from " + TABLE_UNIT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Unit unit = null;

        if (cursor.moveToFirst()) {
            do {

                unit = new Unit();
                unit.setId(Integer.parseInt(cursor.getString(0)));
                unit.setUnit(cursor.getString(1));
                unit.setAuthor(cursor.getString(2));
                unit.setLanguage(cursor.getString(3));


                units.add(unit);

            } while (cursor.moveToNext());
        }

        return units;

    }

    public List<Language> getAllLanguages() {
        List<Language> languages = new LinkedList<Language>();
        String query = "select * from " + TABLE_LANGUAGE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Language language = null;

        if (cursor.moveToFirst()) {
            do {

                language = new Language();
                language.setId(Integer.parseInt(cursor.getString(0)));
                language.setLanguage(cursor.getString(1));


                languages.add(language);

            } while (cursor.moveToNext());
        }

        return languages;
    }

    public int updateVocable(Vocable vocable, int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VOCABULARY_PRIMARY_FORM, vocable.getPrimaryForm());
        values.put(KEY_VOCABULARY_GRAMMAR_FORM, vocable.getGrammarForm());
        values.put(KEY_VOCABULARY_TRANSLATION, vocable.getTranslation());
        values.put(KEY_VOCABULARY_UNIT, vocable.getUnit());

        int i = db.update(TABLE_VOCABULARY, values, KEY_VOCABULARY_ID + "=?",
                new String[]{String.valueOf(id)});

        db.close();
        return i;
    }


    public void deleteVocable(Vocable vocable) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_VOCABULARY, KEY_VOCABULARY_ID + " =?",
                new String[]{String.valueOf(vocable.getId())});

        db.close();
    }

    public void deleteUnit(Unit unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_UNIT, KEY_UNIT_ID + " =?", new String[]{String.valueOf(unit.getId())});

        db.close();

        deleteVocabulary(unit.getUnit());


    }

    public void deleteLanguage(Language language) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_LANGUAGE, KEY_LANGUAGE_ID + " =?",
                new String[]{String.valueOf(language.getId())});
        db.close();

        deleteUnits(language.getLanguage());
    }

    public void deleteVocabulary(String unit) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_VOCABULARY, KEY_VOCABULARY_UNIT + " =?", new String[]{unit});

        db.close();
    }

    public void deleteUnits(String language) {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Unit> units = getAllUnits(language);

        Unit unit = null;

        for (int i = 0; i < units.size(); i++) {
            unit = units.get(i);
            deleteVocabulary(unit.getUnit());
        }

        db.delete(TABLE_UNIT, KEY_UNIT_LANGUAGE + " =?", new String[]{language});

        db.close();
    }

    public void deleteLanguages() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Language> languages = getAllLanguages();

        Language language = null;

        for (int i = 0; i < languages.size(); i++) {
            language = languages.get(i);
            deleteUnits(language.getLanguage());
            db.delete(TABLE_LANGUAGE, KEY_LANGUAGE_ID + " =?", new String[]{String.valueOf(i)});
        }

        db.close();

    }


}

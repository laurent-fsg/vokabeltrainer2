package de.rodemerk.vokabeltrainer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.rodemerk.vokabeltrainer.database.Language;
import de.rodemerk.vokabeltrainer.database.MySQLiteOpenHelper;
import de.rodemerk.vokabeltrainer.database.Unit;
import de.rodemerk.vokabeltrainer.database.Vocable;

public class WordActivity extends ActionBarActivity {

    private Button bt_show, bt_add, bt_delete, bt_showU, bt_showL;
    private ListView ls;

    private EditText pr, gr, tr, un, la, au;

    Context ctx;
    MySQLiteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);


        db = new MySQLiteOpenHelper(this);

        ctx = this;


        bt_show = (Button) findViewById(R.id.bt_show);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_showU = (Button) findViewById(R.id.bt_showUnit);
        bt_showL = (Button) findViewById(R.id.bt_showLanguages);

        pr = (EditText) findViewById(R.id.ed_primary);
        gr = (EditText) findViewById(R.id.ed_grammar);
        tr = (EditText) findViewById(R.id.ed_translation);
        un = (EditText) findViewById(R.id.ed_unit);
        la = (EditText) findViewById(R.id.ed_language);
        au = (EditText) findViewById(R.id.ed_author);

        ls = (ListView) findViewById(R.id.listView);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addVocable(new Vocable(pr.getText().toString(), gr.getText().toString(),
                        tr.getText().toString(), un.getText().toString()));


                Unit u = new Unit(un.getText().toString(), au.getText().toString(),
                        la.getText().toString());


                List<Unit> units = db.getAllUnits(u.getLanguage());

                boolean unitExists = false;


                for (int i = 0; i < units.size(); i++) {


                    if ((u.getUnit().equals(units.get(i).getUnit()))
                            && (u.getAuthor().equals(units.get(i).getAuthor()))
                            && (u.getLanguage().equals(units.get(i).getLanguage()))) {


                        unitExists = true;
                        break;


                    }

                }

                if (!unitExists) {
                    db.addUnit(new Unit(un.getText().toString(), au.getText().toString(),
                            la.getText().toString()));
                }


                List<Language> languages = db.getAllLanguages();

                boolean languageExists = false;

                Language language = new Language(la.getText().toString());

                for (int i = 0; i < languages.size(); i++) {

                    if (language.getLanguage().equals(languages.get(i).getLanguage())) {
                        languageExists = true;
                        break;
                    }

                }

                if (!languageExists) {
                    db.addLanguage(language);
                }

                pr.setText(null);
                gr.setText(null);
                tr.setText(null);
                un.setText(null);
                la.setText(null);
                au.setText(null);
            }
        });

        bt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    List<Vocable> list = db.getAllVocables();

                    List valueList = new ArrayList<String>();
                    valueList = list;

                    ListAdapter adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1, valueList);

                    ls.setAdapter(adapter);

                } catch (Exception e) {
                    System.err.println("Fehler");
                }

            }
        });
        bt_showU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    List<Unit> list = db.getALlUnits();

                    List valueList = new ArrayList<String>();
                    valueList = list;

                    ListAdapter adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1, valueList);

                    ls.setAdapter(adapter);

                } catch (Exception e) {
                    System.err.println("Fehler");
                }

            }
        });

        bt_showL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Language> list = db.getAllLanguages();

                    List valueList = new ArrayList<String>();
                    valueList = list;

                    ListAdapter adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1, valueList);

                    ls.setAdapter(adapter);

                } catch (Exception e) {
                    System.err.println("Fehler");
                }

            }
        });

    }


}

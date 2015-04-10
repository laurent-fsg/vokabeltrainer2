package de.rodemerk.vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {


    private CardView card_learning, card_collection, card_statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = this.getSupportActionBar();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void setComponents() {

        card_learning = (CardView) findViewById(R.id.card_view_lernen);
        card_collection = (CardView) findViewById(R.id.card_view_sammlung);
        card_statistics = (CardView) findViewById(R.id.card_view_statistik);


        card_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startLearning();

            }
        });

        card_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startCollection();

            }
        });

        card_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startStatistics();

            }
        });

    }


    private void startLearning() {

        Intent i = new Intent(MainActivity.this, LernenActivity.class);
        startActivity(i);

    }

    private void startCollection() {

        Intent i = new Intent(MainActivity.this, WordActivity.class);
        startActivity(i);


    }

    private void startStatistics() {

        Intent i = new Intent(MainActivity.this, StatistikActivity.class);

        startActivity(i);

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void startCollection(View v) {
        startCollection();
    }

    public void startLearning(View v) {
        startLearning();
    }

    public void startStatistics(View v) {
        startStatistics();
    }
}

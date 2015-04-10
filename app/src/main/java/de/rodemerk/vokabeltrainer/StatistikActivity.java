package de.rodemerk.vokabeltrainer;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;


public class StatistikActivity extends ActionBarActivity {

    String backgroundColor = R.color.primaryColor + "";
    String statusbarColor = R.color.primaryColorDark + "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_statistics);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.abc_ic_clear_mtrl_alpha);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {


                return true;
            }
        });


        Window window = getWindow();


        if (checkAndroidVersion()) {

            window.setStatusBarColor(getResources().getColor(R.color.primaryColorDark));


        }


    }


    public boolean checkAndroidVersion() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            return true;
        }

        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == KeyEvent.KEYCODE_BACK || id == android.R.id.home) {

            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}

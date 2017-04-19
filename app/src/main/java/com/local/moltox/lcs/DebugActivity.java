package com.local.moltox.lcs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import sqlite.helper.DBHelperClass;

public class DebugActivity extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv1 = (TextView) findViewById(R.id.debug_tv1);
        fillTextViews();
    }

    private void fillTextViews() {
        DBHelperClass db = new DBHelperClass(getApplicationContext());
        String query = "SELECT * FROM " + db.CARDS_TABLE_NAME;
        /*
        String query =  "SELECT * FROM " + db.CARDS_CATEGORY_TABLE_NAME
                + " WHERE " + db.CARDS_CATEGORY_TABLE_NAME + "." + db.COL_CARDS_CATEGORY_CARDID + "=" + "1"
                + " AND " + db.CARDS_CATEGORY_TABLE_NAME + "." + db.COL_CARDS_CATEGORY_CATEGORYID + "=" + "1";
        */
        String cursorString = db.dumpQuerytoString(query);
  //        String finalString = cursorString;
        tv1.setText(cursorString);

/*
                + " WHERE " + db.CARDS_TABLE_NAME + "." + db.COL_COMMON_ID + " = " + db.COL_CARDS_DONE_CARD_ID
                + " ORDER BY " + db.COL_CARDS_DONE_CARD_ID

        String query = "SELECT * FROM " + db.CARDS_TABLE_NAME
                + " LEFT JOIN " + db.CARDS_DONE_TABLE_NAME
                + " ON " + db.CARDS_DONE_TABLE_NAME + "." + db.COL_CARDS_DONE_CARD_ID + " = " + db.CARDS_TABLE_NAME + "." + db.COL_COMMON_ID

                // + "," + db.CARDS_TABLE_NAME


                + " WHERE " + db.COL_CARDS_DONE_DATETIME_INCORRECT + " IS NULL"
                + " AND " + db.COL_CARDS_DONE_DATETIME_CORRECT + " IS NULL"
                *
                + db.COL_CARDS_DONE_CARD_ID
                + " OR " + " NOT IN "


                + " ORDER BY " + db.CARDS_DONE_TABLE_NAME + "." + db.COL_CARDS_DONE_DATETIME_CORRECT
                + "," + db.CARDS_DONE_TABLE_NAME + "." + db.COL_CARDS_DONE_DATETIME_INCORRECT ;
  */


    }
}

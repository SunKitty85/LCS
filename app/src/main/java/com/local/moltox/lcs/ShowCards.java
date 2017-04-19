package com.local.moltox.lcs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

import cursorAdapter.helper.CardlistCursorAdapter;
import sqlite.helper.DBHelperClass;

public class ShowCards extends AppCompatActivity {
    ListView lv_CardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cards);
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
        lv_CardList = (ListView) findViewById(R.id.lv_showCards);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillListView();
    }

    private void fillListView() {
        DBHelperClass db = new DBHelperClass(this);
        Cursor cursor = db.getAllCardsCursor();
        CursorAdapter showCardsCursorAdapter = new CardlistCursorAdapter(this,cursor);
        lv_CardList.setAdapter(showCardsCursorAdapter);
    }


}


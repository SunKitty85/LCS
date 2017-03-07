package com.local.moltox.lcs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import sqlite.helper.DBHelperClass;
import sqlite.helper.Table_cards;


public class qanda_activity extends AppCompatActivity {
    private static final String TAG = "qanda_activity: ";
    DBHelperClass db;
    TextView tv_qanda_frag;
    Button btn_newrand;
    int rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qanda_activity);
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
        tv_qanda_frag = (TextView) findViewById(R.id.tv_qanda_frag);
        btn_newrand = (Button) findViewById(R.id.btn_newrand);
        btn_newrand.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Random r = new Random();
                                               rand = r.nextInt(10 - 1) + 1;
                                               tv_qanda_frag.setText("Random ist: " + String.valueOf(rand));
                                               insertTestCards();
                                           }
                                       }

        );
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



    private void insertTestCards() {

        db = new DBHelperClass(getApplicationContext());
        Table_cards card1 = new Table_cards("Question1_1","Answer1_1","Answer2_1","Answer3_1","Answer4_1",20170307);
        Table_cards card2 = new Table_cards("Question1_2","Answer1_2","Answer2_2","Answer3_2","Answer4_2",20170308);
        Table_cards card3 = new Table_cards("Question1_3","Answer1_3","Answer2_3","Answer3_3","Answer4_3",20170309);
        Table_cards card4 = new Table_cards("Question1_4","Answer1_4","Answer2_4","Answer3_4","Answer4_4",20170310);

        long card_id1 = db.createCard(card1);
        long card_id2 = db.createCard(card2);
        long card_id3 = db.createCard(card3);
        long card_id4 = db.createCard(card4);

        Log.v(TAG, "Inserted IDs: " + String.valueOf(card_id1) + " " + String.valueOf(card_id2) + " " +
                String.valueOf(card_id3) + " " + String.valueOf(card_id4));

    }

}


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

import java.util.List;
import java.util.Random;

import sqlite.helper.DBHelperClass;
import sqlite.helper.Card;


public class qanda_activity extends AppCompatActivity {
    private static final String TAG = qanda_activity.class.getName();
    DBHelperClass db;
    TextView tv_qanda_card_id;
    TextView tv_qanda_card_question;
    TextView tv_qanda_card_answer1;


    Button btn_newCard;
    long rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qanda_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DBHelperClass(getApplicationContext());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                insertTestCards();
                showCardList();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_qanda_card_id = (TextView) findViewById(R.id.tv_qanda_card_id);
        tv_qanda_card_question = (TextView) findViewById(R.id.tv_qanda_card_question);
        tv_qanda_card_answer1 = (TextView) findViewById(R.id.tv_qanda_card_answer1);


        btn_newCard = (Button) findViewById(R.id.btn_newCard);
        btn_newCard.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Random r = new Random();
                                               int countCards = db.getCountCards();
                                               Log.v(TAG, "Es gibt  " + countCards + " Cards");
                                               if (countCards < 1) {
                                                   insertTestCards();
                                               } else {
                                                   long cardid;
                                                   do {
                                                       rand = r.nextInt(1500);
                                                       cardid = rand % (countCards + 1);
                                                       Log.v(TAG, "Random ist: " + String.valueOf(rand) +
                                                               "\nCard ID ist: " + cardid);
                                                   } while (cardid == 0);
                                                   showCard(cardid);
                                               }
                                           }
                                       }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        db.closeDB();
    }

    private void showCard(long id) {
        Card card = db.getCard(id);
        tv_qanda_card_id.setText("Card ID: " + card.getId()
                + "\nRelease Date: " + card.getReleaseDate()
                + "\nKategorie ID: " + card.getCategory_id());

        tv_qanda_card_question.setText(getString(R.string.tv_qanda_frag_card_question) + " " + card.getQuestion());
        tv_qanda_card_answer1.setText(getString(R.string.tv_qanda_frag_card_answer1) + " " + card.getAnswer1());

    }

    private void showCardList() {
        Log.v(TAG, "Get all Cards");
        List<Card> allCards = db.getAllCards();
        for (Card tc : allCards) {
            Log.v(TAG, "CARD: " + tc.getId() + " " + tc.getQuestion());
        }
    }

    // Debug only
    private void insertTestCards() {
        Card card1 = new Card("Question1_1", "Answer1_1", "Answer2_1", "Answer3_1", "Answer4_1", 20170307,1);
        Card card2 = new Card("Question1_2", "Answer1_2", "Answer2_2", "Answer3_2", "Answer4_2", 20170308,1);
        Card card3 = new Card("Question1_3", "Answer1_3", "Answer2_3", "Answer3_3", "Answer4_3", 20170309,1);
        Card card4 = new Card("Question1_4", "Answer1_4", "Answer2_4", "Answer3_4", "Answer4_4", 20170310,1);
        long card_id1 = db.createCard(card1);
        long card_id2 = db.createCard(card2);
        long card_id3 = db.createCard(card3);
        long card_id4 = db.createCard(card4);
        Log.v(TAG, "Inserted IDs: " + String.valueOf(card_id1) + " " + String.valueOf(card_id2) + " " +
                String.valueOf(card_id3) + " " + String.valueOf(card_id4));
    }
}

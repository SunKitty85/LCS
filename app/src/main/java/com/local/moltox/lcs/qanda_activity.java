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

import sqlite.helper.Cards_done;
import sqlite.helper.DBHelperClass;
import sqlite.helper.Card;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class qanda_activity extends AppCompatActivity {
    private static final String TAG = qanda_activity.class.getName();
    DBHelperClass db;
    Long currentCardID;
    TextView tv_qanda_card_id;
    TextView tv_qanda_card_question;
    TextView tv_qanda_card_answer1;

    Button btn_newCard;
    Button btn_showAnswer;
    Button btn_AnswerCorrect;
    Button btn_AnswerIncorrect;
    Button btn_dumpQuery;
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

                db.showCardListAsLog();
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
                                                   // TODO Delete isnertTestCards method
                                                   // insertTestCards();
                                               } else {

                                                   do {
                                                       rand = r.nextInt(1500);
                                                       currentCardID = rand % (countCards + 1);
                                                       Log.v(TAG, "Random ist: " + String.valueOf(rand) +
                                                               "\nCard ID ist: " + currentCardID);
                                                   } while (currentCardID == 0);
                                                   showCard(currentCardID);
                                               }
                                           }
                                       }
        );

        btn_showAnswer = (Button) findViewById(R.id.btn_showAnswer);
        btn_showAnswer.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Log.v(TAG, "Show Answer Button");
                                                  tv_qanda_card_answer1.setVisibility(View.VISIBLE);
                                              }
                                          }


        );

        btn_AnswerCorrect = (Button) findViewById(R.id.btn_answer_correct);
        btn_AnswerCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Answer Correct");
                Cards_done dc = new Cards_done(currentCardID, TRUE);
                db.createCard_DoneRow(dc);
            }
        });

        btn_AnswerIncorrect = (Button) findViewById(R.id.btn_answer_not_correct);
        btn_AnswerIncorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Answer Incorrect");
                Cards_done dc = new Cards_done(currentCardID, FALSE);
                db.createCard_DoneRow(dc);
                // +++++++++++++++++++++++++++++++++++++++++++++++
                // Debug only:
                // ++++++++++++++++++++++++++++++++++++++++++++++++
                db.selectAllOfTabletoLog(db.CARDS_DONE_TABLE_NAME);
            }
        });


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
        tv_qanda_card_answer1.setVisibility(View.INVISIBLE);
        tv_qanda_card_answer1.setText(getString(R.string.tv_qanda_frag_card_answer1) + "\n\n\n " + card.getAnswer1());
        currentCardID = id;
    }
}

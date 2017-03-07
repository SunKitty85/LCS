package com.local.moltox.lcs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by moltox on 04.03.2017.
 */

public class DBHelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LCS_DB.db";
    public static final String CARDS_TABLE_NAME = "cards";
    public static final String CARDS_COL_ID = "id";
    public static final String CARDS_COL_QUESTION = "question";
    public static final String CARDS_COL_ANSWER01 = "answer01";
    public static final String CARDS_COL_ANSWER02 = "answer02";
    public static final String CARDS_COL_ANSWER03 = "answer03";
    public static final String CARDS_COL_ANSWER04 = "answer04";
    public static final String CARDS_COL_RELEASE_DATE = "release_date";


    public DBHelperClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + CARDS_TABLE_NAME + " ("
                        + CARDS_COL_ID + " integer primary key, "
                        + CARDS_COL_QUESTION + " text, "
                        + CARDS_COL_ANSWER01 + " text, "
                        + CARDS_COL_ANSWER02 + " text, "
                        + CARDS_COL_ANSWER03 + " text, "
                        + CARDS_COL_ANSWER04 + " text, "
                        + CARDS_COL_RELEASE_DATE + " text)"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class lcs_db implements BaseColumns {


    }


}

package sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moltox on 04.03.2017.
 */

public class DBHelperClass extends SQLiteOpenHelper {
    private static final String TAG = DBHelperClass.class.getName();
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "LCS_DB.db";

    // Table Names
    private static final String CARDS_TABLE_NAME = "tb_cards";
    private static final String CARDS_DONE_TABLE_NAME = "tb_cards_done";
    private static final String PULLS_TABLE_NAME = "tb_pulls";
    private static final String CATEGORY_TABLE_NAME = "tb_category";

    // Common Column Names
    private static final String COL_COMMON_ID = "id";

    // Columns cards Table
    private static final String COL_CARDS_QUESTION = "question";
    private static final String COL_CARDS_ANSWER01 = "answer01";
    private static final String COL_CARDS_ANSWER02 = "answer02";
    private static final String COL_CARDS_ANSWER03 = "answer03";
    private static final String COL_CARDS_ANSWER04 = "answer04";
    private static final String COL_CARDS_RELEASE_DATE = "release_date";
    private static final String COL_CARDS_CATEGORY_ID = "categoryid";

    // Columns cards_done table
    private static final String COL_CARDS_DONE_CARD_ID = "card_id";
    private static final String COL_CARDS_DONE_DONE_DATETIME = "datetime";
    private static final String COL_CARDS_DONE_CORRECT = "correct";

    // Columns pulls table
    private static final String COL_PULLS_POLLDATETIME = "polldatetime";

    // Columns category table
    private static final String COL_CATEGORY_CATEGORY = "category";
    private static final String COL_CATEGORY_PICFILENAME = "picfilename";

    public DBHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_CARDS =
            "CREATE TABLE " + CARDS_TABLE_NAME + " ("
                    + COL_COMMON_ID + " integer primary key, "
                    + COL_CARDS_QUESTION + " text, "
                    + COL_CARDS_ANSWER01 + " text, "
                    + COL_CARDS_ANSWER02 + " text, "
                    + COL_CARDS_ANSWER03 + " text, "
                    + COL_CARDS_ANSWER04 + " text, "
                    + COL_CARDS_RELEASE_DATE + " text, "
                    + COL_CARDS_CATEGORY_ID + " text)";

    private static final String CREATE_TABLE_CARDS_DONE =
            "CREATE TABLE " + CARDS_DONE_TABLE_NAME + " ("
                    + COL_COMMON_ID + " integer primary key, "
                    + COL_CARDS_DONE_CARD_ID + " integer, "
                    + COL_CARDS_DONE_DONE_DATETIME + " integer, "
                    + COL_CARDS_DONE_CORRECT + " integer)";

    private static final String CREATE_TABLE_PULLS =
            "CREATE TABLE " + PULLS_TABLE_NAME + " ("
                    + COL_COMMON_ID + " integer primary key, "
                    + COL_PULLS_POLLDATETIME + " integer)";

    private static final String CREATE_TABLE_CATEGORY =
            "CREATE TABLE " + CATEGORY_TABLE_NAME + " ("
                    + COL_COMMON_ID + " integer primary key, "
                    + COL_CATEGORY_CATEGORY + " text, "
                    + COL_CATEGORY_PICFILENAME + " text)";



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "DBHelperClass: onCreate");
        db.execSQL(CREATE_TABLE_CARDS);
        db.execSQL(CREATE_TABLE_CARDS_DONE);
        db.execSQL(CREATE_TABLE_PULLS);
        db.execSQL(CREATE_TABLE_CATEGORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CARDS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CARDS_DONE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PULLS_TABLE_NAME);
        onCreate(db);
        // TODO DBHelperClass onUpgrade richtig machen
    }

    // Table Methods
    public long createCard(Card table_card)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CARDS_QUESTION,table_card.getQuestion());
        values.put(COL_CARDS_ANSWER01,table_card.getAnswer1());
        values.put(COL_CARDS_ANSWER02,table_card.getAnswer2());
        values.put(COL_CARDS_ANSWER03,table_card.getAnswer3());
        values.put(COL_CARDS_ANSWER04,table_card.getAnswer4());
        values.put(COL_CARDS_RELEASE_DATE, table_card.getReleaseDate());
        values.put(COL_CARDS_CATEGORY_ID, table_card.getCategory_id());

        long card_id = db.insert(CARDS_TABLE_NAME,null,values);

        return card_id;
    }

    // Fetching a special Card

    public Card getCard(long card_id)  {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =
                "SELECT * FROM " + CARDS_TABLE_NAME + " WHERE "
                + COL_COMMON_ID + " = " + card_id;
        Log.v(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)  cursor.moveToFirst();
        Card card = new Card();
        card.setId(cursor.getInt(cursor.getColumnIndex(COL_COMMON_ID)));
        card.setQuestion(cursor.getString(cursor.getColumnIndex((COL_CARDS_QUESTION))));
        card.setAnswer1(cursor.getString(cursor.getColumnIndex((COL_CARDS_ANSWER01))));
        card.setAnswer2(cursor.getString(cursor.getColumnIndex((COL_CARDS_ANSWER02))));
        card.setAnswer3(cursor.getString(cursor.getColumnIndex((COL_CARDS_ANSWER03))));
        card.setAnswer4(cursor.getString(cursor.getColumnIndex((COL_CARDS_ANSWER04))));
        card.setReleaseDate(cursor.getInt(cursor.getColumnIndex(COL_CARDS_RELEASE_DATE)));
        card.setCategory_id(cursor.getInt(cursor.getColumnIndex(COL_CARDS_CATEGORY_ID)));
        return card;
    }

    public List<Card> getAllCards()  {
        List<Card> tableCards = new ArrayList<Card>();
        String selectQuery = "SELECT * FROM " + CARDS_TABLE_NAME;
        Log.v(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())  {
            do  {
                Card tc = new Card();
                tc.setId(cursor.getInt((cursor.getColumnIndex(COL_COMMON_ID))));
                tc.setQuestion(cursor.getString(cursor.getColumnIndex(COL_CARDS_QUESTION)));
                tableCards.add(tc);
            } while (cursor.moveToNext());
        }
        return tableCards;
    }

    public int getCountCards()  {
        String selectQuery = "SELECT * FROM " + CARDS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        int count = cursor.getCount();
        Log.v(TAG, "Card Count: " + String.valueOf(count));
        return count;
    }

    public void closeDB()  {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) db.close();
    }




}

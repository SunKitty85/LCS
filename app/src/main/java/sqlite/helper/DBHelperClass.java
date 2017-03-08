package sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moltox on 04.03.2017.
 */

public class DBHelperClass extends SQLiteOpenHelper {
    private static final String TAG = DBHelperClass.class.getName();
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "LCS_DB.db";

    // Table Names
    private static final String CARDS_TABLE_NAME = "tb_cards";
    private static final String CARDS_DONE_TABLE_NAME = "tb_cards_done";
    private static final String PULLS_TABLE_NAME = "tb_pulls";

    // Common Column Names
    private static final String COL_COMMON_ID = "id";

    // Columns cards Table
    private static final String COL_CARDS_QUESTION = "question";
    private static final String COL_CARDS_ANSWER01 = "answer01";
    private static final String COL_CARDS_ANSWER02 = "answer02";
    private static final String COL_CARDS_ANSWER03 = "answer03";
    private static final String COL_CARDS_ANSWER04 = "answer04";
    private static final String COL_CARDS_RELEASE_DATE = "release_date";

    // Columns cards_done table
    private static final String COL_CARDS_DONE_CARD_ID = "card_done_card_id";
    private static final String COL_CARDS_DONE_DONE_DATETIME = "card_done_datetime";
    private static final String COL_CARDS_DONE_CORRECT = "card_done_correct";

    // Columns pulls table
    private static final String COL_PULLS_POLLDATETIME = "pulls_polldatetime";

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
                    + COL_CARDS_RELEASE_DATE + " text)";

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


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "DBHelperClass: onCreate");
        db.execSQL(CREATE_TABLE_CARDS);
        db.execSQL(CREATE_TABLE_CARDS_DONE);
        db.execSQL(CREATE_TABLE_PULLS);
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
    public long createCard(Table_cards table_card)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CARDS_QUESTION,table_card.getQuestion());
        values.put(COL_CARDS_ANSWER01,table_card.getAnswer1());
        values.put(COL_CARDS_ANSWER02,table_card.getAnswer2());
        values.put(COL_CARDS_ANSWER03,table_card.getAnswer3());
        values.put(COL_CARDS_ANSWER04,table_card.getAnswer4());
        values.put(COL_CARDS_RELEASE_DATE, table_card.getReleaseDate());

        long card_id = db.insert(CARDS_TABLE_NAME,null,values);

        return card_id;
    }

    // Fetching a special Card

    public Table_cards getCard(long card_id)  {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =
                "SELECT * FROM " + CARDS_TABLE_NAME + " WHERE "
                + COL_COMMON_ID + " = " + card_id;
        Log.v(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)  cursor.moveToFirst();
        Table_cards card = new Table_cards();
        card.setId(cursor.getInt(cursor.getColumnIndex(COL_COMMON_ID)));
        card.setQuestion(cursor.getString(cursor.getColumnIndex((COL_CARDS_QUESTION))));
        card.setAnswer1(cursor.getString(cursor.getColumnIndex((COL_CARDS_ANSWER01))));
        card.setAnswer2(cursor.getString(cursor.getColumnIndex((COL_CARDS_ANSWER02))));
        card.setAnswer3(cursor.getString(cursor.getColumnIndex((COL_CARDS_ANSWER03))));
        card.setAnswer4(cursor.getString(cursor.getColumnIndex((COL_CARDS_ANSWER04))));
        card.setReleaseDate(cursor.getInt(cursor.getColumnIndex(COL_CARDS_RELEASE_DATE)));

        return card;
    }

    public List<Table_cards> getAllCards()  {
        List<Table_cards> tableCards = new ArrayList<Table_cards>();
        String selectQuery = "SELECT * FROM " + CARDS_TABLE_NAME;
        Log.v(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())  {
            do  {
                Table_cards tc = new Table_cards();
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

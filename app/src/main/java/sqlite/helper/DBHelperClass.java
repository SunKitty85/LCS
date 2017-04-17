package sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLInput;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moltox on 04.03.2017.
 */

public class DBHelperClass extends SQLiteOpenHelper {
    private static final String TAG = DBHelperClass.class.getName();
    public static final int DATABASE_VERSION = 9;
    public static final String DATABASE_NAME = "LCS_DB.db";

    // Table Names
    public static final String CARDS_TABLE_NAME = "tb_cards";
    public static final String CARDS_DONE_TABLE_NAME = "tb_cards_done";
    public static final String PULLS_TABLE_NAME = "tb_pulls";
    public static final String CATEGORY_TABLE_NAME = "tb_category";

    // Common Column Names
    public static final String COL_COMMON_ID = "id";

    // Columns cards Table
    public static final String COL_CARDS_QUESTION = "question";
    public static final String COL_CARDS_ANSWER01 = "answer01";
    public static final String COL_CARDS_ANSWER02 = "answer02";
    public static final String COL_CARDS_ANSWER03 = "answer03";
    public static final String COL_CARDS_ANSWER04 = "answer04";
    public static final String COL_CARDS_RELEASE_DATE = "release_date";
    public static final String COL_CARDS_CATEGORY_ID = "categoryid";

    // Columns cards_done table
    public static final String COL_CARDS_DONE_CARD_ID = "card_id";
    public static final String COL_CARDS_DONE_DATETIME_CORRECT = "datetime_correct";
    public static final String COL_CARDS_DONE_DATETIME_INCORRECT = "datetime_incorrect";

    // Columns pulls table
    public static final String COL_PULLS_POLLDATETIME = "polldatetime";

    // Columns category table
    public static final String COL_CATEGORY_CATEGORY = "category";
    public static final String COL_CATEGORY_PICFILENAME = "picfilename";

    // JSONFlags
    public static final int TABLE_FLAG_CARDS = 01;
    public static final int TABLE_FLAG_CATEGORY = 02;
    public static final String TABLE_FLAG_CARDS_CATEGORY = "03";

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
                    + COL_CARDS_DONE_DATETIME_CORRECT + " integer, "
                    + COL_CARDS_DONE_DATETIME_INCORRECT + " integer)";

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
        Log.v(TAG, "onUpgrade: DROPPING ALL TABLES NOW!");
        db.execSQL("DROP TABLE IF EXISTS " + CARDS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CARDS_DONE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PULLS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
        Log.v(TAG, "onUpgrade: DROPPED ALL TABLES!");
        Log.v(TAG, "Creating new Database");
        onCreate(db);
        // TODO DBHelperClass onUpgrade richtig machen
    }

    // Table Methods
    public long createCard(Card table_card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CARDS_QUESTION, table_card.getQuestion());
        values.put(COL_CARDS_ANSWER01, table_card.getAnswer1());
        values.put(COL_CARDS_ANSWER02, table_card.getAnswer2());
        values.put(COL_CARDS_ANSWER03, table_card.getAnswer3());
        values.put(COL_CARDS_ANSWER04, table_card.getAnswer4());
        values.put(COL_CARDS_RELEASE_DATE, table_card.getReleaseDate());
        values.put(COL_CARDS_CATEGORY_ID, table_card.getCategory_id());
        long card_id = db.insert(CARDS_TABLE_NAME, null, values);
        return card_id;
    }


    public long createCard_DoneRow(Cards_done done_card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CARDS_DONE_CARD_ID, done_card.getCard_id());
        values.put(COL_CARDS_DONE_DATETIME_CORRECT, done_card.getDone_DateTime_Correct());
        values.put(COL_CARDS_DONE_DATETIME_INCORRECT, done_card.getDone_DateTime_Incorrect());
        long card_done_ID = db.insert(CARDS_DONE_TABLE_NAME, null, values);
        return card_done_ID;
    }


    // Fetching a special Card
    // *******************************************************************

    public Card getCard(long card_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =
                "SELECT * FROM " + CARDS_TABLE_NAME + " WHERE "
                        + COL_COMMON_ID + " = " + card_id;
        Log.v(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Log.v(TAG, "Moved Cursor to first");
        }
        Log.v(TAG, "Cursor Count: " + cursor.getCount() + "\nCursor pos: " + cursor.getPosition());
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

    // Fetching all cards
    // ***************************************************************************

    public List<Card> getAllCards() {
        List<Card> tableCards = new ArrayList<Card>();
        String selectQuery = "SELECT * FROM " + CARDS_TABLE_NAME;
        Log.v(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Card tc = new Card();
                tc.setId(cursor.getInt((cursor.getColumnIndex(COL_COMMON_ID))));
                tc.setQuestion(cursor.getString(cursor.getColumnIndex(COL_CARDS_QUESTION)));
                tableCards.add(tc);
            } while (cursor.moveToNext());
        }
        return tableCards;
    }

    public void showCardListAsLog() {
        Log.v(TAG, "Get all Cards");
        List<Card> allCards = this.getAllCards();
        for (Card tc : allCards) {
            Log.v(TAG, "CARD: " + tc.getId() + " " + tc.getQuestion());
        }
    }


    public int getCountCards() {
        String selectQuery = "SELECT * FROM " + CARDS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int count = cursor.getCount();
        Log.v(TAG, "Card Count: " + String.valueOf(count));
        return count;
    }


    public List<Cards_done> getAllCards_Done_Table() {
        List<Cards_done> tableCards_Done = new ArrayList<Cards_done>();
        String selectQuery = "SELECT * FROM " + CARDS_DONE_TABLE_NAME;
        Log.v(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Cards_done dc = new Cards_done();
                dc.setId(cursor.getInt(cursor.getColumnIndex(COL_COMMON_ID)));
                dc.setCard_id(cursor.getLong(cursor.getColumnIndex(COL_CARDS_DONE_CARD_ID)));
                tableCards_Done.add(dc);
            } while (cursor.moveToNext());
        }
        return tableCards_Done;
    }

    public void showDone_CardListAsLog() {
        Log.v(TAG, "Get all Done_Cards");
        List<Cards_done> allCards = this.getAllCards_Done_Table();
        for (Cards_done dc : allCards) {
            Log.v(TAG, "ID: " + dc.getId() + "\nCard ID: " + dc.getCard_id() + "\n");
        }
    }

    public void selectAllOfTabletoLog(String tableName) {
        String selectQuery = "SELECT * FROM " + tableName;
        Log.v(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String cursorString = DatabaseUtils.dumpCursorToString(cursor);
        Log.v(TAG, "dumpCursortoString: \n" + cursorString);
    }

    public void dumpQuerytoLog(String query) {
        Log.v(TAG, query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        String cursorString = DatabaseUtils.dumpCursorToString(cursor);
        Log.v(TAG, "dumpCursortoString: \n" + cursorString);
    }

    public String dumpQuerytoString(String query) {
        Log.v(TAG, query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        String cursorString = DatabaseUtils.dumpCursorToString(cursor);
        return cursorString;
    }

    public void dumpQuerytoSystemout(String query) {
        Log.v(TAG, query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        Log.v(TAG, "Rows: " + cursor.getCount() + "\nColumns: " + cursor.getColumnCount());
        DatabaseUtils.dumpCursor(cursor);
        //String cursorString = DatabaseUtils.dumpCursorToString(cursor);
        // Log.v(TAG,"dumpCursortoString: \n" + cursorString);
    }

    public void insertIntoFromJson(JSONObject jsonObject) {
        try {
            int table_flag = jsonObject.getInt("TABLE_FLAG");
            Log.v(TAG, "Table Flag: " + table_flag + "\nTable Flag string:" + TABLE_FLAG_CATEGORY + "|");

            switch (table_flag) {
                case TABLE_FLAG_CARDS:

                    break;
                case TABLE_FLAG_CATEGORY:
                    // Data for DB extract:
                    JSONArray jArrayDb = new JSONArray(jsonObject.getString("out_JSON"));
                    Log.v(TAG, "Array Length is: " + String.valueOf(jArrayDb.length()));
                    // Put Data in ContentValue from JSON
                    for (int i = 0; i < jArrayDb.length() - 1; i++) {
                        String myString = jArrayDb.getString(i);
                        JSONObject jObj = new JSONObject(myString);

                        Log.v(TAG, "i " + String.valueOf(i) + "\nJSON: \n"
                                + "\nid= " + jObj.getString("id")
                                + "\ncategory= " + jObj.getString("category")
                                + "\npicfilename= " + jObj.getString("picfilename"));
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(COL_COMMON_ID, jObj.getString("id"));
                        contentValues.put(COL_CATEGORY_CATEGORY, jObj.getString("category"));
                        contentValues.put(COL_CATEGORY_PICFILENAME, jObj.getString("picfilename"));
                        long cat_id = insertCategory(contentValues);
                        Log.v(TAG, "Category inserted with id: " + cat_id);
                    }
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private long insertCategory(ContentValues contentValues) {
        Log.v(TAG, "insert Category method");
        SQLiteDatabase db = this.getWritableDatabase();
        long cat_id = db.insert(CATEGORY_TABLE_NAME,null,contentValues);
        return cat_id;
    }

    private void insertCards(JSONArray jsonArray) {
        Log.v(TAG, "insert Cards method");
    }

    private void insertCards_Category(JSONArray jsonArray) {
        Log.v(TAG, "insert Cards_Category");
    }


    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) db.close();
    }


}

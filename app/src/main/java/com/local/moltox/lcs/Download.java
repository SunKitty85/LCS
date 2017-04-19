package com.local.moltox.lcs;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cursorAdapter.helper.CategoryCursorAdapter;
import misc.helper.DownloadQuery;
import misc.helper.JsonObjectsForDownload;
import sqlite.helper.DBHelperClass;

public class Download extends AppCompatActivity implements
        DownloadQuery.OnRequestExecutedListener {
    // private Context context;
    private static final String TAG = Download.class.getName();
    private static final String SERVER_ROOT_URL = "http://5.9.67.156/lcs/";
    private static final String SERVER_DIRECT_ORDER_EXTENSION = "query.php";
    private static final String API_KEY_DIRECTORDER = "u23923u5r823894n23z34fz8hhdsbvahuishwe8278";
    TextView tv_download_1;
    Button btn_downloadNow;
    ListView lv_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
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
        btn_downloadNow = (Button) findViewById(R.id.btn_download_downloadNow);
        btn_downloadNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDownload(jsonCategory());
            }
        });
        tv_download_1 = (TextView) findViewById(R.id.tv_download_1);
        lv_categories = (ListView) findViewById(R.id.lv_categories);
        lv_categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(TAG, "Item clicked:\nPosition: " + String.valueOf(position) + " id: " + String.valueOf(id) );
                // TODO delete Debug entries:
                TextView tv_category = (TextView) view.findViewById(R.id.cat_name);
                Log.v(TAG, "Cat name: " + tv_category.getText());
                // ... end debug entries
                doDownload(jsonCards());
            }
        });
    }

    @Override
    protected void onResume()  {
        super.onResume();
        fillListView();

    }

    private void fillListView()  {
        DBHelperClass db = new DBHelperClass(this);
        Cursor cursor = db.getCategorys();
        CursorAdapter categoryCursorAdapter = new CategoryCursorAdapter(this,cursor);
        lv_categories.setAdapter(categoryCursorAdapter);
    }

    private void doDownload(JSONObject jsonObject) {
        DownloadQuery dq;

        try {
            dq = new DownloadQuery(this, "POST", SERVER_ROOT_URL + SERVER_DIRECT_ORDER_EXTENSION, API_KEY_DIRECTORDER, this);
            dq.execute(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnRequestExecuted(String result) {
        try {
            JSONObject jObj = new JSONObject(result);
            insertJsonToDb(jObj);
            tv_download_1.setText(result);
            fillListView();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void insertJsonToDb(JSONObject jsonObject) {
        DBHelperClass dbHelperClass = new DBHelperClass(this);
        dbHelperClass.insertIntoFromJson(jsonObject);
        dbHelperClass.closeDB();
    }

    private JSONObject jsonCategory() {
        JsonObjectsForDownload jofd = new JsonObjectsForDownload();
        JSONObject jsonObject = jofd.getJsonForCategory();
        return jsonObject;
    }

    private JSONObject jsonCards()  {
        JsonObjectsForDownload jofd = new JsonObjectsForDownload();
        JSONObject jsonObject = jofd.getJsonForCards();
        return jsonObject;
    }
}

// Test

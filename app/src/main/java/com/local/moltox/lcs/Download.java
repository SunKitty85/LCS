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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import misc.helper.DownloadQuery;
import misc.helper.JsonObjectsForDownload;
import sqlite.helper.DBHelperClass;

public class Download extends AppCompatActivity implements
        DownloadQuery.OnRequestExecutedListener {
    private static final String TAG = Download.class.getName();
    private static final String SERVER_ROOT_URL = "http://5.9.67.156/lcs/";
    private static final String SERVER_DIRECT_ORDER_EXTENSION = "query.php";
    private static final String API_KEY_DIRECTORDER = "u23923u5r823894n23z34fz8hhdsbvahuishwe8278";
    TextView tv_download_1;
    Button btn_downloadNow;

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
                doDownload(v, jsonCategory());
            }
        });
        tv_download_1 = (TextView) findViewById(R.id.tv_download_1);
    }


    private void doDownload(View v, JSONObject jsonObject) {
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

            // jObjDb = jObj.getJSONObject("out_JSON");
            /*

            */
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
}

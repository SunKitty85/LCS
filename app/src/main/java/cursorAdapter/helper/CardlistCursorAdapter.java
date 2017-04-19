package cursorAdapter.helper;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.local.moltox.lcs.R;

import org.w3c.dom.Text;

import sqlite.helper.DBHelperClass;

/**
 * Created by moltox on 17.04.2017.
 */

public class CardlistCursorAdapter extends CursorAdapter {
    private static final String TAG = CardlistCursorAdapter.class.getName();

    public CardlistCursorAdapter(Context context, Cursor cursor)  {
        super(context,cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.lv_item_cards, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv_card_id = (TextView) view.findViewById(R.id.tv_card_id);

        TextView tv_card_question = (TextView) view.findViewById(R.id.tv_card_question);
        TextView tv_card_answer01 = (TextView) view.findViewById(R.id.tv_card_answer01);

        tv_card_id.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelperClass.COL_COMMON_ID)));
        tv_card_question.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelperClass.COL_CARDS_QUESTION)));
        tv_card_answer01.setText(cursor.getString(cursor.getColumnIndexOrThrow(DBHelperClass.COL_CARDS_ANSWER01)));
        Log.v(TAG, "ShowCards bindView");
    }
}

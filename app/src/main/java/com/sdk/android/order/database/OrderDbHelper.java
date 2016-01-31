package com.sdk.android.order.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.sdk.android.order.R;
import com.sdk.util.database.JsonToQuery;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by kevinadesara on 19/01/16.
 */
public class OrderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "order.db";
    public static final String TAG = OrderDbHelper.class.getSimpleName();

    private JsonToQuery j2q;

    public OrderDbHelper(Context context) throws IOException, JSONException {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        j2q = new JsonToQuery(context.getResources(), R.raw.table_definition);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "creating Order db...");
        try {
            db.execSQL(j2q.getCreateTablesQuery());
            Log.d(TAG, "Order db created.");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade Order db");
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        try {
            db.execSQL(j2q.getDeleteTablesQuery());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onDowngrade Order db");
        onUpgrade(db, oldVersion, newVersion);
    }

    public ArrayList<Long> insert(String table, ArrayList<ContentValues> records) {
        if (records == null || records.isEmpty())
            return null;

        ArrayList<Long> ids = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        String sql = QueryUtil.prepareInsertQuery(table, records.get(0), true, true);
        SQLiteStatement insert = db.compileStatement(sql);
        for (ContentValues record : records) {
            Set<Map.Entry<String, Object>> s = record.valueSet();
            Iterator itr = s.iterator();
            Log.d("DatabaseSync", "ContentValue Length :: " + record.size());
            int idx = 0;
            while (itr.hasNext()) {
                Map.Entry me = (Map.Entry) itr.next();
                String key = me.getKey().toString();
                String value = (String) me.getValue();
                insert.bindString(idx++, value);
                Log.d("DatabaseSync", "Key:" + key + ", values:" + (value == null ? null : value));
            }
            long id = insert.executeInsert();
            ids.add(id);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return ids;
    }

}

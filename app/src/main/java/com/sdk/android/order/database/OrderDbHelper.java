package com.sdk.android.order.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sdk.android.order.R;
import com.sdk.util.database.JsonToQuery;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by kevinadesara on 19/01/16.
 */
public class OrderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "order.db";

    private JsonToQuery j2q;

    public OrderDbHelper(Context context) throws IOException, JSONException {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        j2q = new JsonToQuery(context.getResources(), R.raw.table_definition);
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(j2q.getCreateTablesQuery());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
        onUpgrade(db, oldVersion, newVersion);
    }

}

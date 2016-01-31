package com.sdk.android.order.database;

import android.content.ContentValues;
import android.util.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by kevinadesara on 31/01/16.
 */
class QueryUtil {
    public static String prepareInsertQuery(String tableName, ContentValues record, boolean insertOrReplace, boolean compiledQuery) {
        StringBuilder sbMain = new StringBuilder("INSERT");
        StringBuilder sbColumns = new StringBuilder();
        StringBuilder sbValues = new StringBuilder();

        if (insertOrReplace == true)
            sbMain.append(" OR REPLACE");
        sbMain.append(" INTO ").append(tableName);

        sbColumns.append("(");
        sbValues.append(" VALUES(");

        Set<Map.Entry<String, Object>> s = record.valueSet();
        Iterator itr = s.iterator();
        Log.d("DatabaseSync", "ContentValue Length :: " + record.size());
        while (itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            String key = me.getKey().toString();
            sbColumns.append(key).append(",");

            Object value = me.getValue();
            sbValues.append(compiledQuery ? "?" : value).append(",");

            Log.d("DatabaseSync", "Key:" + key + ", values:" + (value == null ? null : value.toString()));
        }

        sbColumns.replace(sbColumns.length() - 1, sbColumns.length(), "");
        sbValues.replace(sbValues.length() - 1, sbValues.length(), "");

        sbValues.append(")");
        sbColumns.append(")");

        sbMain.append(sbColumns.toString());
        sbMain.append(sbValues.toString());
        return sbMain.toString();
    }
}

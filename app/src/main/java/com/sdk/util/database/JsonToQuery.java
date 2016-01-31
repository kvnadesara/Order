package com.sdk.util.database;

import android.content.res.Resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kevinadesara on 19/01/16.
 */
public class JsonToQuery {
    private Resources mRes;
    private int mJsonFileResId;
    private JSONObject mTableDef;

    public JsonToQuery(Resources res, int jsonFileResId) throws IOException, JSONException {
        mRes = res;
        mJsonFileResId = jsonFileResId;
        mTableDef = readJsonFile(mRes, mJsonFileResId);
    }

    public JsonToQuery(JSONObject tableDef) {
        mTableDef = tableDef;
    }

    public String getCreateTablesQuery() throws IOException, JSONException {
        JSONArray tables = mTableDef.getJSONArray("tables");
        return prepareCreateTableScript(tables);
    }

    public String getDeleteTablesQuery() throws JSONException {
        JSONArray tables = mTableDef.getJSONArray("tables");
        return prepareDeleteTableScript(tables);
    }

    private JSONObject readJsonFile(Resources res, int jsonFileResId) throws IOException, JSONException {
        InputStream in_s = res.openRawResource(jsonFileResId);
        byte[] b = new byte[in_s.available()];
        in_s.read(b);
        String strJson = new String(b);
        return new JSONObject(strJson);
    }

    private String prepareCreateTableScript(JSONArray tables) throws JSONException {
        StringBuilder sb = new StringBuilder();
        int len = tables.length();
        for (int i = 0; i < len; i++) {
            JSONObject table = tables.getJSONObject(i);
            String tableName = table.getString("name");
            JSONArray columns = table.getJSONArray("columns");
            sb.append("DROP TABLE IF EXISTS ").append(tableName).append(";\n");
            sb.append("CREATE TABLE ").append(tableName).append(" (")
                    .append(columnsToScript(columns))
                    .append(" );\n");

        }
        return sb.toString();
    }

    private String prepareDeleteTableScript(JSONArray tables) throws JSONException {
        StringBuilder sb = new StringBuilder();
        int len = tables.length();
        for (int i = 0; i < len; i++) {
            JSONObject table = tables.getJSONObject(i);
            String tableName = table.getString("name");
            sb.append("DROP TABLE IF EXISTS ").append(tableName).append(";\n");
        }
        return sb.toString();
    }

    private String columnsToScript(JSONArray columns) throws JSONException {
        StringBuilder sb = new StringBuilder();
        int len = columns.length();
        for (int i = 0; i < len; i++) {
            JSONObject column = columns.getJSONObject(i);
            String columnName = column.getString("name");
            String datatype = column.getString("datatype");
            sb.append(columnName).append(" ").append(datatype);
            if (i < len - 1)
                sb.append(",");
        }
        return sb.toString();
    }
}

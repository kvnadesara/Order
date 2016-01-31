package com.sdk.android.order.database.dao;

import android.content.ContentValues;

import com.sdk.android.order.database.OrderContract;
import com.sdk.android.order.database.OrderDbHelper;
import com.sdk.android.order.model.Item;

import java.util.ArrayList;

/**
 * Created by kevinadesara on 31/01/16.
 */
public class ItemMasterDao {
    private OrderDbHelper mDbHelper;

    public ItemMasterDao(OrderDbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    public void save(Item item) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        save(items);
    }
    public void save(ArrayList<Item> items) {
        ArrayList<ContentValues> records = new ArrayList<>();
        for(Item item : items) {
            ContentValues record = new ContentValues();
            record.put(OrderContract.ItemEntry.C_ITEM_NAME, item.getName());
            records.add(record);
        }
        mDbHelper.insert(OrderContract.ItemEntry.TABLE_NAME, records);
    }
}

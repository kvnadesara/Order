package com.sdk.android.order.model;

import android.database.Cursor;

import com.sdk.android.order.database.OrderContract;

/**
 * Created by kevinadesara on 31/01/16.
 */
public class Item {
    private int pkId;
    private String name;

    public static Item fromCursor(Cursor c) {
        int pkId = c.getInt(c.getColumnIndexOrThrow(OrderContract.ItemEntry.C_PK_ID));
        String name = c.getString(c.getColumnIndexOrThrow(OrderContract.ItemEntry.C_ITEM_NAME));
        return new Item(pkId, name);
    }

    private Item() {
        this(0, "");
    }

    public Item(String name) {
        this(0, name);
    }

    public Item(int pkId, String name) {
        this.pkId = pkId;
        this.name = name;
    }

    public int getPkId() {
        return pkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item(" + getName() + ")";
    }
}

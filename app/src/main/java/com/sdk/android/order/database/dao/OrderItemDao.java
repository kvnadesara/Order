package com.sdk.android.order.database.dao;

import android.content.ContentValues;

import com.sdk.android.order.database.OrderDbHelper;

import java.util.ArrayList;

/**
 * Created by kevinadesara on 20/01/16.
 */
class OrderItemDao {
    private OrderDbHelper mDbHelper;

    public OrderItemDao(OrderDbHelper dbHelper) {
        mDbHelper = dbHelper;
    }

    public void save(ArrayList<ContentValues> orderItems) {
        
    }
}

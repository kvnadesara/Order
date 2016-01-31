package com.sdk.android.order.database.contract;

import android.provider.BaseColumns;

/**
 * Created by kevinadesara on 19/01/16.
 */
public class OrderItemContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public OrderItemContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class OrderItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "orderitems";
        public static final String C_PK_ID = "pk_id";
        public static final String C_ORDER_FK_ID = "orderid";
        public static final String C_ITEM_NAME = "itemname";
        public static final String C_ITEM_QTY = "qty";
    }
}

package com.sdk.android.order.database.contract;

import android.provider.BaseColumns;

/**
 * Created by kevinadesara on 19/01/16.
 */
public final class OrderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public OrderContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class OrderEntry implements BaseColumns {
        public static final String TABLE_NAME = "order";
        public static final String C_PK_ID = "pk_id";
        public static final String C_ORDER_NAME = "ordername";
        public static final String C_ORDER_DATE = "orderdate";
    }
}
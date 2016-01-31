package com.sdk.android.order.database;

import android.provider.BaseColumns;

/**
 * Created by kevinadesara on 31/01/16.
 */
public final class OrderContract {
    public OrderContract() {}

    public static abstract class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "itemmaster";
        public static final String C_PK_ID = "pk_id";
        public static final String C_ITEM_NAME = "itemname";
    }

    public static abstract class OrderEntry implements BaseColumns {
        public static final String TABLE_NAME = "order";
        public static final String C_PK_ID = "pk_id";
        public static final String C_ORDER_NAME = "ordername";
        public static final String C_ORDER_DATE = "orderdate";
    }

    public static abstract class OrderItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "orderitems";
        public static final String C_PK_ID = "pk_id";
        public static final String C_FK_ORDER_ID = "fk_order_id";
        public static final String C_ITEM_NAME = "itemname";
        public static final String C_ITEM_QTY = "qty";
    }
}

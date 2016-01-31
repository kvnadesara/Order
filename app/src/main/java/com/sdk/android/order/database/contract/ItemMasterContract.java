package com.sdk.android.order.database.contract;

import android.provider.BaseColumns;

/**
 * Created by kevinadesara on 19/01/16.
 */
public class ItemMasterContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ItemMasterContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "itemmaster";
        public static final String C_PK_ID = "pk_id";
        public static final String C_ITEM_NAME = "itemname";
    }
}

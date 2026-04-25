package com.micael.collecta.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "collecta.db"
        private const val DATABASE_VERSION = 1

        // Tabela UserData
        const val TABLE_USER = "UserData"
        const val COLUMN_USER_NAME = "username"
        const val COLUMN_COLLECTOR_FULLNAME = "collectorsfullname"
        const val COLUMN_BRAND_NAME = "brandsname"
        const val COLUMN_BRAND_LOGO = "brandslogo"
        const val COLUMN_PHONE = "phonenumber"

        // Tabela Collections
        const val TABLE_COLLECTIONS = "Collections"
        const val COLUMN_ID = "id"
        const val COLUMN_CUST_NAME = "name"
        const val COLUMN_DATE = "date"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_CUST_PHONE = "phonenumber"
        const val COLUMN_PRODUCTS_LIST = "productslist"
        const val COLUMN_PAID = "paid" // "Sim" ou "Não"
        const val COLUMN_COLLECTION_DAY = "collectionday"
        const val COLUMN_TOTAL = "total"
        const val COLUMN_LEFT = "left"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUserTable = ("CREATE TABLE $TABLE_USER (" +
                "$COLUMN_USER_NAME TEXT," +
                "$COLUMN_COLLECTOR_FULLNAME TEXT," +
                "$COLUMN_BRAND_NAME TEXT," +
                "$COLUMN_BRAND_LOGO BLOB," +
                "$COLUMN_PHONE TEXT)")

        val createCollectionsTable = ("CREATE TABLE $TABLE_COLLECTIONS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CUST_NAME TEXT," +
                "$COLUMN_DATE TEXT," +
                "$COLUMN_ADDRESS TEXT," +
                "$COLUMN_CUST_PHONE TEXT," +
                "$COLUMN_PRODUCTS_LIST TEXT," +
                "$COLUMN_PAID TEXT," +
                "$COLUMN_COLLECTION_DAY TEXT," +
                "$COLUMN_TOTAL REAL," +
                "$COLUMN_LEFT REAL)")

        db.execSQL(createUserTable)
        db.execSQL(createCollectionsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_COLLECTIONS")
        onCreate(db)
    }
}

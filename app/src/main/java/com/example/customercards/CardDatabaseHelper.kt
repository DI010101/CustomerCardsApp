package com.example.customercards

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CardDatabaseHelper(context: Context) : 
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "customer_cards.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_CARDS = "cards"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_CODE = "code"
        private const val COLUMN_CODE_TYPE = "code_type"
        private const val COLUMN_CREATED_AT = "created_at"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_CARDS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_CODE TEXT NOT NULL,
                $COLUMN_CODE_TYPE TEXT NOT NULL,
                $COLUMN_CREATED_AT INTEGER NOT NULL
            )
        """.trimIndent()
        
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CARDS")
        onCreate(db)
    }

    fun insertCard(card: CustomerCard): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, card.name)
            put(COLUMN_CODE, card.code)
            put(COLUMN_CODE_TYPE, card.codeType)
            put(COLUMN_CREATED_AT, card.createdAt)
        }
        
        return db.insert(TABLE_CARDS, null, values)
    }

    fun getAllCards(): List<CustomerCard> {
        val cards = mutableListOf<CustomerCard>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_CARDS,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_CREATED_AT DESC"
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(COLUMN_NAME))
                val code = getString(getColumnIndexOrThrow(COLUMN_CODE))
                val codeType = getString(getColumnIndexOrThrow(COLUMN_CODE_TYPE))
                val createdAt = getLong(getColumnIndexOrThrow(COLUMN_CREATED_AT))
                
                cards.add(CustomerCard(id, name, code, codeType, createdAt))
            }
        }
        cursor.close()
        
        return cards
    }

    fun getCard(id: Long): CustomerCard? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_CARDS,
            null,
            "$COLUMN_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        var card: CustomerCard? = null
        
        if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val code = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CODE))
            val codeType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CODE_TYPE))
            val createdAt = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT))
            
            card = CustomerCard(id, name, code, codeType, createdAt)
        }
        
        cursor.close()
        return card
    }

    fun deleteCard(id: Long): Int {
        val db = writableDatabase
        return db.delete(TABLE_CARDS, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun updateCard(card: CustomerCard): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, card.name)
            put(COLUMN_CODE, card.code)
            put(COLUMN_CODE_TYPE, card.codeType)
        }
        
        return db.update(TABLE_CARDS, values, "$COLUMN_ID = ?", arrayOf(card.id.toString()))
    }
}

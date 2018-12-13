package com.example.vitorizkiimanda.footballschedulevri.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.EVENT_ID to TEXT + UNIQUE,
                FavoriteMatch.EVENT_DATE to TEXT,
                FavoriteMatch.MATCH_HOME_ID to TEXT,
                FavoriteMatch.MATCH_HOME_NAME to TEXT,
                FavoriteMatch.MATCH_HOME_SCORE to TEXT,
                FavoriteMatch.MATCH_HOME_GOALS to TEXT,
                FavoriteMatch.MATCH_HOME_SHOTS to TEXT,
                FavoriteMatch.MATCH_HOME_KEEPER to TEXT,
                FavoriteMatch.MATCH_HOME_DEFENSE to TEXT,
                FavoriteMatch.MATCH_HOME_MIDFIELD to TEXT,
                FavoriteMatch.MATCH_HOME_FORWARD to TEXT,
                FavoriteMatch.MATCH_HOME_SUBS to TEXT,
                FavoriteMatch.MATCH_AWAY_ID to TEXT,
                FavoriteMatch.MATCH_AWAY_NAME to TEXT,
                FavoriteMatch.MATCH_AWAY_SCORE to TEXT,
                FavoriteMatch.MATCH_AWAY_GOALS to TEXT,
                FavoriteMatch.MATCH_AWAY_SHOTS to TEXT,
                FavoriteMatch.MATCH_AWAY_KEEPER to TEXT,
                FavoriteMatch.MATCH_AWAY_DEFENSE to TEXT,
                FavoriteMatch.MATCH_AWAY_MIDFIELD to TEXT,
                FavoriteMatch.MATCH_AWAY_FORWARD to TEXT,
                FavoriteMatch.MATCH_AWAY_SUBS to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
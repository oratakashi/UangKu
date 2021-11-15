package com.oratakashi.uangku.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oratakashi.uangku.data.db.dao.CategoryDao
import com.oratakashi.uangku.data.db.dao.TransactionDao
import com.oratakashi.uangku.data.model.CategoryEntity
import com.oratakashi.uangku.data.model.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UangkuDatabase : RoomDatabase() {

    abstract fun category(): CategoryDao
    abstract fun transaction(): TransactionDao

    companion object {

        private var INSTANCE: UangkuDatabase? = null

        fun getAppDatabase(context: Context): UangkuDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context,
                        UangkuDatabase::class.java,
                        "UangkuDatabase.db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
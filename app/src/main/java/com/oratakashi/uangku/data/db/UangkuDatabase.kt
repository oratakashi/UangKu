package com.oratakashi.uangku.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oratakashi.uangku.data.db.dao.CategoryDao
import com.oratakashi.uangku.data.model.CategoryEntity

@Database(
    entities = [
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UangkuDatabase : RoomDatabase() {

    abstract fun category(): CategoryDao

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
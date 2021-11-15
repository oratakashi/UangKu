package com.oratakashi.uangku.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.oratakashi.uangku.data.model.TransactionEntity
import io.reactivex.Single

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data: TransactionEntity): Single<Long>
}
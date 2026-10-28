package edu.vt.mobiledev.aacapplication.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.SentenceEntity

@Dao
interface SentenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSentence(sentence: SentenceEntity)

    @Query("SELECT * FROM sentences")
    suspend fun getAllSentences(): List<SentenceEntity>
}


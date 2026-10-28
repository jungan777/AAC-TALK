package edu.vt.mobiledev.aacapplication.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.WordEntity

@Dao
interface WordDao {
    @Query("SELECT * FROM words WHERE categoryId = :categoryId")
    suspend fun getWordsByCategoryAndType(categoryId: Int): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>)

    //개별 단어
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity)

}

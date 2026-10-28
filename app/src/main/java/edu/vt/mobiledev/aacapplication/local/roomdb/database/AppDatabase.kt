package edu.vt.mobiledev.aacapplication.local.roomdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.CategoryDao
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.SentenceDao
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.WordDao
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.CategoryEntity
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.SentenceEntity
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.WordEntity

@Database(entities = [CategoryEntity::class, WordEntity::class, SentenceEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun categoryDao(): CategoryDao
    abstract fun sentenceDao():SentenceDao
    // 필요 시 categoryDao, sentenceDao 도 추가
}



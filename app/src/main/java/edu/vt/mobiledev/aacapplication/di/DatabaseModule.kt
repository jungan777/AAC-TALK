package edu.vt.mobiledev.aacapplication.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.CategoryDao
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.SentenceDao
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.WordDao
import edu.vt.mobiledev.aacapplication.local.roomdb.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "aac-database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideWordDao(db: AppDatabase): WordDao {
        return db.wordDao()
    }
    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao {
        return db.categoryDao()
    }

    @Provides
    fun provideSentenceDao(db: AppDatabase): SentenceDao {
        return db.sentenceDao()
    }

}

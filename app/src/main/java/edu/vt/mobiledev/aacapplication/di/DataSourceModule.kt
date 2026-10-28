
package edu.vt.mobiledev.aacapplication.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import edu.vt.mobiledev.aacapplication.data.datasource.SentenceDataSource
import edu.vt.mobiledev.aacapplication.data.datasource.CategoryDataSource
import edu.vt.mobiledev.aacapplication.data.datasource.WordDataSource
import edu.vt.mobiledev.aacapplication.data.datasource.AiRemoteDataSource
import edu.vt.mobiledev.aacapplication.data.impl.SentenceLocalDataSourceImpl
import edu.vt.mobiledev.aacapplication.local.dataSourceImpl.CategoryLocalDataSourceImpl
import edu.vt.mobiledev.aacapplication.local.dataSourceImpl.WordLocalDataSourceImpl

import edu.vt.mobiledev.aacapplication.remote.AiRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindSentenceDataSource(
        impl: SentenceLocalDataSourceImpl
    ): SentenceDataSource

    @Binds
    abstract fun bindCategoryDataSource(
        impl: CategoryLocalDataSourceImpl
    ): CategoryDataSource

    @Binds
    abstract fun bindWordDataSource(
        impl: WordLocalDataSourceImpl
    ): WordDataSource

    @Binds
    abstract fun bindAiRemoteDataSource(
        impl: AiRemoteDataSourceImpl
    ): AiRemoteDataSource
}

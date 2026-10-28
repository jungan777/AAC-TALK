package edu.vt.mobiledev.aacapplication.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.vt.mobiledev.aacapplication.data.impl.AiRepositoryImpl
import edu.vt.mobiledev.aacapplication.data.impl.CategoryRepositoryImpl
import edu.vt.mobiledev.aacapplication.data.impl.SentenceRepositoryImpl
import edu.vt.mobiledev.aacapplication.data.impl.WordRepositoryImpl
import edu.vt.mobiledev.aacapplication.domain.repository.AiRepository
import edu.vt.mobiledev.aacapplication.domain.repository.CategoryRepository
import edu.vt.mobiledev.aacapplication.domain.repository.SentenceRepository
import edu.vt.mobiledev.aacapplication.domain.repository.WordRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun bindAiRepository(
        impl: AiRepositoryImpl
    ): AiRepository

    @Binds
    abstract fun bindSentenceRepository(
        impl: SentenceRepositoryImpl
    ): SentenceRepository

    @Binds
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    abstract fun bindWordRepository(
        impl: WordRepositoryImpl
    ): WordRepository

}

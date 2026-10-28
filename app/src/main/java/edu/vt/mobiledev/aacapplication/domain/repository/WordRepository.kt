package edu.vt.mobiledev.aacapplication.domain.repository

import edu.vt.mobiledev.aacapplication.domain.model.Word

interface WordRepository {
    suspend fun getWordsByCategoryAndType(
        categoryId: Int
    ): List<Word>

    suspend fun insertWords(words: List<Word>)
    suspend fun insertWord(word: Word)
}
package edu.vt.mobiledev.aacapplication.data.datasource

import edu.vt.mobiledev.aacapplication.data.model.WordDataModel

interface WordDataSource {
    suspend fun getWordsByCategoryAndType(categoryId: Int): List<WordDataModel>
    suspend fun insertWords(words: List<WordDataModel>)
    suspend fun insertWord(word: WordDataModel)
}
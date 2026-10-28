package edu.vt.mobiledev.aacapplication.local.dataSourceImpl

import edu.vt.mobiledev.aacapplication.data.datasource.WordDataSource
import edu.vt.mobiledev.aacapplication.data.mapper.toDataModel
import edu.vt.mobiledev.aacapplication.data.mapper.toEntity
import edu.vt.mobiledev.aacapplication.data.model.WordDataModel
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.WordDao
import javax.inject.Inject
import kotlin.collections.map

class WordLocalDataSourceImpl @Inject constructor(
    private val wordDao: WordDao
) : WordDataSource {

    override suspend fun getWordsByCategoryAndType(
        categoryId: Int
    ): List<WordDataModel> {
        return wordDao.getWordsByCategoryAndType(categoryId).map {
            it.toDataModel()
        }
    }

    override suspend fun insertWords(words: List<WordDataModel>) {
        wordDao.insertWords(words.map { it.toEntity() })
    }

    override suspend fun insertWord(word: WordDataModel) {
        wordDao.insertWord(word.toEntity())
    }
}
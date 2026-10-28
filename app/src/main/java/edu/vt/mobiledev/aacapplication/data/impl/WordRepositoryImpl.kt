
package edu.vt.mobiledev.aacapplication.data.impl

import edu.vt.mobiledev.aacapplication.data.datasource.WordDataSource
import edu.vt.mobiledev.aacapplication.data.mapper.toDataModel
import edu.vt.mobiledev.aacapplication.data.mapper.toDomain
import edu.vt.mobiledev.aacapplication.domain.model.Word
import edu.vt.mobiledev.aacapplication.domain.repository.WordRepository
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val dataSource: WordDataSource
) : WordRepository {

    override suspend fun getWordsByCategoryAndType(
        categoryId: Int
    ): List<Word> {
        return dataSource
            .getWordsByCategoryAndType(categoryId)
            .map { it.toDomain() }
    }

    override suspend fun insertWords(words: List<Word>) {
        dataSource.insertWords(
            words.map { it.toDataModel() }
        )
    }

    override suspend fun insertWord(word: Word) {
        dataSource.insertWord(word.toDataModel())
    }
}

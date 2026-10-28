package edu.vt.mobiledev.aacapplication.data.impl

import edu.vt.mobiledev.aacapplication.data.datasource.SentenceDataSource
import edu.vt.mobiledev.aacapplication.data.mapper.toDataModel
import edu.vt.mobiledev.aacapplication.data.mapper.toDomain
import edu.vt.mobiledev.aacapplication.domain.model.Sentence
import edu.vt.mobiledev.aacapplication.domain.repository.SentenceRepository
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.SentenceDao
import javax.inject.Inject

class SentenceRepositoryImpl @Inject constructor(
    private val dataSource: SentenceDataSource
) : SentenceRepository {

    override suspend fun insertSentence(sentence: Sentence) {
        dataSource.insertSentence(sentence.toDataModel())
    }

    override suspend fun getAllSentences(): List<Sentence> {
        return dataSource.getAllSentences().map {
            it.toDomain()
        }
    }
}
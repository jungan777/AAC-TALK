package edu.vt.mobiledev.aacapplication.data.impl

import edu.vt.mobiledev.aacapplication.data.datasource.SentenceDataSource
import edu.vt.mobiledev.aacapplication.data.mapper.toDataModel
import edu.vt.mobiledev.aacapplication.data.mapper.toEntity
import edu.vt.mobiledev.aacapplication.data.model.SentenceDataModel
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.SentenceDao
import javax.inject.Inject

class SentenceLocalDataSourceImpl @Inject constructor(
    private val sentenceDao: SentenceDao
) : SentenceDataSource {

    override suspend fun insertSentence(sentence: SentenceDataModel) {
        sentenceDao.insertSentence(sentence.toEntity())
    }

    override suspend fun getAllSentences(): List<SentenceDataModel> {
        return sentenceDao.getAllSentences().map {
            it.toDataModel()
        }
    }
}
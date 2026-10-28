package edu.vt.mobiledev.aacapplication.data.datasource

import edu.vt.mobiledev.aacapplication.data.model.SentenceDataModel

interface SentenceDataSource {

    suspend fun insertSentence(sentence: SentenceDataModel)

    suspend fun getAllSentences(): List<SentenceDataModel>
}
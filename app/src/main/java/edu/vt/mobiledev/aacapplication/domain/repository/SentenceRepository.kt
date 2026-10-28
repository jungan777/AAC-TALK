package edu.vt.mobiledev.aacapplication.domain.repository

import edu.vt.mobiledev.aacapplication.domain.model.Sentence

interface SentenceRepository {
    suspend fun insertSentence(sentence: Sentence)
    suspend fun getAllSentences(): List<Sentence>
}
package edu.vt.mobiledev.aacapplication.domain.usecase

import edu.vt.mobiledev.aacapplication.domain.model.Sentence
import edu.vt.mobiledev.aacapplication.domain.repository.SentenceRepository
import javax.inject.Inject

class InsertSentenceUseCase @Inject constructor(
    private val repository: SentenceRepository
) {
    suspend operator fun invoke(sentence: Sentence) {
        repository.insertSentence(sentence)
    }
}
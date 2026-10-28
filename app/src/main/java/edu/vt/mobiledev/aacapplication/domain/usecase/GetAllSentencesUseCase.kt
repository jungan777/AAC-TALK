package edu.vt.mobiledev.aacapplication.domain.usecase

import edu.vt.mobiledev.aacapplication.domain.model.Sentence
import edu.vt.mobiledev.aacapplication.domain.repository.SentenceRepository
import javax.inject.Inject

class GetAllSentencesUseCase @Inject constructor(
    private val repository: SentenceRepository
) {
    suspend operator fun invoke(): List<Sentence> {
        return repository.getAllSentences()
    }
}
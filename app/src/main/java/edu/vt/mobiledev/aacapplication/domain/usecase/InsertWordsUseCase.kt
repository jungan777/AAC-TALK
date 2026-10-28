package edu.vt.mobiledev.aacapplication.domain.usecase

import edu.vt.mobiledev.aacapplication.domain.model.Word
import edu.vt.mobiledev.aacapplication.domain.repository.WordRepository
import javax.inject.Inject

class InsertWordsUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke(words: List<Word>) {
        repository.insertWords(words)
    }
}
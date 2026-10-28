package edu.vt.mobiledev.aacapplication.domain.usecase

import edu.vt.mobiledev.aacapplication.domain.model.Word
import edu.vt.mobiledev.aacapplication.domain.repository.WordRepository
import javax.inject.Inject

class GetWordsByCategoryAndTypeUseCase @Inject constructor(
    private val repository: WordRepository
) {
    suspend operator fun invoke(categoryId: Int): List<Word> {
        return repository.getWordsByCategoryAndType(categoryId)
    }
}
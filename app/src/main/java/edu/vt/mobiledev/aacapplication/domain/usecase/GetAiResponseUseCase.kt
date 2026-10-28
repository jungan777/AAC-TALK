package edu.vt.mobiledev.aacapplication.domain.usecase

import edu.vt.mobiledev.aacapplication.domain.repository.AiRepository
import javax.inject.Inject

class GetAiResponseUseCase @Inject constructor(
    private val repository: AiRepository
) {
    suspend operator fun invoke(prompt: String): String {
        require(prompt.isNotBlank()) {
            "Prompt cannot be blank"
        }

        return repository.getAiResponse(prompt)
    }
}
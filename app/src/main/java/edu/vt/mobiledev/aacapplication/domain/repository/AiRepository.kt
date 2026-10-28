package edu.vt.mobiledev.aacapplication.domain.repository

interface AiRepository {
    suspend fun getAiResponse(prompt: String): String
}
package edu.vt.mobiledev.aacapplication.data.datasource

interface AiRemoteDataSource {
    suspend fun generateResponse(prompt: String): String
}
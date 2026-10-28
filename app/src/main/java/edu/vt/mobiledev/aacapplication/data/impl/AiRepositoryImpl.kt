package edu.vt.mobiledev.aacapplication.data.impl

import edu.vt.mobiledev.aacapplication.data.datasource.AiRemoteDataSource
import edu.vt.mobiledev.aacapplication.domain.repository.AiRepository
import javax.inject.Inject

class AiRepositoryImpl @Inject constructor(
    private val dataSource: AiRemoteDataSource
) : AiRepository {

    override suspend fun getAiResponse(prompt: String): String {
        return dataSource.generateResponse(prompt)
    }
}
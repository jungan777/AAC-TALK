package edu.vt.mobiledev.aacapplication.remote

import edu.vt.mobiledev.aacapplication.data.FirebaseAiProvider
import edu.vt.mobiledev.aacapplication.data.datasource.AiRemoteDataSource
import javax.inject.Inject

class AiRemoteDataSourceImpl @Inject constructor(
    private val firebaseAiProvider: FirebaseAiProvider
) : AiRemoteDataSource {

    override suspend fun generateResponse(prompt: String): String {
        val model = firebaseAiProvider.getModel()

        val response = model.generateContent(prompt)

        return response.text.orEmpty()
    }
}
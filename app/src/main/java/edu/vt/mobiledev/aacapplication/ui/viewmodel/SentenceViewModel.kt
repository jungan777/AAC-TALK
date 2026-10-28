
package edu.vt.mobiledev.aacapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.vt.mobiledev.aacapplication.domain.model.Sentence
import edu.vt.mobiledev.aacapplication.domain.usecase.InsertSentenceUseCase
import edu.vt.mobiledev.aacapplication.domain.usecase.GetAllSentencesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SentenceViewModel @Inject constructor(
    private val insertSentenceUseCase: InsertSentenceUseCase,
    private val getAllSentencesUseCase: GetAllSentencesUseCase
) : ViewModel() {

    private val _sentences = MutableStateFlow<List<Sentence>>(emptyList())
    val sentences: StateFlow<List<Sentence>> = _sentences.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    // 완성한 문장을 DB에 저장
    fun addSentence(sentenceText: String) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                insertSentenceUseCase(
                    Sentence(content = sentenceText)
                )

                // 저장 성공 후 목록 갱신
                _sentences.value = getAllSentencesUseCase()

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "문장 저장 중 오류가 발생했습니다."
            }
        }
    }

    // 저장된 모든 문장 조회
    fun getAllSentences(onResult: (List<Sentence>) -> Unit) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                val list = getAllSentencesUseCase()

                _sentences.value = list
                onResult(list)

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "문장 조회 중 오류가 발생했습니다."
            }
        }
    }
}


package edu.vt.mobiledev.aacapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.vt.mobiledev.aacapplication.domain.model.Word
import edu.vt.mobiledev.aacapplication.domain.usecase.GetWordsByCategoryAndTypeUseCase
import edu.vt.mobiledev.aacapplication.domain.usecase.InsertWordUseCase
import edu.vt.mobiledev.aacapplication.domain.usecase.InsertWordsUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val getWordsByCategoryAndTypeUseCase: GetWordsByCategoryAndTypeUseCase,
    private val insertWordUseCase: InsertWordUseCase,
    private val insertWordsUseCase: InsertWordsUseCase
) : ViewModel() {

    private val categoryStack = mutableListOf<Int>()

    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words: StateFlow<List<Word>> = _words.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    // 카테고리별 단어 조회
    fun loadWordsByCategoryAndType(categoryId: Int) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                _words.value =
                    getWordsByCategoryAndTypeUseCase(categoryId)

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "단어 조회 중 오류가 발생했습니다."
            }
        }
    }

    // 단일 단어 저장
    fun insertWord(word: Word) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                insertWordUseCase(word)

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "단어 저장 중 오류가 발생했습니다."
            }
        }
    }

    // 여러 단어 저장 (미구햔)
    fun insertWords(words: List<Word>) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                insertWordsUseCase(words)

            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "단어 저장 중 오류가 발생했습니다."
            }
        }
    }
}

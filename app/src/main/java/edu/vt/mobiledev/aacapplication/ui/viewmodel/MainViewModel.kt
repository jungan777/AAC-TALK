
package edu.vt.mobiledev.aacapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.vt.mobiledev.aacapplication.domain.model.Word
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private var initialCategoryId: Int = 0

    // 현재 WordList 화면이 몇 번째인지
    private val _screenCount = MutableStateFlow(0)
    val screenCount: StateFlow<Int> = _screenCount.asStateFlow()

    // 현재 카테고리 ID
    private val _currentCategoryId = MutableStateFlow<Int?>(null)
    val currentCategoryId: StateFlow<Int?> =
        _currentCategoryId.asStateFlow()

    // 가장 최근에 선택한 단어
    private val _selectedWord = MutableStateFlow<Word?>(null)
    val selectedWord: StateFlow<Word?> =
        _selectedWord.asStateFlow()

    // 현재까지 선택한 전체 단어 목록
    private val _selectedWords =
        MutableStateFlow<List<Word>>(emptyList())

    val selectedWords: StateFlow<List<Word>> =
        _selectedWords.asStateFlow()

    /** 초기 카테고리 세팅 (Home에서 선택 시 호출) */
    fun setInitialCategory(categoryId: Int) {
        initialCategoryId = categoryId
        _screenCount.value = 0
        _currentCategoryId.value = categoryId
    }

    /** 화면 앞으로 이동 + 카테고리 업데이트 */
    fun nextScreen() {
        _screenCount.value += 1
        _currentCategoryId.value =
            initialCategoryId + _screenCount.value
    }

    /** 화면 뒤로 이동 + 카테고리 업데이트 */
    fun prevScreen() {
        _screenCount.value -= 1
        _currentCategoryId.value =
            initialCategoryId + _screenCount.value
    }

    /** 단어 선택 */
    fun selectWord(word: Word) {
        _selectedWord.value = word
        _selectedWords.value = _selectedWords.value + word
    }

    /** 가장 최근에 선택한 단어 1개 삭제 */
    fun removeLastSelectedWord() {
        _selectedWords.value =
            if (_selectedWords.value.isNotEmpty()) {
                _selectedWords.value.dropLast(1)
            } else {
                _selectedWords.value
            }
    }

    /** 선택된 단어 전체 삭제 */
    fun removeAllSelectedWords() {
        _selectedWords.value = emptyList()
    }

    /** 선택한 단어를 맨 앞에 추가 */
    fun addSelectedWord(word: Word) {
        _selectedWords.value =
            listOf(word) + _selectedWords.value
    }

    /** 현재 카테고리에 해당하는 마지막 단어 제거 */
    private fun removeLastIfMatchesCurrentCategory() {
        val categoryId = _currentCategoryId.value ?: return
        val currentList = _selectedWords.value

        if (
            currentList.isNotEmpty() &&
            currentList.last().categoryId == categoryId
        ) {
            _selectedWords.value = currentList.dropLast(1)
        }
    }

    /** 뒤로가기: 화면 이동 + 단어 제거(조건부) */
    fun goBack() {
        _screenCount.value -= 1

        _currentCategoryId.value =
            initialCategoryId + _screenCount.value

        val categoryId = _currentCategoryId.value ?: return
        val currentList = _selectedWords.value

        if (
            currentList.isNotEmpty() &&
            currentList.last().categoryId == categoryId
        ) {
            _selectedWords.value = currentList.dropLast(1)
        }
    }
}

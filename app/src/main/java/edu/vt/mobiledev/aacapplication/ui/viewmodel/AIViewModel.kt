
package edu.vt.mobiledev.aacapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.vt.mobiledev.aacapplication.domain.usecase.GetAiResponseUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiViewModel @Inject constructor(
    private val getAiResponseUseCase: GetAiResponseUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _aiResponse = MutableStateFlow("")
    val aiResponse: StateFlow<String> = _aiResponse.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun sendPrompt(prompt: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val result = getAiResponseUseCase(prompt)
                _aiResponse.value = result

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "AI 응답 생성 중 오류가 발생했습니다."

            } finally {
                _isLoading.value = false
            }
        }
    }
}

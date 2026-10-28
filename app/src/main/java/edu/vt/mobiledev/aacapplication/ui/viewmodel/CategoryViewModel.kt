
package edu.vt.mobiledev.aacapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.vt.mobiledev.aacapplication.domain.model.Category
import edu.vt.mobiledev.aacapplication.domain.usecase.GetAllCategoriesUseCase
import edu.vt.mobiledev.aacapplication.domain.usecase.InsertCategoryUseCase
import edu.vt.mobiledev.aacapplication.domain.usecase.InsertCategoriesUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val insertCategoriesUseCase: InsertCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun insertCategory(category: Category) {
        launchAction {
            insertCategoryUseCase(category)
            _categories.value = getAllCategoriesUseCase()
        }
    }

    fun insertCategories(categories: List<Category>) {
        launchAction {
            insertCategoriesUseCase(categories)
            _categories.value = getAllCategoriesUseCase()
        }
    }

    fun getAllCategories() {
        launchAction {
            _categories.value = getAllCategoriesUseCase()
        }
    }

    private fun launchAction(action: suspend () -> Unit) {
        viewModelScope.launch {
            _errorMessage.value = null

            try {
                action()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _errorMessage.value =
                    e.message ?: "카테고리 처리 중 오류가 발생했습니다."
            }
        }
    }
}

package edu.vt.mobiledev.aacapplication.domain.usecase

import edu.vt.mobiledev.aacapplication.domain.model.Category
import edu.vt.mobiledev.aacapplication.domain.repository.CategoryRepository
import javax.inject.Inject

class InsertCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(categories: List<Category>) {
        repository.insertCategories(categories)
    }
}
package edu.vt.mobiledev.aacapplication.domain.usecase

import edu.vt.mobiledev.aacapplication.domain.model.Category
import edu.vt.mobiledev.aacapplication.domain.repository.CategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getAllCategories()
    }
}
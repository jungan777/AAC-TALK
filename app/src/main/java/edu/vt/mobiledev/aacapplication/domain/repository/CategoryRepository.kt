package edu.vt.mobiledev.aacapplication.domain.repository

import edu.vt.mobiledev.aacapplication.domain.model.Category

interface CategoryRepository {
    suspend fun insertCategories(categories: List<Category>)
    suspend fun insertCategory(category: Category)
    suspend fun getAllCategories(): List<Category>
}
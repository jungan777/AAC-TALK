
package edu.vt.mobiledev.aacapplication.data.datasource

import edu.vt.mobiledev.aacapplication.data.model.CategoryDataModel

interface CategoryDataSource {
    suspend fun insertCategories(categories: List<CategoryDataModel>)
    suspend fun insertCategory(category: CategoryDataModel)
    suspend fun getAllCategories(): List<CategoryDataModel>
}


package edu.vt.mobiledev.aacapplication.data.impl

import edu.vt.mobiledev.aacapplication.data.datasource.CategoryDataSource
import edu.vt.mobiledev.aacapplication.data.mapper.toDataModel
import edu.vt.mobiledev.aacapplication.data.mapper.toDomain
import edu.vt.mobiledev.aacapplication.domain.model.Category
import edu.vt.mobiledev.aacapplication.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: CategoryDataSource
) : CategoryRepository {

    override suspend fun insertCategories(
        categories: List<Category>
    ) {
        dataSource.insertCategories(
            categories.map { it.toDataModel() }
        )
    }

    override suspend fun insertCategory(
        category: Category
    ) {
        dataSource.insertCategory(category.toDataModel())
    }

    override suspend fun getAllCategories(): List<Category> {
        return dataSource.getAllCategories().map {
            it.toDomain()
        }
    }
}

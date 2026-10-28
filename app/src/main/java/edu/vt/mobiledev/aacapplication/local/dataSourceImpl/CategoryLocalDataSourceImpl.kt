package edu.vt.mobiledev.aacapplication.local.dataSourceImpl

import edu.vt.mobiledev.aacapplication.data.datasource.CategoryDataSource

import edu.vt.mobiledev.aacapplication.data.mapper.toDataModel
import edu.vt.mobiledev.aacapplication.data.mapper.toEntity
import edu.vt.mobiledev.aacapplication.data.model.CategoryDataModel
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.CategoryDao
import javax.inject.Inject

class CategoryLocalDataSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryDataSource {

    override suspend fun insertCategories(
        categories: List<CategoryDataModel>
    ) {
        categoryDao.insertCategories(
            categories.map { it.toEntity() }
        )
    }

    override suspend fun insertCategory(
        category: CategoryDataModel
    ) {
        categoryDao.insertCategory(category.toEntity())
    }

    override suspend fun getAllCategories(): List<CategoryDataModel> {
        return categoryDao.getAllCategories().map {
            it.toDataModel()
        }
    }
}

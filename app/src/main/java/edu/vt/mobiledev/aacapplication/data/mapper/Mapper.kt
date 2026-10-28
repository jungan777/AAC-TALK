package edu.vt.mobiledev.aacapplication.data.mapper

import edu.vt.mobiledev.aacapplication.data.model.CategoryDataModel
import edu.vt.mobiledev.aacapplication.domain.model.Sentence
import edu.vt.mobiledev.aacapplication.data.model.SentenceDataModel
import edu.vt.mobiledev.aacapplication.data.model.WordDataModel
import edu.vt.mobiledev.aacapplication.domain.model.Category
import edu.vt.mobiledev.aacapplication.domain.model.Word
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.CategoryEntity
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.SentenceEntity
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.WordEntity

// Domain -> Data
fun Sentence.toDataModel(): SentenceDataModel {
    return SentenceDataModel(
        id = id,
        content = content
    )
}

// Data -> Domain
fun SentenceDataModel.toDomain(): Sentence {
    return Sentence(
        id = id,
        content = content
    )
}

// Data -> Room Entity
fun SentenceDataModel.toEntity(): SentenceEntity {
    return SentenceEntity(
        id = id,
        content = content
    )
}

// Room Entity -> Data
fun SentenceEntity.toDataModel(): SentenceDataModel {
    return SentenceDataModel(
        id = id,
        content = content
    )
}
fun Category.toDataModel() = CategoryDataModel(
    id = id,
    nameOfCategory = nameOfCategory,
    icon = icon
)

// Data -> Domain
fun CategoryDataModel.toDomain() = Category(
    id = id,
    nameOfCategory = nameOfCategory,
    icon = icon
)

// Data -> Entity
fun CategoryDataModel.toEntity() = CategoryEntity(
    id = id,
    nameOfCategory = nameOfCategory,
    icon = icon
)

// Entity -> Data
fun CategoryEntity.toDataModel() = CategoryDataModel(
    id = id,
    nameOfCategory = nameOfCategory,
    icon = icon
)
fun Word.toDataModel() = WordDataModel(
    id = id,
    categoryId = categoryId,
    text = text,
    type = type,
    icon = icon
)

// Data -> Domain
fun WordDataModel.toDomain() = Word(
    id = id,
    categoryId = categoryId,
    text = text,
    type = type,
    icon = icon
)

// Data -> Entity
fun WordDataModel.toEntity() = WordEntity(
    id = id,
    categoryId = categoryId,
    text = text,
    type = type,
    icon = icon
)

// Entity -> Data
fun WordEntity.toDataModel() = WordDataModel(
    id = id,
    categoryId = categoryId,
    text = text,
    type = type,
    icon = icon
)
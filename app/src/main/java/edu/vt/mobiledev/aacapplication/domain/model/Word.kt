package edu.vt.mobiledev.aacapplication.domain.model

data class Word(
    val id: Int = 0,
    val categoryId: Int,
    val text: String,
    val type: String,
    val icon: Int
)
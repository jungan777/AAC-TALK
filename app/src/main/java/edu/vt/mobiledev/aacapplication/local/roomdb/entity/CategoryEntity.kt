package edu.vt.mobiledev.aacapplication.local.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    //1,2,3 이런식으로 자동생성된다 따라서 1 장소 등등... 이렇게 엮임
    val nameOfCategory: String, // 장소, 음식, 사람, 활동 등
    val icon:Int
)


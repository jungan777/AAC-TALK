package edu.vt.mobiledev.aacapplication.local.roomdb.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    foreignKeys = [ForeignKey(
        //키 연결
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        //id와 category id 연동
        childColumns = ["categoryId"],
        onDelete = ForeignKey.CASCADE
    )],
    //연동을 위해 인덱스 추가
    indices = [Index("categoryId")]
)
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryId: Int,    // 어느 카테고리에 속하는 단어인지
    val text: String,      // 단어 내용 (예: 화장실, 가고 싶어요, 지금)
    val type: String,       // 명사/동사/부사/형용사 중 하나
    val icon:Int
)
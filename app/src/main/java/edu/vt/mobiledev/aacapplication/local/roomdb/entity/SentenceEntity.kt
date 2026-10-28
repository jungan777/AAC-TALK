package edu.vt.mobiledev.aacapplication.local.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sentences")
data class SentenceEntity(
    //sentence history 를 위한 거임
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,       // 유저가 완성한 문장을 보여주는 것으로
)
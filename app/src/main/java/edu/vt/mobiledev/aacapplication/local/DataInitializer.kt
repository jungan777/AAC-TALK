package edu.vt.mobiledev.aacapplication.local

import android.content.Context
import android.util.Log
import androidx.room.withTransaction
import dagger.hilt.android.qualifiers.ApplicationContext
import edu.vt.mobiledev.aacapplication.R
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.CategoryDao
import edu.vt.mobiledev.aacapplication.local.roomdb.dao.WordDao
import edu.vt.mobiledev.aacapplication.local.roomdb.database.AppDatabase
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.CategoryEntity
import edu.vt.mobiledev.aacapplication.local.roomdb.entity.WordEntity
import javax.inject.Inject

class DataInitializer @Inject constructor(
    private val database: AppDatabase,
    private val categoryDao: CategoryDao,
    private val wordDao: WordDao,
    @ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    suspend fun initIfFirstRun() {
        if (!prefs.getBoolean("is_first_run", true)) {
            Log.d("DataInitializer", "Not first run, skipping initialization")
            return
        }

        val categories = listOf(
            CategoryEntity(id = 1, nameOfCategory = "직업/사람", icon = R.drawable.aac_job),
            CategoryEntity(id = 2, nameOfCategory = "장소", icon = R.drawable.aac_school),
            CategoryEntity(id = 3, nameOfCategory = "음식", icon = R.drawable.aac_food),
            CategoryEntity(id = 4, nameOfCategory = "활동", icon = R.drawable.aac_activity),
            CategoryEntity(id = 5, nameOfCategory = "상태", icon = R.drawable.aac_state),
            CategoryEntity(id = 6, nameOfCategory = "나만의 문장", icon = R.drawable.aac_docu)
        )

        val words = listOf(
            // 상태 (categoryId = 5)
            WordEntity(categoryId = 5, text = "목말라요", type = "문장", icon = R.drawable.aac_thirsty),
            WordEntity(categoryId = 5, text = "배고프다", type = "문장", icon = R.drawable.aac_hungry),
            WordEntity(categoryId = 5, text = "더워요", type = "문장", icon = R.drawable.aac_hot),
            WordEntity(categoryId = 5, text = "추워요", type = "문장", icon = R.drawable.aac_cold),
            WordEntity(categoryId = 5, text = "피곤해요", type = "문장", icon = R.drawable.aac_tired),
            WordEntity(categoryId = 5, text = "배부르다", type = "문장", icon = R.drawable.aac_full),
            WordEntity(categoryId = 5, text = "아파요", type = "문장", icon = R.drawable.aac_sick),
            WordEntity(categoryId = 5, text = "졸려요", type = "문장", icon = R.drawable.aac_sleepy),
            WordEntity(categoryId = 5, text = "주세요", type = "문장", icon = R.drawable.aac_give),
            WordEntity(categoryId = 5, text = "도와주세요", type = "문장", icon = R.drawable.aac_help),
            WordEntity(categoryId = 5, text = "좋아요", type = "문장", icon = R.drawable.aac_good),
            WordEntity(categoryId = 5, text = "나빠요", type = "문장", icon = R.drawable.aac_bad),

            // 음식 (categoryId = 3)
            WordEntity(categoryId = 3, text = "밥", type = "명사", icon = R.drawable.aac_rice),
            WordEntity(categoryId = 3, text = "물", type = "명사", icon = R.drawable.aac_water),
            WordEntity(categoryId = 3, text = "고기", type = "명사", icon = R.drawable.aac_meat),
            WordEntity(categoryId = 3, text = "생선", type = "명사", icon = R.drawable.aac_fish),
            WordEntity(categoryId = 3, text = "야채", type = "명사", icon = R.drawable.aac_vegi),
            WordEntity(categoryId = 3, text = "과일", type = "명사", icon = R.drawable.aac_fruit),
            WordEntity(categoryId = 3, text = "빵", type = "명사", icon = R.drawable.aac_bread),
            WordEntity(categoryId = 3, text = "피자", type = "명사", icon = R.drawable.aac_pizza),

            // 활동 (categoryId = 4)
            WordEntity(categoryId = 4, text = "보다", type = "단어", icon = R.drawable.aac_look),
            WordEntity(categoryId = 4, text = "듣다", type = "단어", icon = R.drawable.aac_hear),
            WordEntity(categoryId = 4, text = "자다", type = "단어", icon = R.drawable.aac_sleep),
            WordEntity(categoryId = 4, text = "놀다", type = "단어", icon = R.drawable.aac_play),
            WordEntity(categoryId = 4, text = "운동하다", type = "단어", icon = R.drawable.aac_exercise),
            WordEntity(categoryId = 4, text = "수영하다", type = "단어", icon = R.drawable.aac_swimming),
            WordEntity(categoryId = 4, text = "산책하다", type = "단어", icon = R.drawable.aac_walking),
            WordEntity(categoryId = 4, text = "읽다", type = "단어", icon = R.drawable.aac_read),
            WordEntity(categoryId = 4, text = "그리다", type = "단어", icon = R.drawable.aac_draw),
            WordEntity(categoryId = 4, text = "노래하다", type = "단어", icon = R.drawable.aac_sing),
            WordEntity(categoryId = 4, text = "춤추다", type = "단어", icon = R.drawable.aac_dance),
            WordEntity(categoryId = 4, text = "먹다", type = "단어", icon = R.drawable.aac_eat),
            WordEntity(categoryId = 4, text = "만나다", type = "단어", icon = R.drawable.aac_meet),

            // 장소 (categoryId = 2)
            WordEntity(categoryId = 2, text = "화장실", type = "명사", icon = R.drawable.aac_toilet),
            WordEntity(categoryId = 2, text = "방", type = "명사", icon = R.drawable.aac_room),
            WordEntity(categoryId = 2, text = "학교", type = "명사", icon = R.drawable.aac_school),
            WordEntity(categoryId = 2, text = "집", type = "명사", icon = R.drawable.aac_home),
            WordEntity(categoryId = 2, text = "공원", type = "명사", icon = R.drawable.aac_park),
            WordEntity(categoryId = 2, text = "도서관", type = "명사", icon = R.drawable.aac_library),
            WordEntity(categoryId = 2, text = "병원", type = "명사", icon = R.drawable.aac_hospital),
            WordEntity(categoryId = 2, text = "놀이터", type = "명사", icon = R.drawable.aac_playground),
            WordEntity(categoryId = 2, text = "지하철", type = "명사", icon = R.drawable.aac_subway),

            // 직업/사람 (categoryId = 1)
            WordEntity(categoryId = 1, text = "엄마", type = "문장", icon = R.drawable.aac_mom),
            WordEntity(categoryId = 1, text = "나", type = "문장", icon = R.drawable.aac_me),
            WordEntity(categoryId = 1, text = "아빠", type = "문장", icon = R.drawable.aac_dad),
            WordEntity(categoryId = 1, text = "친구", type = "문장", icon = R.drawable.aac_friend),
            WordEntity(categoryId = 1, text = "할머니", type = "문장", icon = R.drawable.aac_grandmother),
            WordEntity(categoryId = 1, text = "할아버지", type = "문장", icon = R.drawable.aac_grandfater),
            WordEntity(categoryId = 1, text = "선생님", type = "문장", icon = R.drawable.aac_teacher),
            WordEntity(categoryId = 1, text = "의사", type = "문장", icon = R.drawable.aac_doctor),
            WordEntity(categoryId = 1, text = "요리사", type = "문장", icon = R.drawable.aac_chef),
            WordEntity(categoryId = 1, text = "화가", type = "문장", icon = R.drawable.aac_job),
            WordEntity(categoryId = 1, text = "가수", type = "문장", icon = R.drawable.aac_singer)
        )

        try {
            // 카테고리와 단어를 함께 저장: 도중 실패하면 둘 다 롤백
            database.withTransaction {
                categoryDao.insertCategories(categories)
                wordDao.insertWords(words)
            }

            // 모든 DB 삽입이 성공한 다음 최초 실행 플래그 저장
            val saved = prefs.edit()
                .putBoolean("is_first_run", false)
                .commit()

            check(saved) { "Failed to persist first-run flag" }
            Log.d("DataInitializer", "Initialized ${categories.size} categories and ${words.size} words")
        } catch (e: Exception) {
            Log.e("DataInitializer", "Initial data insertion failed", e)
            throw e
        }
    }
}

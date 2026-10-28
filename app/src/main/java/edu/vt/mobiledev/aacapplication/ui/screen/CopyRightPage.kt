import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.ClickableText

data class Attribution(
    val title: String,
    val author: String,
    val url: String
)

@Composable
fun CopyRightPage() {
    val context = LocalContext.current

    // 아이콘 리스트 (네가 보낸 전체 목록)
    val attributions = listOf(
        Attribution("개 산책 아이콘", "photo3idea_studio", "https://www.flaticon.com/kr/free-icons/-"),
        Attribution("앉다 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("기분 아이콘", "noomtah", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("비빔밥 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("활동 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("학교 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("예술가 아이콘", "RaftelDesign", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("목마르다 아이콘", "HAJICON", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("더운 날씨 아이콘", "Pomicon", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("배고픈 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("춥다 아이콘", "wanicon", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("피곤 아이콘", "miftahulniqi", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("상처 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("잠 잘 수 없음 아이콘", "surang", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("용서 아이콘", "Parzival’ 1997", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("유능한 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("걱정 아이콘", "kornkun", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("아이와 놀다 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("운동하다 아이콘", "GOWI", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("수영 아이콘", "Chattapat", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("학생 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("학교 자료 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("명음 아이콘", "photo3idea_studio", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("행복한 사람들 아이콘", "muh zakaria", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("지각 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("듣기 아이콘", "msidiqf", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("자다 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("밥 그릇 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("물 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("고기 아이콘", "monkik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("물고기 아이콘", "surang", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("야채 아이콘", "justicon", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("과일 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("빵 아이콘", "amonrat rungreangfangsai", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("피자 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("화장실 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("방 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("학교 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("집 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("경치 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("대학 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("병원 아이콘", "Blak1ta", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("가게 아이콘", "Vectors Market", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("놀이터 아이콘", "cah nggunung", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("바다 아이콘", "Yobany_MTOM", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("기차역 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("엄마 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("아버지 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("친구 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("가족 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("노인 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("선생님 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("정신과 의사 아이콘", "Flat Icons", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("요리사 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("가수 아이콘", "dDara", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("종이 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("남자 아이콘", "Freepik", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("Volume icons", "Bharat Icons", "https://www.flaticon.com/free-icons/volume"),
        Attribution("Back icons", "Freepik", "https://www.flaticon.com/free-icons/back"),
        Attribution("폭식 아이콘", "Flat Icons", "https://www.flaticon.com/kr/free-icons/"),
        Attribution("만나다 아이콘", "Eucalyp", "https://www.flaticon.com/kr/free-icons/")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "아이콘 출처 (Flaticon)",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        items(attributions) { attribution ->
            val annotatedText = buildAnnotatedString {
                append("${attribution.title} 제작자: ${attribution.author} - Flaticon")
                addStyle(
                    style = SpanStyle(
                        color = androidx.compose.ui.graphics.Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = 0,
                    end = length
                )
                addStringAnnotation(
                    tag = "URL",
                    annotation = attribution.url,
                    start = 0,
                    end = length
                )
            }

            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations("URL", offset, offset)
                        .firstOrNull()?.let { annotation ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                            context.startActivity(intent)
                        }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

package edu.vt.mobiledev.aacapplication.ui.screen

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.vt.mobiledev.aacapplication.R
import edu.vt.mobiledev.aacapplication.ScreenRoute
import edu.vt.mobiledev.aacapplication.ui.activity.AiRecommendActivity
import edu.vt.mobiledev.aacapplication.ui.viewmodel.AiViewModel
import edu.vt.mobiledev.aacapplication.ui.viewmodel.MainViewModel
@Composable
fun AiPage(
    navController: NavController,
    mainViewModel: MainViewModel,
    aiViewModel: AiViewModel = hiltViewModel()
) {

    val selectedWords by mainViewModel.selectedWords.collectAsState()
    val response by aiViewModel.aiResponse.collectAsState()
    val isLoading by aiViewModel.isLoading.collectAsState()
    val context = LocalContext.current

    val prompt = """
        너는 AAC 교육자야, 사용자가 준 단어를 모두 이용해서 가장 자연스러운 문장 4개를 각각 넘버링해서 출력해 그리고 각 문장 끝에 줄바꿈 해.
        정확히 문장과 문장이 쓰이는상황만 써 다른 말 쓰지 말고.일상에서 문장이 자주 쓰이는 상황으로 적어.
        AAC 앱 교육용이라 최대한 쉽고 직관적인 문장과 문장이 쓰이는 상황 적어. 가장 우선적으로 한국어 문법에 맞게 적어 단어 순서, 형태와 관계 없이 의미가 담겨있게.
        사용자가 고른 단어 모두 다 사용해서 문장을 만들어.문장 다음 "상황"을 적을 때 줄 바꿈 한줄을 해.
        사용 단어: '${selectedWords.joinToString(", ") { it.text }}'
    """.trimIndent()

    // AI 응답이 준비되면 Activity 이동
    LaunchedEffect(response) {
        if (response.isNotEmpty()) {
            val intent = Intent(context, AiRecommendActivity::class.java)
            intent.putExtra("AI_RESPONSE", response)
            context.startActivity(intent)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.aac_speaker),
            contentDescription = "TTS Speaker",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "${selectedWords.joinToString(", ") { it.text }} 문장을 완성 했어요! AI 추천 문장과 자연스러운 문장을 같이 볼까요?",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp, // 텍스트 크기 키우기
            modifier = Modifier.padding(horizontal = 40.dp)
        )

        if (isLoading) {
            CircularProgressIndicator()
            Text(
                "AI 응답 생성 중...",
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        val buttonModifier = Modifier
            .width(250.dp)
            .height(60.dp)
        Spacer(modifier = Modifier.height(20.dp)) // 버튼 간격

        Button(
            onClick = { aiViewModel.sendPrompt(prompt) },
            enabled = !isLoading,
            modifier = buttonModifier
        ) {
            Text("AI 추천 문장 보기", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(15.dp)) // 버튼 간격

        Button(
            onClick = { navController.popBackStack(ScreenRoute.Home.name, inclusive = false) },
            modifier = buttonModifier
        ) {
            Text("처음으로", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(15.dp))


    }
}

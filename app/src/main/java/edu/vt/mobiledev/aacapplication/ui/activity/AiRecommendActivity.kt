package edu.vt.mobiledev.aacapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import edu.vt.mobiledev.aacapplication.ui.theme.AACApplicationTheme
import edu.vt.mobiledev.aacapplication.ui.viewmodel.AiViewModel

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.vt.mobiledev.aacapplication.ui.viewmodel.SentenceViewModel
import edu.vt.mobiledev.aacapplication.util.TTS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AiRecommendActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val response = intent.getStringExtra("AI_RESPONSE") ?: "응답 없음"


        setContent {
            AACApplicationTheme {
                AiRecommendScreen(response)
            }
        }
    }
}

@Composable
fun AiRecommendScreen(
    response: String,
    viewModel: AiViewModel = hiltViewModel(),
    sentenceViewModel: SentenceViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current

    // 체크 상태 관리
    val selectedItems = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "나만의 문장 추가" 버튼

        val responseList = Regex("""\d+\.\s""")
            .split(response)
            .filter { it.isNotBlank() }
            .map { item ->
                val lines = item.trim().lines()
                if (lines.size >= 2) {
                    val sentence = lines[0].trim()
                    val situation = lines.subList(1, lines.size).joinToString(" ").trim()
                    "$sentence\n$situation"
                } else item.trim()
            }
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 50.dp))
        } else {


            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(responseList.size) { index ->

                    val isSelected = selectedItems.contains(index)
                    androidx.compose.material3.Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                TTS.speak(responseList[index].trim()) // 클릭 시 TTS
                                if (isSelected) selectedItems.remove(index)
                                else selectedItems.add(index)
                            },
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                        elevation = androidx.compose.material3.CardDefaults.cardElevation(4.dp),
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = if (isSelected) Color(0xFFD0F0C0) else Color.White
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${index + 1}. ${responseList[index].trim()}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            androidx.compose.material3.Checkbox(
                                checked = isSelected,
                                onCheckedChange = {
                                    if (it) selectedItems.add(index)
                                    else selectedItems.remove(index)
                                }
                            )
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                // 선택된 문장을 DB에 추가
                selectedItems.forEach { index ->
                    val sentenceText = responseList[index].trim()
                    // DB에 삽입
                    CoroutineScope(Dispatchers.IO).launch {
                        sentenceViewModel.addSentence(sentenceText)
                    }
                }
                Toast.makeText(context, "선택된 문장을 저장했습니다.", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .width(350.dp)
                .height(80.dp)
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "나만의 문장 추가",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        // 처음으로 버튼
        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
            },
            modifier = Modifier
                .width(350.dp)
                .height(80.dp)
                .padding(bottom = 24.dp),
        ) {
            Text(
                text = "처음으로",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


package edu.vt.mobiledev.aacapplication.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.vt.mobiledev.aacapplication.ui.viewmodel.SentenceViewModel
import edu.vt.mobiledev.aacapplication.util.TTS

@Composable
fun MySentencePage(
    sentenceViewModel: SentenceViewModel = hiltViewModel()
) {
    // ViewModel의 StateFlow 구독
    val sentences by sentenceViewModel.sentences.collectAsState()

    // 화면 최초 진입 시 DB 조회
    LaunchedEffect(sentenceViewModel) {
        sentenceViewModel.getAllSentences { }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(
            items = sentences,
            key = { it.id }
        ) { sentence ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 4.dp
                    )
                    .clickable {
                        TTS.speak(sentence.content)
                    },
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    text = sentence.content,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

package edu.vt.mobiledev.aacapplication.ui.activity

import CopyRightPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.vt.mobiledev.aacapplication.data.BottomNavigationItem
import edu.vt.mobiledev.aacapplication.data.bottomNavItems
import edu.vt.mobiledev.aacapplication.ui.theme.AACApplicationTheme
import edu.vt.mobiledev.aacapplication.ui.viewmodel.CategoryViewModel
import edu.vt.mobiledev.aacapplication.ui.viewmodel.MainViewModel
import edu.vt.mobiledev.aacapplication.ui.viewmodel.WordViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.vt.mobiledev.aacapplication.R
import edu.vt.mobiledev.aacapplication.ScreenRoute
import edu.vt.mobiledev.aacapplication.ui.screen.AiPage
import edu.vt.mobiledev.aacapplication.ui.screen.CategoryGridScreen
import edu.vt.mobiledev.aacapplication.ui.screen.MySentencePage
import edu.vt.mobiledev.aacapplication.ui.screen.WordGridScreen
import edu.vt.mobiledev.aacapplication.util.TTS

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            AACApplicationTheme {
                MyApp()

            }
        }
    }
}

@Composable
fun MyApp() {
    val mainViewModel: MainViewModel = hiltViewModel()
    val wordViewModel: WordViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val navController = rememberNavController()
    val words by wordViewModel.words.collectAsState()
    val categories by categoryViewModel.categories.collectAsState()
    val screenCount by mainViewModel.screenCount.collectAsState()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        TTS.init(context)
    }

    LaunchedEffect(Unit) {
        categoryViewModel.getAllCategories() // ViewModel에서 카테고리 불러오는 함수
    }

    Scaffold(
        topBar = {
            if (currentRoute != ScreenRoute.Profile.name) {
                TopBarLogic(mainViewModel, wordViewModel, navController)
            }

        },

        bottomBar = {
            BottomNavigationBar(navController = navController, items = bottomNavItems)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenRoute.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenRoute.Home.name) {
                LaunchedEffect(Unit) {
                    mainViewModel.removeAllSelectedWords()
                }
                CategoryGridScreen(
                    categories = categories,
                    onCategoryClick = { categoryId ->
                        //처음 id 설정
                        if (categoryId == 6) {
                            // 나만의 문장 페이지로 이동
                            navController.navigate(ScreenRoute.MySentencePage.name)
                        } else {
                            // 기존 Word 리스트 화면으로 이동
                            mainViewModel.setInitialCategory(categoryId)
                            wordViewModel.loadWordsByCategoryAndType(categoryId)
                            navController.navigate(ScreenRoute.WordList.name)
                        }
                    }
                )
            }

            composable(ScreenRoute.Profile.name) { CopyRightPage() }

            composable(ScreenRoute.MySentencePage.name) {
                MySentencePage()
            }

            composable(ScreenRoute.WordList.name) {
                WordGridScreen(
                    words = words,
                    onWordClick = { selectedWord ->
                        TTS.speak(selectedWord.text)
                        //클릭시 다음 화면
                        mainViewModel.nextScreen()
                        //단어 추가(위에 표시할꺼 보여주려고)
                        mainViewModel.selectWord(selectedWord)


                        val nextCategoryId = mainViewModel.currentCategoryId.value
                        //AIPage 로 진입 하면 뒤로 가기 눌렀을 때 Home으로 가지게
                        if (nextCategoryId == 6) {
                            navController.navigate(ScreenRoute.AiPage.name) {
                                popUpTo(ScreenRoute.WordList.name) { inclusive = true }
                            }
                        }

                        if (nextCategoryId != null) {
                            wordViewModel.loadWordsByCategoryAndType(nextCategoryId)
                        } else {
                            navController.navigate(ScreenRoute.AiPage.name)
                        }

                    },
                    onBack = {
                        val beforeCategoryId = mainViewModel.currentCategoryId.value
                        val currentScreenCount = mainViewModel.screenCount.value

                        // 화면 뒤로 가는 대신 Home으로 바로 이동해야 하는 경우
                        if (currentScreenCount <= 0 || beforeCategoryId == 6 || beforeCategoryId == null || beforeCategoryId == 1) {
                            navController.popBackStack(ScreenRoute.Home.name, inclusive = false)
                            return@WordGridScreen // 여기서 바로 반환
                        }

                        // 이전 카테고리로 이동
                        mainViewModel.goBack()

                        val prevCategoryId = mainViewModel.currentCategoryId.value
                        if (prevCategoryId != null) {
                            wordViewModel.loadWordsByCategoryAndType(prevCategoryId)
                        } else {
                            navController.popBackStack(ScreenRoute.Home.name, inclusive = false)
                        }
                    }
                )
            }
            composable(ScreenRoute.AiPage.name) {
                AiPage(navController = navController, mainViewModel = mainViewModel)
            }


        }
    }


}

@Composable
fun BottomNavigationBar(navController: NavHostController, items: List<BottomNavigationItem>) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.label
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        //화면 이동 코드
                        navController.navigate(screen.route) {
                            // 백스택에 같은 화면 쌓이지 않도록
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun TopBarLogic(
    mainViewModel: MainViewModel,
    wordViewModel: WordViewModel,
    navController: NavController
) {
    val selectedWords by mainViewModel.selectedWords.collectAsState()
    val listState = rememberLazyListState()


    // selectedWords 업데이트 될 때 맨 앞?뒤?으로 스크롤
    LaunchedEffect(selectedWords.size) {
        if (selectedWords.isNotEmpty()) {
            listState.animateScrollToItem(selectedWords.lastIndex)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(Color(0xFFE8E9F1)),


        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //TODO 여기에 단어 카드 보이게 70x70으로 생성
        // 왼쪽: 선택된 단어 표시 영역
        LazyRow(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .height(100.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(selectedWords) { word ->

                // 카테고리별 배경색과 테두리 색
                val backgroundColor = when (word.categoryId) {
                    1 -> Color(0xFFFFF0F0) // 빨강 계열
                    2 -> Color(0xFFFFD7AF) // 주
                    3 -> Color(0xFFF1ECC0) // 노
                    4 -> Color(0xFFD8FFD0) // 초
                    5 -> Color(0xFFD0D8FF) // 파
                    else -> Color.White
                }

                val borderColor = when (word.categoryId) {
                    1 -> Color(0xFFFDA8A8)
                    2 -> Color(0xFFFFC67A)
                    3 -> Color(0xFFF7D85D)
                    4 -> Color(0xFFADF18F)
                    5 -> Color(0xFF7D6CFF)
                    else -> Color.Gray
                }

                Card(
                    modifier = Modifier.size(width = 120.dp, height = 100.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(2.dp, borderColor),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = backgroundColor)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = word.icon),
                            contentDescription = word.text,
                            modifier = Modifier.size(50.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = word.text,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        // 오른쪽: 버튼 2개 세로 배치
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xFFE4F3FF))
        ) {
            IconButton(onClick = {
                val beforeCategoryId = mainViewModel.currentCategoryId.value
                val currentScreenCount = mainViewModel.screenCount.value

                // screenCount가 0이거나 AIPage였으면 바로 Home으로
                if (currentScreenCount <= 0 || beforeCategoryId == 6 || beforeCategoryId == null || beforeCategoryId == 1) {
                    navController.popBackStack(ScreenRoute.Home.name, inclusive = false)
                    return@IconButton
                }

                // 화면 뒤로 이동 + 카테고리 업데이트
                mainViewModel.goBack()

                val prevCategoryId = mainViewModel.currentCategoryId.value
                if (prevCategoryId != null) {
                    wordViewModel.loadWordsByCategoryAndType(prevCategoryId)
                } else {
                    navController.popBackStack(ScreenRoute.Home.name, inclusive = false)
                }
            })

            {
                Icon(
                    painter = painterResource(R.drawable.aac_back),
                    contentDescription = "지우기",
                    modifier = Modifier.size(40.dp)
                )
            }
            Text("지우기")
            IconButton(onClick = {
                //TODO: 위에 단어 삭제되게
                mainViewModel.nextScreen()

                val nextCategoryId = mainViewModel.currentCategoryId.value

                if (nextCategoryId == 6) {
                    navController.navigate(ScreenRoute.AiPage.name) {
                        popUpTo(ScreenRoute.WordList.name) { inclusive = true }
                    }
                }

                if (nextCategoryId != null) {
                    wordViewModel.loadWordsByCategoryAndType(nextCategoryId)
                } else {
                    navController.navigate(ScreenRoute.AiPage.name)
                }

            }) {
                Icon(
                    painter = painterResource(R.drawable.aac_front),
                    contentDescription = "건너뛰기",
                    modifier = Modifier.size(40.dp)
                )

            }
            Text("건너 뛰기")
        }
    }

}



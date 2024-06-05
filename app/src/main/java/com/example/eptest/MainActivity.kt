package com.example.eptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eptest.ui.theme.EpTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EpTestTheme {
                MainScreen()
            }
        }
    }
}

// Data class to hold MBTI test results
data class MbtiResults(
    val countE: Int, val countI: Int, val countS: Int, val countN: Int,
    val countT: Int, val countF: Int, val countJ: Int, val countP: Int,
)

// Shared ViewModel (alternative to passing data through navigation)
class MbtiViewModel : ViewModel() {
    var results = mutableStateOf(MbtiResults(0, 0, 0, 0, 0, 0, 0, 0))
}

// NAVIGATION 화면 전환 SCREEN
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel = MbtiViewModel()
    NavHost(navController = navController, startDestination = "test") {
        composable("test") { TestScreen(navController = navController, viewModel = viewModel) }
        composable("result") { ResultScreen(navController = navController, viewModel = viewModel) }
    }
}

@Composable
fun Qeustion(
    viewModel: MbtiViewModel,
    text: String,
) {
    var countE by remember { mutableStateOf<Int>(0) }
    var countI by remember { mutableStateOf<Int>(0) }
    var countS by remember { mutableStateOf<Int>(0) }
    var countN by remember { mutableStateOf<Int>(0) }
    var countT by remember { mutableStateOf<Int>(0) }
    var countF by remember { mutableStateOf<Int>(0) }
    var countJ by remember { mutableStateOf<Int>(0) }
    var countP by remember { mutableStateOf<Int>(0) }

    Text(text = text)
    Row(
    ) {
        Button(onClick = {
            countE++
            viewModel.results.value =
                MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
        }) {
            Text(text = "예")
        }
        Button(onClick = {
            countI++
            viewModel.results.value =
                MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
        }) {
            Text(text = "아니오")
        }
    }
}


//    @Composable
//    fun TestScreen(
//        modifier: Modifier = Modifier,
//        navController: NavController,
//        viewModel: MbtiViewModel,
//    ) {
//        // count 변수들을 저장하기 위한 MutableState
//        var countE by remember { mutableStateOf<Int>(0) }
//        var countI by remember { mutableStateOf<Int>(0) }
//        var countS by remember { mutableStateOf<Int>(0) }
//        var countN by remember { mutableStateOf<Int>(0) }
//        var countT by remember { mutableStateOf<Int>(0) }
//        var countF by remember { mutableStateOf<Int>(0) }
//        var countJ by remember { mutableStateOf<Int>(0) }
//        var countP by remember { mutableStateOf<Int>(0) }
//
//        Column(
//            modifier = modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally, // 가운데로 오는 ui 컴포넌트
//        ) {
//            Text(text = "당신은 사람들과의 대화를 즐깁니까?")
//            Row(
//            ) {
//
//                Button(onClick = {
//                    countE++
//                    viewModel.results.value =
//                        MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
//                }) {
//                    Text(text = "예")
//                }
//                Button(onClick = {
//                    countI++
//                    viewModel.results.value =
//                        MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
//                }) {
//                    Text(text = "아니오")
//                }
//            }
//
//            Text(text = "당신은 세부사항에 집중하는 것을 좋아합니까?")
//            Row(
//            ) {
//
//                Button(onClick = {
//                    countS++
//                    viewModel.results.value =
//                        MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
//                }) {
//                    Text(text = "예")
//                }
//                Button(onClick = {
//                    countN++
//                    viewModel.results.value =
//                        MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
//                }) {
//                    Text(text = "아니오")
//                }
//            }
//
//            Text(text = "당신은 문제를 해결 시 논리적인 접근을 선호하나요?")
//            Row(
//            ) {
//
//                Button(onClick = {
//                    countT++
//                    viewModel.results.value =
//                        MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
//                }) {
//                    Text(text = "예")
//                }
//                Button(onClick = {
//                    countF++
//                    viewModel.results.value =
//                        MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
//                }) {
//                    Text(text = "아니오")
//                }
//            }
//
//            Text(text = "당신은 일을 마무리하는 것을 선호하나요?")
//            Row(
//            ) {
//
//                Button(onClick = {
//                    countJ++
//                    viewModel.results.value =
//                        MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
//                }) {
//                    Text(text = "예")
//                }
//                Button(onClick = {
//                    countP++
//                    viewModel.results.value =
//                        MbtiResults(countE, countI, countS, countN, countT, countF, countJ, countP)
//                }) {
//                    Text(text = "아니오")
//                }
//            }
//            Button(onClick = {
//
//                navController.navigate("result")
//            }) {
//                Text(text = "MBTI 측정결과")
//            }
//        }
//    }
//////////////////////////////////////복사///////////////////////////////////////////////////////

@Composable
fun TestScreen(
    modifier: Modifier = Modifier,
    navController: NavController, viewModel: MbtiViewModel,
) {
    // count 변수들을 저장하기 위한 MutableState
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Qeustion(viewModel = viewModel, text = "당신은 사람들과의 대화를 즐깁니까?")
        Qeustion(viewModel = viewModel, text = "당신은 세부사항에 집중하는 것을 좋아합니까?")
        Qeustion(viewModel = viewModel, text = "당신은 문제를 해결 시 논리적인 접근을 선호하나요?")
        Qeustion(viewModel = viewModel, text = "당신은 일을 마무리하는 것을 선호하나요?")

        Button(onClick = {
            navController.navigate("result")
        }) {
            Text(text = "MBTI 측정결과")
        }
    }
}


//////////////////////////////////////////////복사 끝/////////////////////////////////////////////

@Composable
fun ResultScreen(navController: NavController, viewModel: MbtiViewModel) {
    var mbtiString = ""
    val results = viewModel.results.value
//    val total =
//        results.countE + results.countI + results.countS + results.countN + results.countT + results.countF + results.countJ + results.countP
    if (results.countE > results.countI) {
        mbtiString += "E"
    } else {
        mbtiString += "I"
    }

    if (results.countS > results.countN) {
        mbtiString += "S"
    } else {
        mbtiString += "N"
    }

    if (results.countT > results.countF) {
        mbtiString += "T"
    } else {
        mbtiString += "F"
    }

    if (results.countJ > results.countP) {
        mbtiString += "J"
    } else {
        mbtiString += "P"
    }

    Text(text = mbtiString)

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EpTestTheme {
        MainScreen()
    }
}


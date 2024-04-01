package com.devtechlogix.notesapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devtechlogix.notesapplication.api.APIService
import com.devtechlogix.notesapplication.data.DataManager
import com.devtechlogix.notesapplication.models.Contributers
import com.devtechlogix.notesapplication.ui.theme.NotesApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiService: APIService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Local Data Loading
//        CoroutineScope(Dispatchers.IO).launch {
//            delay(3000)
//            DataManager.loadsData(applicationContext)
//        }

        //Remote Data Loading
        CoroutineScope(Dispatchers.IO).launch {
            delay(10000)
            val response = apiService.getContributors()
            Log.d("", response.toString())
            DataManager.dataList = response.body() as List<Contributers>
            DataManager.isDataLoaded.value = true
        }


        setContent {
//            App()
            NotesApplicationTheme {
                ShowContributersList {

                }
            }
        }
    }
}


//@Composable
//fun App() {
//    val theme = remember {
//        mutableStateOf(false)
//    }
//    NotesApplicationTheme(theme.value) {
//        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
//            Text(text = "Hello AR", style = MaterialTheme.typography.headlineMedium)
//            Button(onClick = {
//                theme.value = !theme.value
//            }) {
//                Text(text = "theme changed")
//            }
//        }
//    }
//
//}


@Composable
fun App() {

    val degree = produceState(initialValue = 0) {
        while (true) {
            delay(16)
            value = value + 10 % 360
        }
    }


    val context = LocalContext.current
    if (DataManager.isDataLoaded.value) {
        if (DataManager.currentPage.value == PAGES.LIST) {
            ShowContributersList() {
                DataManager.currentContributer = it
                DataManager.switchPages()
                Toast.makeText(context, it.login, Toast.LENGTH_SHORT).show()
            }
        } else {
            ShowDetailPage(contributers = DataManager.currentContributer!!) {
                Toast.makeText(context, it.login, Toast.LENGTH_SHORT).show()
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(1f)) {
            Column {
                Image(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .rotate(degree.value.toFloat())

                )
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

enum class PAGES {
    LIST, DETAIL
}
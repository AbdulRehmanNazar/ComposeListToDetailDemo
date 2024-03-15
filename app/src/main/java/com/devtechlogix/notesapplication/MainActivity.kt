package com.devtechlogix.notesapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.devtechlogix.notesapplication.api.APIService
import com.devtechlogix.notesapplication.data.DataManager
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
            val response = apiService.getContributors()
            Log.d("", response.toString())
            DataManager.isDataLoaded.value = true
        }
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    val context = LocalContext.current
    if (DataManager.isDataLoaded.value) {

        if (DataManager.currentPage.value == PAGES.LIST) {
            ShowContributersList() {
                DataManager.currentContributer = it
                DataManager.switchPages()
                Toast
                    .makeText(context, it.login, Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            ShowDetailPage(contributers = DataManager.currentContributer!!) {
                Toast
                    .makeText(context, it.login, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    } else {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(1f)) {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

enum class PAGES {
    LIST, DETAIL
}
package com.devtechlogix.notesapplication

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devtechlogix.notesapplication.api.APIService
import com.devtechlogix.notesapplication.models.ContributerNavType
import com.devtechlogix.notesapplication.models.Contributers
import com.devtechlogix.notesapplication.ui.theme.NotesApplicationTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiService: APIService

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(text = "ContributerList", color = Color.White) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
                )
            }) {
                Box(modifier = Modifier.padding(it)) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PAGES.LIST.name) {
        composable(PAGES.LIST.name) {
//            NotesApplicationTheme {
            ShowContributersList {
                val contributer = Uri.encode(Gson().toJson(it))
                navController.navigate("${PAGES.DETAIL.name}/$contributer")
            }
//            }
        }
        composable(
            "${PAGES.DETAIL.name}/{contributer}", arguments = listOf(navArgument("contributer") {
                type = ContributerNavType()
            })
        ) {
            val contributers = it.arguments?.getParcelable<Contributers>("contributer")
            ShowDetailPage(contributers!!, navController) {}
        }
    }
}


enum class PAGES {
    LIST, DETAIL
}
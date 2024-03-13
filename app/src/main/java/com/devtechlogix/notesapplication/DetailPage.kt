package com.devtechlogix.notesapplication

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devtechlogix.notesapplication.data.DataManager
import com.devtechlogix.notesapplication.models.Contributers


/**
 * @Author: Abdul Rehman
 * @Date: 13/03/2024.
 */

@Composable
fun ShowDetailPage(contributers: Contributers, onClick: (data: Contributers) -> Unit) {
    BackHandler {
        DataManager.currentContributer = null
        DataManager.switchPages()
    }
    Column {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(1f),
            text = "Detail Page",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Box(modifier = Modifier.fillMaxSize(1f)) {
            ContributerItem(contributers = contributers, onClick = onClick)
        }

    }

}

fun testLambdas() {
    val lambdasTest = { x: Int -> println() }
}
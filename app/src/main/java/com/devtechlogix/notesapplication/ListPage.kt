package com.devtechlogix.notesapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.devtechlogix.notesapplication.models.Contributers
import com.devtechlogix.notesapplication.viewmodels.ContributersViewModel


/**
 * @Author: Abdul Rehman
 * @Date: 13/03/2024.
 */


@Composable
fun ShowContributersList(onClick: (data: Contributers) -> Unit) {
    val contributersViewModel: ContributersViewModel = hiltViewModel()
    val contributers: State<List<Contributers>> =
        contributersViewModel.contributers.collectAsState()

    Column {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(1f),
            text = "Contributers List",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(contributers.value) { contributer ->
                ContributerItem(contributer, onClick)
            }
        }
    }
}

@Composable
fun ContributerItem(contributers: Contributers, onClick: (data: Contributers) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(1f)
            .clickable {
                onClick(contributers)
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(contributers.imageURL),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Fit,


                )
            Spacer(modifier = Modifier.padding(10.dp))
            Column {
                Text(text = contributers.login, fontWeight = FontWeight.Bold)
                Text(text = contributers.url)
            }
        }
    }
}
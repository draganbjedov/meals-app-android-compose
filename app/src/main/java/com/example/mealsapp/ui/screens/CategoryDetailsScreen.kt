package com.example.mealsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.mealsapp.viewmodel.CategoryDetailsViewModel

@Composable
@ExperimentalCoilApi
fun CategoryDetailsScreen(
    viewModel: CategoryDetailsViewModel = viewModel(),
) {
    val category = viewModel.category
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = rememberImagePainter(
                data = category.imageUrl,
                builder = {
                    crossfade(true)
                    transformations(RoundedCornersTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        )
        Text(
            text = category.name,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = category.description,
                style = MaterialTheme.typography.subtitle2,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
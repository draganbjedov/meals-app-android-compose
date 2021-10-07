package com.example.mealsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.mealsapp.model.Category
import com.example.mealsapp.viewmodel.MealCategoriesViewModel

@Composable
@ExperimentalCoilApi
fun MealCategoriesScreen(viewModel: MealCategoriesViewModel = viewModel()) {
    val categories: List<Category> by viewModel.categories.observeAsState(listOf())
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(categories) { MealCategory(category = it) }
    }
}

@Composable
@ExperimentalCoilApi
fun MealCategory(category: Category) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row {
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
                    .size(88.dp)
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

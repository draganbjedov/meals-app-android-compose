package com.example.mealsapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.mealsapp.model.Category
import com.example.mealsapp.viewmodel.MealCategoriesViewModel

@Composable
@ExperimentalCoilApi
fun MealCategoriesScreen(
    viewModel: MealCategoriesViewModel = viewModel(),
    onClick: (id: String) -> Unit,
) {
    val categories: List<Category> by viewModel.categories.observeAsState(listOf())
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(categories) { MealCategory(category = it, onClick = onClick) }
    }
}

@Composable
@ExperimentalCoilApi
fun MealCategory(category: Category, onClick: (id: String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable { onClick(category.id) }
    ) {
        Column(
            modifier = Modifier.animateContentSize()
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
                        .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(0.8f)
                )
                Icon(
                    imageVector =
                    if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            isExpanded = !isExpanded
                        }
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.subtitle2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (isExpanded) 10 else 2,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 16.dp, end = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

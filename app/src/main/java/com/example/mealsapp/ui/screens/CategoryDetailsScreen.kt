package com.example.mealsapp.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.mealsapp.viewmodel.CategoryDetailsViewModel
import java.lang.Float.min

@Composable
@ExperimentalCoilApi
fun CategoryDetailsScreen(
    viewModel: CategoryDetailsViewModel = viewModel(),
) {
    val category = viewModel.category

    val scrollState = rememberLazyListState()
    val offset = min(1f, 1f - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex))
    val imageSize by animateDpAsState(targetValue = max(60.dp, 150.dp * offset))

    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(elevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(width = 3.dp, color = Color.Green),
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
                                .size(imageSize)
                        )
                    }
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                    )
                }
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(16.dp)
                )
            }
            val dummyContentList = (0..100).map { "Dummy content $it" }
            LazyColumn(state = scrollState) {
                items(dummyContentList) {
                    Text(text = it, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

enum class ImageState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 3.dp),
    Expanded(Color.Green, 200.dp, 6.dp),
}
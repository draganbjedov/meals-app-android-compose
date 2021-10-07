package com.example.mealsapp.ui.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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

    var imageState by remember { mutableStateOf(ImageState.Normal) }
    val transition = updateTransition(targetState = imageState, label = "imageState")

    val imageSize: Dp by transition.animateDp(targetValueByState = { it.size }, label = "size")
    val borderColor: Color by transition.animateColor(targetValueByState = { it.color }, label = "borderColor")
    val borderWidth: Dp by transition.animateDp(targetValueByState = { it.borderWidth }, label = "borderWidth")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(width = borderWidth, color = borderColor),
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
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                imageState =
                    if (imageState == ImageState.Normal) ImageState.Expanded else ImageState.Normal
            }
        ) {
            Text(text = if (imageState == ImageState.Expanded) "Zoom out" else "Zoom in")
        }
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

enum class ImageState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 3.dp),
    Expanded(Color.Green, 200.dp, 6.dp),
}
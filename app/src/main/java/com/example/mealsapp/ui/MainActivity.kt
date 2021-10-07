package com.example.mealsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealsapp.model.Category
import com.example.mealsapp.ui.theme.MealsAppTheme
import com.example.mealsapp.viewmodel.MealCategoriesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppTheme {
                MealsCategoriesScreen()
            }
        }
    }
}

@Composable
fun MealsCategoriesScreen(viewModel: MealCategoriesViewModel = viewModel()) {
    val categories: List<Category> by viewModel.categories.observeAsState(listOf())
    NumOfCat(num = categories.size)
}

@Composable
fun NumOfCat(num: Int) {
    Text(text = "Meals Categories $num")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealsAppTheme {
        MealsCategoriesScreen()
    }
}
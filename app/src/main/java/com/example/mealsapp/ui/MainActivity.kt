package com.example.mealsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.example.mealsapp.ui.screens.CategoryDetailsScreen
import com.example.mealsapp.ui.screens.MealCategoriesScreen
import com.example.mealsapp.ui.theme.MealsAppTheme

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppTheme {
                App()
            }
        }
    }
}

@Composable
@ExperimentalCoilApi
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "categories") {
        composable(route = "categories") {
            MealCategoriesScreen { id ->
                navController.navigate("category_details/$id")
            }
        }
        composable(
            route = "category_details/{categoryId}",
            arguments = listOf(navArgument("categoryId") {
                type = NavType.StringType
            })) { CategoryDetailsScreen() }
    }
}
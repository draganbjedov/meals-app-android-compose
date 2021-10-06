package com.example.mealsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mealsapp.model.MealsRepository

class MealCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
): ViewModel() {
}
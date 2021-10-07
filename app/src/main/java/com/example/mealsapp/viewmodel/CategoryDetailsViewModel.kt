package com.example.mealsapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mealsapp.model.Category
import com.example.mealsapp.model.MealsRepository

class CategoryDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    val category: Category

    init {
        val id = savedStateHandle.get<String>("categoryId")!!
        category = MealsRepository.getInstance().getCategory(id)
    }
}
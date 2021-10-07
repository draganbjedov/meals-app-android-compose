package com.example.mealsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsapp.model.Category
import com.example.mealsapp.model.MealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository(),
) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>(listOf())

    val categories: LiveData<List<Category>> = _categories

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.i("MEALS_APP", "Sending request...")
                val response = repository.getCategories()
                Log.i("MEALS_APP", "Response received")
                _categories.postValue(response.categories)
            }
        }
    }

}
package com.example.mealsapp.model.api

import com.example.mealsapp.model.response.CategoriesResponse
import retrofit2.http.GET

interface MealsService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse
}
package com.example.mealsapp.model.api

import com.example.mealsapp.model.Category
import com.example.mealsapp.model.response.CategoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MealsService {

    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("lookup.php?i={id}")
    suspend fun getCategory(@Path("id") id: String): Category
}
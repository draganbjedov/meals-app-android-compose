package com.example.mealsapp.model

import com.example.mealsapp.model.api.MealsService
import com.example.mealsapp.model.response.CategoriesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsRepository {

    private val service = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MealsService::class.java)

    suspend fun getCategories(): CategoriesResponse {
        return service.getCategories()
    }
}
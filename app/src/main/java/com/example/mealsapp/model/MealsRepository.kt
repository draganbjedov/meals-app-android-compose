package com.example.mealsapp.model

import com.example.mealsapp.model.api.MealsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsRepository private constructor() {

    private val service = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MealsService::class.java)

    private var cachedCategories = listOf<Category>()

    suspend fun getCategories(): List<Category> {
        val response = service.getCategories()
        cachedCategories = response.categories
        return cachedCategories
    }

    fun getCategory(id: String): Category {
        return cachedCategories.first { it.id == id }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance(): MealsRepository {
            synchronized(this) {
                if (instance == null) {
                    instance = MealsRepository()
                }
                return instance as MealsRepository
            }
        }
    }
}
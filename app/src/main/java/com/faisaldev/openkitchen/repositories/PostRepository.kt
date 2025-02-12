package com.faisaldev.openkitchen.repositories

import com.faisaldev.openkitchen.dtos.Response
import com.faisaldev.openkitchen.network.ApiService
import javax.inject.Inject

class OnboardingRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun pingOpenKitchen(): Response<String> {
        return apiService.pingOpenApiKitchen()
    }


}
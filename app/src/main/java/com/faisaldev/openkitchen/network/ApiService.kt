package com.faisaldev.openkitchen.network;

import com.faisaldev.openkitchen.dtos.Response;

import kotlin.coroutines.*;
import retrofit2.http.POST;


interface ApiService {

    @POST("/ping")
    suspend fun pingOpenApiKitchen() : Response<String>

}

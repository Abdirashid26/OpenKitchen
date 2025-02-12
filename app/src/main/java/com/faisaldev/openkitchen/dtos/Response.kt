package com.faisaldev.openkitchen.dtos;

import kotlinx.serialization.Serializable


@Serializable
public data class Response<T>(
    val status : String,
    val message : String,
    val data : T
)

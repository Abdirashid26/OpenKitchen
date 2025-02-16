package com.faisaldev.openkitchen.utils

sealed class OpenKitchenState{
    object Loading : OpenKitchenState()
    data class Success<T>(val data: T) : OpenKitchenState()
    data class Error(val message: String) : OpenKitchenState()
}

object Global{
    var GLOBAL_STATUS = "Status : "
}

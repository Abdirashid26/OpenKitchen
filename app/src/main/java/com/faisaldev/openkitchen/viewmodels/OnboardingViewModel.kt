package com.faisaldev.openkitchen.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faisaldev.openkitchen.dtos.Response
import com.faisaldev.openkitchen.repositories.OnboardingRepository
import com.faisaldev.openkitchen.utils.OpenKitchenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val repository: OnboardingRepository) : ViewModel() {

    private val _pingState = MutableStateFlow<OpenKitchenState>(OpenKitchenState.Loading)
    val pingState: StateFlow<OpenKitchenState> = _pingState.asStateFlow()

    fun pingOpenKitchen() {
        viewModelScope.launch {
            try {
                val response = repository.pingOpenKitchen()
                if (response.status.equals("00",true)){
                    _pingState.value = OpenKitchenState.Success(response.data)
                }else{
                    _pingState.value = OpenKitchenState.Error(response.message)
                }
                println("Open Kitchen Ping response: ${response.message}") // Replace with LiveData or StateFlow
            } catch (e: Exception) {
                _pingState.value = OpenKitchenState.Error("Sorry we could not complete your request. Please try again")
                println("Error Pinging Open Kitchen: ${e.message}")
            }
        }
    }
}
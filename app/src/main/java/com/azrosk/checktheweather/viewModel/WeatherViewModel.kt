package com.azrosk.checktheweather.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azrosk.checktheweather.api.ApiService
import com.azrosk.checktheweather.models.WeatherResponse
import com.azrosk.checktheweather.repository.WeatherRepository
import com.azrosk.checktheweather.utilities.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(private val weatherRepository: WeatherRepository) : ViewModel()
{
    private val _response =MutableLiveData<WeatherResponse>()
    val responseWeather : LiveData<WeatherResponse>
        get() =_response

    init {
        getWeather()
    }

    private fun getWeather() = viewModelScope.launch {
        weatherRepository.getWeather().let { response ->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }
            else{
                Log.e("response_error", response.message())
            }
        }
    }

}
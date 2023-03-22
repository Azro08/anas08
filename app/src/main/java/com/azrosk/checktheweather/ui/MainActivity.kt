package com.azrosk.checktheweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.azrosk.checktheweather.databinding.ActivityMainBinding
import com.azrosk.checktheweather.models.WeatherResponse
import com.azrosk.checktheweather.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding?=null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.responseWeather.observe(this){ weather ->
            getWeather(weather)
        }
    }

    private fun getWeather(weather: WeatherResponse) {
        binding.apply {
            val sunrise = weather.sys.sunrise.toLong()
            val sunset = weather.sys.sunset.toLong()
            tvWeatherStat.text = weather.weather[0].description
            tvHumidity.text = weather.main.humidity.toString()
            tvPressure.text = weather.main.pressure.toString()
            tvSunrise.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise*1000))
            tvSunset.text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset*1000))
            tvWind.text = weather.wind.speed.toString()
            "${weather.main.temp.toInt()}°C".also { tvTemp.text = it }
            "Max temp. \n${weather.main.tempMax} °C".also { tvMaxTemp.text = it }
            "Min temp. \n${weather.main.tempMin} °C".also { tvMinTemp.text = it }
            tvLocation.text = weather.name
            val updatedAt: Long = weather.dt.toLong()
            val updatedAtText =
                "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                    Date(updatedAt * 1000)
                )
            tvUpdatedAt.text = updatedAtText
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
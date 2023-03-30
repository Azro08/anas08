package com.azrosk.checktheweather.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.azrosk.checktheweather.R
import com.azrosk.checktheweather.adapter.RecyclerViewAdapter
import com.azrosk.checktheweather.databinding.ActivityMainBinding
import com.azrosk.checktheweather.api.entity.WeatherResponse
import com.azrosk.checktheweather.models.WeatherStatus
import com.azrosk.checktheweather.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter: RecyclerViewAdapter? = null
    private val viewModel: WeatherViewModel by viewModels()
    private val weatherStatus = ArrayList<WeatherStatus>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.responseWeather.observe(this) { weather ->
            getWeather(weather)
        }
    }

    private fun getWeather(weather: WeatherResponse) {
        binding.apply {
            setStat(weather)
            Log.d("response", weather.toString())
            tvWeatherStat.text = weather.weather[0].description
            "${weather.main.temp.toInt()}°C".also { tvTemp.text = it }
            "Max temp. \n${weather.main.temp_max} °C".also { tvMaxTemp.text = it }
            "Min temp. \n${weather.main.temp_min} °C".also { tvMinTemp.text = it }
            tvLocation.text = weather.name
            val updatedAt: Long = weather.dt.toLong()
            val updatedAtText =
                "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                    Date(updatedAt * 1000)
                )
            tvUpdatedAt.text = updatedAtText
        }

    }

    private fun setStat(weather: WeatherResponse) {
        val sunrise = weather.sys.sunrise.toLong()
        val sunset = weather.sys.sunset.toLong()
        val sunriseFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
        val sunsetFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))

        weatherStatus.add(
            WeatherStatus(
                R.drawable.humidity,
                getString(R.string.humidity),
                weather.main.humidity.toString()
            )
        )
        weatherStatus.add(
            WeatherStatus(
                R.drawable.pressure,
                getString(R.string.pressure),
                weather.main.pressure.toString()
            )
        )
        weatherStatus.add(
            WeatherStatus(
                R.drawable.sunrise,
                getString(R.string.sunrise),
                sunriseFormat
            )
        )
        weatherStatus.add(
            WeatherStatus(
                R.drawable.sunset,
                getString(R.string.sunset),
                sunsetFormat
            )
        )
        weatherStatus.add(
            WeatherStatus(
                R.drawable.wind,
                getString(R.string.wind),
                weather.wind.speed.toString()
            )
        )
        weatherStatus.add(WeatherStatus(R.drawable.info, "Made by", "Anas"))

        rvAdapter = RecyclerViewAdapter(weatherStatus)
        binding.rvStatus.layoutManager = GridLayoutManager(this, 3)
        binding.rvStatus.setHasFixedSize(true)
        binding.rvStatus.adapter = rvAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
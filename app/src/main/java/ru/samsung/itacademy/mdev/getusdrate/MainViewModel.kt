package ru.samsung.itacademy.mdev.getusdrate

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val usdRate = MutableLiveData<String>()
    private val rateCheckInteractor = RateCheckInteractor()

    fun onCreate() {
        refreshRate()
    }

    fun onRefreshClicked() {
        refreshRate()
    }

    private fun refreshRate() {
        GlobalScope.launch(Dispatchers.Main) {
            val rate = rateCheckInteractor.requestRate()
            Log.d(TAG, "usdRate = $rate")
            usdRate.value = rate
        }
    }

    companion object {
        const val TAG = "MainViewModel"
        const val USD_RATE_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=USD&tsyms=RUB&api_key=${R.string.API_KEY}"
    }
}
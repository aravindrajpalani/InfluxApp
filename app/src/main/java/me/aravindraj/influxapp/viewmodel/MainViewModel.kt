package me.aravindraj.influxapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import me.aravindraj.influxapp.data.repository.MainRepository
import me.aravindraj.influxapp.utils.Resource

class MainViewModel(application: Application,private val mainRepository: MainRepository): AndroidViewModel(application) {

    fun getFoodData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getFoodData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
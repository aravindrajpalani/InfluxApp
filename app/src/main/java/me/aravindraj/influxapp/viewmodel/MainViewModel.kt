package me.aravindraj.influxapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import me.aravindraj.influxapp.data.model.Fnblist
import me.aravindraj.influxapp.data.model.FoodList
import me.aravindraj.influxapp.data.model.SelectedFood
import me.aravindraj.influxapp.data.repository.MainRepository
import me.aravindraj.influxapp.utils.Resource
import java.math.BigDecimal

class MainViewModel(application: Application, private val mainRepository: MainRepository) :
    AndroidViewModel(application) {

    var grandTotal = MutableLiveData<BigDecimal>(BigDecimal("0.0"))
    var fnblist = MutableLiveData<MutableList<Fnblist>>(mutableListOf())
    var foodData: FoodList? = null

    fun setGrandTotal() {
        var total = BigDecimal("0.0")
        fnblist.value?.filter { it.quantity > 0 }?.forEach {
            total = total.plus((it.ItemPrice.toBigDecimal().multiply(it.quantity.toBigDecimal())))
        }
        grandTotal.value = total
    }

    fun getFoodData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            foodData = mainRepository.getFoodData()
            foodData?.FoodList?.forEach {
                fnblist.value?.addAll(it.fnblist)
            }
            emit(Resource.success(data = foodData))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun onFoodAdd(foodItemId: String) {
        val food = fnblist.value?.find { it.VistaFoodItemId == foodItemId }
        if (food != null) {
            food.quantity = food.quantity + 1
            setGrandTotal()
        }
    }

    fun onFoodRemove(foodItemId: String) {
        val food = fnblist.value?.find { it.VistaFoodItemId == foodItemId }
        if (food != null && food.quantity > 0) {
            food.quantity = food.quantity - 1
            setGrandTotal()
        }
    }

    fun onSubFoodItemChanged(
        foodItemId: String,
        subFoodItemId: String
    ) {
        val food = fnblist.value?.find { it.VistaFoodItemId == foodItemId }
        if (food != null) {
            food.selectedSubFoodItemId = subFoodItemId
            setGrandTotal()
        }

    }
}
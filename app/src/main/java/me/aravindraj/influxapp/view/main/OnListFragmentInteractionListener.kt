package me.aravindraj.influxapp.view.main

interface OnListFragmentInteractionListener {
    fun onFoodAdd(foodItemId: String)
    fun onFoodRemove(foodItemId: String)
    fun onSubFoodItemChanged(foodItemId: String,subFoodItemId: String)
}
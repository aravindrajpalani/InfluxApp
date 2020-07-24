package me.aravindraj.influxapp.view

interface OnListFragmentInteractionListener {
    fun onFoodAdd(foodItemId: String)
    fun onFoodRemove(foodItemId: String)
    fun onSubFoodItemChanged(foodItemId: String,subFoodItemId: String)
}
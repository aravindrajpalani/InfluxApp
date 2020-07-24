package me.aravindraj.influxapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.aravindraj.influxapp.R
import me.aravindraj.influxapp.data.model.FoodBeveragesItem
import me.aravindraj.influxapp.viewmodel.MainViewModel

class FoodFragment(private val foodBeveragesList: List<FoodBeveragesItem>) : Fragment() {


    private val mainViewModel: MainViewModel by activityViewModels()


    // TODO: Customize parameters
    private var columnCount = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = FoodListAdapter(
                    context,
                    foodBeveragesList,
                    object : OnListFragmentInteractionListener {
                        override fun onFoodAdd(foodItemId: String) {
                            mainViewModel.onFoodAdd(foodItemId)

                        }

                        override fun onFoodRemove(foodItemId: String) {
                        }

                        override fun onSubFoodItemChanged(
                            foodItemId: String,
                            subFoodItemId: String,
                            subFoodItemName: String,
                            subFoodItemPrice: String
                        ) {
                        }

                    })
            }
        }
        return view
    }


    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
//        @JvmStatic
//        fun newInstance(columnCount: Int) =
//            FoodFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
//            }
    }
}

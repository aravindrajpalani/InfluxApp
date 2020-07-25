package me.aravindraj.influxapp.view.bottomslider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_food_summary_list.view.*
import me.aravindraj.influxapp.R
import me.aravindraj.influxapp.data.model.Fnblist

class FoodSummaryAdapter(
    private var mValues: List<Fnblist>
) : RecyclerView.Adapter<FoodSummaryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_summary_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mNameTxtView.text = "${item.Name} (${item.quantity})"
        holder.mPriceTxtView.text = item.ItemPrice.toBigDecimal().multiply(item.quantity.toBigDecimal()).toString()
    }

    override fun getItemCount(): Int = mValues.size

    fun updateList(list: List<Fnblist>) {
        mValues = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mNameTxtView: TextView = mView.txtViewName
        val mSubItemNameTxtView: TextView = mView.txtViewSubItemName
        val mPriceTxtView: TextView = mView.txtViewPrice
    }
}
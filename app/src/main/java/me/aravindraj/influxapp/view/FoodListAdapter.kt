package me.aravindraj.influxapp.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.request.RequestOptions
import me.aravindraj.influxapp.R


import me.aravindraj.influxapp.view.FoodFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.item_food_list.view.*
import me.aravindraj.influxapp.data.model.Fnblist

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class FoodListAdapter(
    private val context: Context,
    private val mValues: List<Fnblist>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.Name
        holder.mContentView.text = item.ItemPrice
        if(item.ImgUrl.isNotEmpty()&& context != null){

                Glide.with(context!!)
                    .load(item.ImgUrl)
                    .transform(   CenterCrop(),GranularRoundedCorners(30f, 30f, 0f, 0f))
                    .into(holder.mImgView)


        }

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.txtViewName
        val mContentView: TextView = mView.txtViewPrice
        val mImgView: ImageView = mView.imageView
        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}

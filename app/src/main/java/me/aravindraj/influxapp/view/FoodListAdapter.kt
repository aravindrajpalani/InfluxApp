package me.aravindraj.influxapp.view


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import kotlinx.android.synthetic.main.item_food_list.view.*
import me.aravindraj.influxapp.R
import me.aravindraj.influxapp.data.model.Fnblist


/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class FoodListAdapter(
    private val context: Context,
    private val mValues: List<Fnblist>,
    private val mListener: OnListFragmentInteractionListener
) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mNameTxtView.text = item.Name
        holder.mCountTxtView.text = item.quantity.toString()
        if (item.ImgUrl.isNotEmpty() && context != null) {
            Glide.with(context!!)
                .load(item.ImgUrl)
                .transform(CenterCrop(), GranularRoundedCorners(30f, 30f, 0f, 0f))
                .into(holder.mImgView)
        }
        if (item.subitems.isEmpty()) {
            holder.mRadioGroup.visibility = GONE
        } else {
            holder.mRadioGroup.visibility = VISIBLE
            item.ItemPrice = item.subitems[0].SubitemPrice
            item.subitems.forEachIndexed { index, it ->
                holder.mRadioGroup.visibility = VISIBLE
                val mRadioButton = RadioButton(context)
                mRadioButton.setBackgroundResource(R.drawable.ic_txt_bg_selector)
                mRadioButton.setButtonDrawable(null)
                mRadioButton.setTextColor(context.getColorStateList(R.color.ic_txt_color_selector));
                mRadioButton.setText(it.Name)
                mRadioButton.isAllCaps = true
                val params = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(12, 8, 12, 8)
                mRadioButton.layoutParams = params
                mRadioButton.id = index
                mRadioButton.tag = it.VistaSubFoodItemId
                if (index == 0) {
                    mRadioButton.isChecked = true
                }
                holder.mRadioGroup.addView(mRadioButton)
            }
        }
        holder.mPriceTxtView.text = item.ItemPrice
        holder.mPlusBtn.setOnClickListener {
            holder.mCountTxtView.text = (item.quantity+1).toString()
            mListener.onFoodAdd(item.VistaFoodItemId)
        }
        holder.mMinusBtn.setOnClickListener {
            if (item.quantity > 0) {
                holder.mCountTxtView.text = (item.quantity-1).toString()
                mListener.onFoodRemove(item.VistaFoodItemId)
            }

        }
        holder.mRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            Log.e("RadioGroup", "=" + checkedId)
            item.ItemPrice = item.subitems[checkedId].SubitemPrice
            holder.mPriceTxtView.text = item.ItemPrice
            item.selectedSubFoodItemId = item.subitems[checkedId].VistaSubFoodItemId
            mListener.onSubFoodItemChanged(item.VistaFoodItemId, item.selectedSubFoodItemId)
        }


    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mNameTxtView: TextView = mView.txtViewName
        val mPriceTxtView: TextView = mView.txtViewPrice
        val mImgView: ImageView = mView.imageView
        val mPlusBtn: ImageButton = mView.btnPlus
        val mMinusBtn: ImageButton = mView.btnMinus
        val mCountTxtView: TextView = mView.txtCount
        val mRadioGroup: RadioGroup = mView.radioGroup
    }
}

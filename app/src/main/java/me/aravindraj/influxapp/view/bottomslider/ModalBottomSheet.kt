package me.aravindraj.influxapp.view.bottomslider

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.modal_bottom_sheet.*
import me.aravindraj.influxapp.R
import me.aravindraj.influxapp.viewmodel.MainViewModel

class ModalBottomSheet : BottomSheetDialogFragment() {
    private lateinit var foodSummaryAdapter: FoodSummaryAdapter
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.modal_bottom_sheet, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val modalBottomSheet = super.onCreateDialog(savedInstanceState)
        val modalBottomSheetBehavior = (modalBottomSheet as BottomSheetDialog).behavior
//        modalBottomSheetBehavior.setPeekHeight(300)
//        modalBottomSheetBehavior.expandedOffset = 200
//        modalBottomSheetBehavior.isFitToContents = false
//        modalBottomSheetBehavior.halfExpandedRatio = 0.1f
        modalBottomSheet.setOnShowListener {

        }
        return modalBottomSheet
    }

    override fun onStart() {
        super.onStart()
        foodSummaryAdapter = FoodSummaryAdapter(mutableListOf())
        with(list) {
            layoutManager = LinearLayoutManager(context)
            adapter = foodSummaryAdapter
        }
        mainViewModel.grandTotal.observe(this, Observer {
            grandTotalTxtView.setText("AED $it")
        })
        mainViewModel.fnblist.observe(this, Observer {
            Log.e("fnblist","="+it.filter { it.quantity>0 }.size)
            foodSummaryAdapter.updateList(it.filter { it.quantity>0 })

        })
        grandTotalTxtView.setOnClickListener { this.dismiss() }

    }
}

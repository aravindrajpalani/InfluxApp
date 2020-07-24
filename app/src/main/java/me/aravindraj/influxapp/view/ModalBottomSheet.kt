package me.aravindraj.influxapp.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.aravindraj.influxapp.R

class ModalBottomSheet : BottomSheetDialogFragment() {

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
        modalBottomSheetBehavior.setPeekHeight(300)
        modalBottomSheetBehavior.expandedOffset = 200
        modalBottomSheetBehavior.isFitToContents = false
        modalBottomSheetBehavior.halfExpandedRatio = 0.6f
        modalBottomSheet.setOnShowListener {

        }
        return modalBottomSheet

    }
}

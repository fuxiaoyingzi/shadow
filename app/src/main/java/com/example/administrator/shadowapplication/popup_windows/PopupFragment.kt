package com.example.administrator.shadowapplication.popup_windows


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.fragemnt_popup.*
import kotlinx.android.synthetic.main.layout_pupup_view.view.*

/**
 * Author : shadow
 * Desc :
 * Date :2018/6/7/007
 */

class PopupFragment : Fragment() {
    lateinit var switcher: PopupWindowUtil
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragemnt_popup, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val view = LayoutInflater.from(activity).inflate(R.layout.layout_pupup_view, null)
        view.dismissPopup.setOnClickListener { switcher.dismissPopup() }
        showPopup.setOnClickListener {
            switcher = PopupWindowUtil(activity, view)
            switcher.showAsDropDown(showPopup)
        }
    }
}

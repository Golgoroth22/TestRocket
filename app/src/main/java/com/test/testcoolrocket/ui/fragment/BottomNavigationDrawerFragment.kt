package com.test.testcoolrocket.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.test.testcoolrocket.R
import com.test.testcoolrocket.di.Scopes
import com.test.testcoolrocket.ui.activities.interfaces.SaveChartCallback
import kotlinx.android.synthetic.main.drawer_layout.*
import toothpick.Toothpick


class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    private val saveChartCallback =
        Toothpick.openScope(Scopes.DRAWER).getInstance(SaveChartCallback::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drawer_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        drawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.drawer_enter_new_points -> {
                    NewPointsDialogFragment().also {
                        it.show(activity!!.supportFragmentManager, it.tag)
                    }
                    this.dismiss()
                }
                R.id.drawer_save_image -> {
                    saveChartCallback.tryToSaveChart()
                    this.dismiss()
                }
            }
            true
        }
    }


}
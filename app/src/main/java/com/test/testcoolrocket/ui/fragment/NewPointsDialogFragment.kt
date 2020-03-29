package com.test.testcoolrocket.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.DialogFragment
import com.test.testcoolrocket.R
import com.test.testcoolrocket.di.Scopes
import com.test.testcoolrocket.repositories.interfaces.NewRequestListener
import com.test.testcoolrocket.repositories.PointsRepository
import kotlinx.android.synthetic.main.dialog_new_points_fragment.view.*
import timber.log.Timber
import toothpick.Toothpick
import java.lang.Exception

class NewPointsDialogFragment : DialogFragment() {
    private val requestListener: NewRequestListener =
        Toothpick.openScope(Scopes.APP).getInstance(PointsRepository::class.java)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val rootView = activity!!.layoutInflater.inflate(R.layout.dialog_new_points_fragment, null)
        initViews(rootView)
        builder.setView(rootView)
        return builder.create()
    }

    private fun initViews(rootView: View) {
        rootView.dialog_new_points_fragment_countEditText.addTextChangedListener(
            createWatcher(rootView)
        )
        rootView.dialog_new_points_fragment_acceptButton.setOnClickListener {
            requestListener.sendNewRequest(
                rootView.dialog_new_points_fragment_countEditText.text.toString().toInt()
            )
            this.dismiss()
        }
        rootView.dialog_new_points_fragment_dismissButton.setOnClickListener {
            this.dismiss()
        }
    }

    private fun createWatcher(rootView: View) = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            try {
                val count =
                    rootView.dialog_new_points_fragment_countEditText.text.toString().toInt()
                rootView.dialog_new_points_fragment_acceptButton.isEnabled = count in 1..200
            } catch (e: Exception) {
                Timber.e("NewPointsDialogFragment createWatcher onTextChanged ${e.localizedMessage}")
            }
        }
    }
}
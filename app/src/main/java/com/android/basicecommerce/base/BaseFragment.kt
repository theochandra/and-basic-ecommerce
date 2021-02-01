package com.android.basicecommerce.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun showAlertMessage(title: String, message: String, btnLabel: String) {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(btnLabel) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

}
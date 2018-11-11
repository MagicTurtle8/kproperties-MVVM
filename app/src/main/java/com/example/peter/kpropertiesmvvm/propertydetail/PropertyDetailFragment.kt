package com.example.peter.kpropertiesmvvm.propertydetail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.peter.kpropertiesmvvm.PropertyViewModel

import com.example.peter.kpropertiesmvvm.R

import kotlinx.android.synthetic.main.fragment_property_detail.*

/**
 * This fragment displays a specific a property id from which a user has selected from the property list
 */
class PropertyDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_property_detail, container, false)

        // Get a new or existing ViewModel from the ViewModelProvider
        val propertyViewModel = ViewModelProviders
            .of(this.activity!!)
            .get(PropertyViewModel::class.java)

        // Observe for changes to the selected property Id and if so, update the text view text
        propertyViewModel.getPropertyId().observe(this, Observer {
            text_view_property_id.text = it
        })

        return view
    }

}

package com.example.peter.kpropertiesmvvm.propertydetail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.peter.kpropertiesmvvm.PropertyViewModel

import com.example.peter.kpropertiesmvvm.databinding.FragmentPropertyDetailBinding


/**
 * This fragment displays a specific a property id from which a user has selected from the property list
 */
class PropertyDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragmentPropertyDetailBinding = FragmentPropertyDetailBinding.inflate(layoutInflater, container, false)

        // Get a new or existing ViewModel from the ViewModelProvider
        val propertyViewModel = ViewModelProviders
            .of(this.activity!!)
            .get(PropertyViewModel::class.java)


        // Observe for changes to the selected property Id and if so, update the property Id variable in the layout
        propertyViewModel.getPropertyId().observe(this, Observer {
            fragmentPropertyDetailBinding.propertyId = it
        })

        return fragmentPropertyDetailBinding.root
    }

}

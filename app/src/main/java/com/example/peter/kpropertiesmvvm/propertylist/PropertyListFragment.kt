package com.example.peter.kpropertiesmvvm.propertylist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.peter.kpropertiesmvvm.R
import android.support.v7.widget.LinearLayoutManager
import com.example.peter.kpropertiesmvvm.PropertyViewModel
import com.example.peter.kpropertiesmvvm.repository.model.Property
import java.util.ArrayList

import kotlinx.android.synthetic.main.fragment_property_list.*
import kotlinx.android.synthetic.main.fragment_property_list.view.*

/**
 * This fragment displays a list of all the properties in a RecyclerView
 */
class PropertyListFragment : Fragment() {

    private var propertyViewModel: PropertyViewModel? = null
    private var properties = ArrayList<Property>()
    private var propertyAdapter: PropertyAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_property_list, container, false)

        val activity = activity!!

        // Get a new or existing ViewModel from the ViewModelProvider
        propertyViewModel = ViewModelProviders
            .of(this.activity!!)
            .get(PropertyViewModel::class.java)


        // RecyclerView setup
        // We can assume propertyViewModel to not be null as the ViewModelProviders would have
        // retrieved or generated a new one.
        propertyAdapter = PropertyAdapter(properties, activity, propertyViewModel!!)
        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = propertyAdapter

        // Observe for property listing changes
        propertyViewModel?.getPropertyData()?.observe(this, Observer { it ->
            if (it != null){
                // Update UI
                properties = it
                recycler_view.adapter = PropertyAdapter(properties, activity, propertyViewModel!!)
                progressBarVisible(false)
            }
        })


        return view
    }

    private fun progressBarVisible(showBar : Boolean) {
        if (showBar) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

}

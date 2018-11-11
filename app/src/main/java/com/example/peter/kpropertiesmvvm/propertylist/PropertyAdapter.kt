package com.example.peter.kpropertiesmvvm.propertylist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.peter.kpropertiesmvvm.PropertyViewModel
import com.example.peter.kpropertiesmvvm.databinding.ListItemPropertyBinding
import com.example.peter.kpropertiesmvvm.databinding.ListItemPropertyPremiumBinding
import com.example.peter.kpropertiesmvvm.repository.model.Property




/**
 * RecyclerView adapter for property listings
 */
class PropertyAdapter(private val propertyList: ArrayList<Property>, val context: Context, private val viewModel: PropertyViewModel): RecyclerView.Adapter<PropertyViewHolder>() {

    // Determine if the item is premium or regular
    // Type 0 for regular and 1 for premium
    override fun getItemViewType(position: Int): Int {
        return if (!propertyList[position].premium) {
            0
        } else {
            1
        }
    }

    // Inflate view based on view type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {

        return if (viewType == 0) {
            val propertyBinding = ListItemPropertyBinding.inflate(LayoutInflater.from(context), parent, false)
            PropertyViewHolder(propertyBinding)
        } else {
            val propertyBinding = ListItemPropertyPremiumBinding.inflate(LayoutInflater.from(context), parent, false)
            PropertyViewHolder(propertyBinding)
        }
    }

    // Bind the view of the holder through Data Binding
    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {

        val property = propertyList[position]

        // Check if holder is regular or premium
        if (holder.itemViewType == 0) {
            holder.setRegularData(property)
        } else {
            holder.setPremiumData(property)
        }

        /**
        holder.descriptionTextView.text = propertyList[position].description
        holder.priceTextView.text = context.getString(R.string.prefix_dollar_sign, propertyList[position].displayPrice)
        holder.address1TextView.text = propertyList[position].address1
        holder.address2TextView.text = propertyList[position].address2
        holder.bedroomsTextView.text = propertyList[position].bedrooms.toString()
        holder.bathroomsTextView.text = propertyList[position].bathrooms.toString()
        holder.carSpacesTextView.text = propertyList[position].carSpace.toString()

        holder.rootCardView.setOnClickListener {
            // Get the viewModel to set the propertyId into its repository. Which will then update
            // the viewModel's LiveData and hence notifying any views that are observing.
            viewModel.updatePropertyId(propertyList[position].id)
        }


        Glide.with(context)
            .load(propertyList[position].imageUrl)
            .into(holder.imageView)
        **/
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }
}


class PropertyViewHolder : RecyclerView.ViewHolder {

    private var regularBinding: ListItemPropertyBinding? = null
    private var premiumBinding: ListItemPropertyPremiumBinding? = null

    constructor(binding: ListItemPropertyBinding) : super(binding.root) {
        regularBinding = binding
    }

    constructor(binding: ListItemPropertyPremiumBinding) : super(binding.root) {
        premiumBinding = binding
    }


    fun setRegularData(property: Property){
        regularBinding?.property  = property
    }

    fun setPremiumData(property: Property){
        premiumBinding?.property  = property
    }

}


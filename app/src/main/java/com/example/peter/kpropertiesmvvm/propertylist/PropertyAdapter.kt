package com.example.peter.kpropertiesmvvm.propertylist

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.peter.kpropertiesmvvm.PropertyViewModel
import com.example.peter.kpropertiesmvvm.R
import com.example.peter.kpropertiesmvvm.repository.model.Property

import kotlinx.android.synthetic.main.list_item_property.view.*

/**
 * RecyclerView adapter for property listings
 */
class PropertyAdapter(private val propertyList: ArrayList<Property>, val context: Context, private val viewModel: PropertyViewModel): RecyclerView.Adapter<ViewHolder>() {

    // Determine if the item is premium or regular
    override fun getItemViewType(position: Int): Int {
        return if (!propertyList[position].premium) {
            0
        } else {
            1
        }
    }

    // Inflate view based on premium status
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var holder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_property, parent, false)
        )

        if (viewType == 1) {
            holder =
                    ViewHolder(
                        LayoutInflater.from(context).inflate(
                            R.layout.list_item_property_premium,
                            parent,
                            false
                        )
                    )
        }
        return holder
    }

    // Bind the view of the holder through Data Binding
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val descriptionTextView: TextView = view.text_view_description
    val imageView: ImageView = view.image_view
    val priceTextView: TextView = view.text_view_price
    val address1TextView: TextView = view.text_view_address
    val address2TextView: TextView = view.text_view_address_2
    val bedroomsTextView: TextView = view.text_view_num_bedrooms
    val bathroomsTextView: TextView = view.text_view_num_bathrooms
    val carSpacesTextView: TextView = view.text_view_num_car_spaces
    val rootCardView: CardView = view.card_view_root

}
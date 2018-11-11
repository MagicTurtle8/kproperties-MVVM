package com.example.peter.kpropertiesmvvm.repository

import android.arch.lifecycle.MutableLiveData
import com.example.peter.kpropertiesmvvm.repository.model.Property
import com.example.peter.kpropertiesmvvm.repository.model.Model
import com.example.peter.kpropertiesmvvm.repository.remote.PropertyApi
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

/**
 * Handles the network call and also performs operations to prepare data for the ViewModel.
 */
class PropertyRepository {

    private var mRetrofit: Retrofit? = null
    private val propertyResponse: MutableLiveData<ArrayList<Property>> = MutableLiveData()
    private var selectedId: MutableLiveData<String> = MutableLiveData()

    private fun getRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://sentia-test.herokuapp.com/")
                .build()
        }
        return mRetrofit
    }


    private fun getAPIInstance(): PropertyApi? {
        return getRetrofit()?.create(PropertyApi::class.java)
    }

    // Call the latest property list, format and return a list of properties
    fun loadPropertyData(): MutableLiveData<ArrayList<Property>>{
        getAPIInstance()?.getProperties()?.enqueue(object: retrofit2.Callback<Model.Result> {

            override fun onResponse(call: Call<Model.Result>, response: Response<Model.Result>) {

                val properties = ArrayList<Property>()
                val result = response.body()
                for (listing in result?.data?.listings!!) {
                    val property: Property = createProperty(listing)
                    properties.add(property)
                }
                propertyResponse.value = properties
            }

            override fun onFailure(call: Call<Model.Result>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return propertyResponse
    }


    // Sets the id that the user has selected.
    fun setSelectedId(id: String){
        selectedId.value = id
    }

    // Return the last saved user selected property id
    fun getSavedPropertyId(): MutableLiveData<String> {
        return selectedId
    }


    private fun createProperty(listing: Model.Listings): Property {
        val id  = listing.Id
        val area = listing.Area
        val bedrooms = listing.Bedrooms
        val bathrooms = listing.Bathrooms
        val carSpaces = listing.Carspaces
        val description = listing.Description
        val displayPrice = listing.DisplayPrice
        val imageUrl = listing.owner.image.medium.url
        val address = listing.Location.Address
        val address2 = listing.Location.Address2
        val suburb = listing.Location.Suburb
        val state = listing.Location.State
        val premiumValue = listing.is_premium
        var premium = false

        if (premiumValue == 1) {
            premium = true
        }

        return Property(
            id,
            area,
            bedrooms,
            bathrooms,
            carSpaces,
            description,
            displayPrice,
            imageUrl,
            address,
            address2,
            suburb,
            state,
            premium
        )
    }

}

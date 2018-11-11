package com.example.peter.kpropertiesmvvm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.peter.kpropertiesmvvm.repository.PropertyRepository
import com.example.peter.kpropertiesmvvm.repository.model.Property

/**
 * ViewModel to keep a reference to the property repository and an up-to-date list of all properties.
 *
 * The ViewModel does not know about view and also because the data here is cached,
 * therefore is not affected by configuration changes such as recreating activity due to rotation since
 * it is always receiving the original ViewModel.
 *
 * Operations is done in the repository, so the viewModel is nice and simple.
 */
class PropertyViewModel : ViewModel() {

    private val repository: PropertyRepository = PropertyRepository()

    fun getPropertyData(): MutableLiveData<ArrayList<Property>> {
        return repository.loadPropertyData()
    }

    fun getPropertyId(): MutableLiveData<String> {
        return repository.getSavedPropertyId()
    }

    fun updatePropertyId(id: String){
        return repository.setSelectedId(id)
    }

}





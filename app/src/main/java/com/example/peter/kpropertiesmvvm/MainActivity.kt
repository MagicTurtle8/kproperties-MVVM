package com.example.peter.kpropertiesmvvm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.peter.kpropertiesmvvm.propertydetail.PropertyDetailFragment
import com.example.peter.kpropertiesmvvm.propertylist.PropertyListFragment


/**
 *
 * With the combination of ViewModelProviders, which retrieves/generates the viewModel and by using LiveData to observe
 * for changes, this allows for data to survive configuration changes.
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // The R.layout.activity_masterdetail is a alias that points to the single fragment layout
        // for regular devices, and two-pane fragment layout for tablets
        setContentView(R.layout.activity_masterdetail)

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)
        var fragmentDetail = fm.findFragmentById(R.id.detail_fragment_container)

        // Get the fragment manager to display the property list fragment
        if (fragment == null) {
            fragment = PropertyListFragment()
            fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

        // If detail fragment is not null, that means a two-pane layout is used (tablets)
        // so get the fragment manger to display the detail fragment
        if (findViewById<FrameLayout>(R.id.detail_fragment_container) != null) {
            if (fragmentDetail == null) {
                fragmentDetail = PropertyDetailFragment()
                fm.beginTransaction()
                    .add(R.id.detail_fragment_container, fragmentDetail)
                    .commit()
            }
        }

    }
}

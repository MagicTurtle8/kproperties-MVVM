# kproperties-MVVM
A simple Android Application that parses a JSON feed and displays the list and detail (for tablets) on to specific Fragments.

This app aims to demonstrate:
- Model–view–viewmodel (MVVM) Architecture
- Handle orientation change
- Support devices with wide screens (tablets)
- Basic Data Binding

## App Architecture Design
The app UI consists of one fragment for regular devices and 2 fragments for devices with wide screens, all of which are hosted by a single host Activity. The fragments include:

- List - Uses a RecyclerView to list all the properties found
- Details - Displays the property Id of the selected property from this list


General app architecture shown below, where arrow represents data flow:

![img](https://imgur.com/0Rnr32t.png)

## Handling Device Rotation
Since the viewModel (PropertyViewModel) contains a reference to its repository and is able to survive through the Andorid lifecycle, data will be retained on device rotation and managed through the ViewModelProvider.

## Supporting Tablets
Through the use of Fragments to display the UI, this give more design flexibility and also easily support tablets by delegating a specific layout for screens over a specific width (sw600dp chosen in this project).

Through the use of alias resource (a resource that points to another resource), we can:
- Point to the phone activity layout for screens less than sw600dp
- Point to the tablet activity layout for screens more equal to or more than sw600dp.

## Handling Fragment Communication
One of the main app function is when a user selects a property item from the list fragment, it will display that property ID onto the detail fragment. Instead of implementing Callbacks, the List Fragment get the ViewModel to update the reposity to update the last saved Property Id. However, at the same time, the Detail Fragment has been observing for changes to the property Id, so it will be notified and update the UI. 


## Libraries used
- Retrofit2
- Glide
- Android Architecture Components
- Common Android Support Libraries

## References
- https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live-kotlin/
- https://developer.android.com/jetpack/docs/guide
- Android Programming - The Big Nerd Ranch Guide 2nd Ed.

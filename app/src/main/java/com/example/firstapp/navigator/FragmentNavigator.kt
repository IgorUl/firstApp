package com.example.firstapp.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.firstapp.R
import com.example.firstapp.fragments.SortFragment
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.fragments.MainFragment

class FragmentNavigator(private val fragment: Fragment) : MainContract.FragmentNavigator {

    private val sortScreen = SortFragment()

    override fun navigateToMainView() {
        val fragmentManager: FragmentManager? = fragment.activity?.supportFragmentManager
        fragmentManager
            ?.popBackStack()
    }

    override fun navigateToSortView() {
        val fragmentManager: FragmentManager? = fragment.activity?.supportFragmentManager
        fragmentManager?.beginTransaction()
            ?.setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_in_right,
                R.animator.reverse_slide_in_right,
                R.animator.reverse_slide_in_left
            )
            ?.replace(R.id.main_container, sortScreen)
            ?.addToBackStack(null)
            ?.commit()
    }
}
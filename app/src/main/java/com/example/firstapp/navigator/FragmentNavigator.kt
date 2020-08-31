package com.example.firstapp.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.firstapp.R
import com.example.firstapp.fragments.SortFragment
import com.example.firstapp.contracts.MainContract

class FragmentNavigator(private val fragment: Fragment): MainContract.FragmentNavigator {

    private val sortScreen = SortFragment()

    override fun navigateToMainView() {
        val fragmentManager: FragmentManager? = fragment.activity?.supportFragmentManager
        fragmentManager?.popBackStack()
    }

    override fun navigateToSortView() {
        val fragmentManager: FragmentManager? = fragment.activity?.supportFragmentManager
        fragmentManager?.beginTransaction()
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.replace(R.id.main_container, sortScreen)
            ?.addToBackStack(null)
            ?.commit()
    }
}
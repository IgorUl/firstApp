package com.example.firstapp.contracts

import com.example.firstapp.activity.MainActivity

interface MainContract {

    interface MainView {
        fun enabledAddButton(state: Boolean)
        fun enableTextViewAndClearButton(state: Boolean)
        fun showStringToTextView(stringToShow: String)
        fun clearTextView()
        fun clearEditText()
        fun enableNextButton(state: Boolean)
    }

    interface SortView {
        fun updateSortButtonAndRadioGroup(state: Boolean)
        fun showStringToTextView(stringToShow: String)
    }

    interface MainPresenter {
        fun onClickAddButton()
        fun onClickClearButton()
        fun onClickNextButton(activity: MainActivity)
    }

    interface SortPresenter {
        fun onClickSortButton()
    }
}
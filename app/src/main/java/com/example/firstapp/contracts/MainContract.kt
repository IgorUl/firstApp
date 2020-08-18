package com.example.firstapp.contracts

interface MainContract {

    interface MainView {
        fun enabledAddButton(state: Boolean)
        fun enableTextViewAndClearButton(state: Boolean)
        fun showStringToTextView(stringToShow: String)
        fun clearTextView()
        fun clearEditText()
        fun enableNextButton(state: Boolean)

    }

    interface MainPresenter {
        fun onClickAddButton()
        fun onClickClearButton()
    }

    interface SortPresenter {
        fun onClickSortButton()
    }

    interface SortView {
        fun updateSortButtonAndRadioGroup(state: Boolean)
        fun showStringToTextView(stringToShow: String)
    }
}
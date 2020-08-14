package com.example.firstapp.contracts

interface MainContract {

    interface MainView {
        fun enabledAddButton(state: Boolean)
        fun updateSortButtonAndRadioGroup(state: Boolean)
        fun showStringToTextView(stringToShow: String)
        fun enableTextViewAndClearButton(state: Boolean)
        fun clearTextView()
        fun clearEditText()

    }

    interface MainPresenter {
        fun onClickAddButton()
        fun onClickSortButton()
        fun onClickClearButton()
    }
}
package com.example.firstapp

interface MainContract {

    interface MainView {
        fun toggleAddButtonAndTextView()
        fun updateSortButtonAndRadioGroup(state: Boolean)
        fun getStringFromEditText(): String
        fun showStringToTextView(string: String)
        fun enableTextViewAndClearButton()
        fun getSortType(): SortType
        fun clearTextView()
        fun changeButtonColor()
        fun clearEditText()
    }

    interface MainPresenter {
        fun add()
        fun sort()
        fun clear()
    }
}
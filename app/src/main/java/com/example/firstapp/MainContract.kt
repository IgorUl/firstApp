package com.example.firstapp

interface MainContract {

    interface MainView {
        fun toggleAddButton(state: Boolean)
        fun updateSortButtonAndRadioGroup(state: Boolean)
        fun getStringFromEditText(): String
        fun showStringToTextView()
        fun enableTextViewAndClearButton()
        fun getSortType(): SortType
        fun clearTextView()
        fun clearEditText()
        fun showSortedStringWithTimeStamps(
            startSortingTime: String,
            sortedString: String,
            endSortingTime: String,
            timeDifference: String
        )
    }

    interface MainPresenter {
        fun add()
        fun sort()
        fun clear()
    }
}
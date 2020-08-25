package com.example.firstapp.contracts


interface MainContract {

    interface MainView {
        fun updateAddButton(isEnable: Boolean)
        fun updateClearButtonVisibility(isVisible: Boolean)
        fun showStringToTextView(stringToShow: String)
        fun clearTextView()
        fun clearEditText()
        fun updateNextButton(isEnable: Boolean)
        fun showErrorMessage(messageId: Int)
    }

    interface MainScreenNavigator {
        fun navigateToSortScreen(comments: String)
    }

    interface SortView {
        fun setOutputText(stringToShow: String)
    }

    interface MainPresenter {
        fun onClickAddButton()
        fun onClickClearButton()
        fun onClickNextButton()
    }

    interface SortPresenter {
        fun onClickSortButton()
    }
}
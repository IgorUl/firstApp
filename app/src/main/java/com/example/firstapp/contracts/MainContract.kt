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
        fun showWrongCommentCountToast()
        fun setCommentCount(commentCount: Int)
    }

    interface SortView {
        fun setOutputText(stringToShow: String)
        fun showErrorSortMessage()
    }

    interface MainPresenter {
        fun onClickAddButton()
        fun onClickClearButton()
        fun onClickNextButton()
    }

    interface SortPresenter {
        fun onClickSortButton()
    }

    interface FragmentNavigator {
        fun navigateToMainView()
        fun navigateToSortView()
    }

    interface OnScreenChangeListener {
        fun onScreenChange()
    }
}
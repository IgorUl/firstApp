package com.example.firstapp.contracts


interface MainContract {

    interface MainView: Scroll {
        fun updateAddButton(isEnable: Boolean)
        fun updateClearButtonVisibility(isVisible: Boolean)
        fun showStringToTextView(stringToShow: String)
        fun clearTextView()
        fun clearEditText()
        fun clearCommentCount()
        fun updateNextButton(isEnable: Boolean)
        fun showErrorMessage(messageId: Int)
        fun showWrongCommentCountToast()
        fun setCommentCount(commentCount: Int)
        fun updateScrollUpVisibility(isVisible: Boolean)
        fun updateScrollDownVisibility(isVisible: Boolean)
    }

    interface SortView: Scroll {
        fun setOutputText(stringToShow: String)
        fun showErrorSortMessage()
    }

    interface Scroll {
        fun scrollDown()
        fun scrollUp()
    }

    interface MainPresenter: OnClickScrollListener {
        fun onClickAddButton()
        fun onClickClearButton()
        fun onClickNextButton()
    }

    interface SortPresenter: OnClickScrollListener {
        fun onClickSortButton()
    }

    interface OnClickScrollListener {
        fun onClickScrollUp()
        fun onClinkScrollDown()
    }

    interface FragmentNavigator {
        fun navigateToMainView()
        fun navigateToSortView()
    }

    interface OnScreenChangeListener {
        fun onScreenChange()
    }
}
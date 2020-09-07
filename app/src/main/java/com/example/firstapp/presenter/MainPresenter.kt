package com.example.firstapp.presenter

import androidx.core.text.isDigitsOnly
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.navigator.FragmentNavigator
import com.example.firstapp.data.Model
import org.jetbrains.annotations.TestOnly

class MainPresenter(
    private val view: MainContract.MainView,
    private val navigator: FragmentNavigator,
    private val model: Model
) :
    MainContract.MainPresenter {

    private var inputString = ""

    override fun onClickAddButton() {
        model.addToList(inputString)
        if (model.isListCanSort()) {
            view.updateNextButton(true)
        }
        showSavedComments()
        view.clearEditText()
        model.updateSortView()
        view.setCommentCount(model.getCommentListSize())
    }

    fun onClickGenerateButton(commentCountString: String) {
        if (commentCountString.isNotEmpty() && commentCountString.isDigitsOnly()) {
            val commentCount: Int = commentCountString.toInt()
            model.generateComments(commentCount)
            view.setCommentCount(model.getCommentListSize())
            showSavedComments()
            if (model.isListCanSort())
                view.updateNextButton(true)
            model.updateSortView()
            view.clearCommentCount()
        } else {
            view.showWrongCommentCountToast()
        }
    }

    override fun onClickNextButton() =
        navigator.navigateToSortView()

    override fun onClickClearButton() {
        model.clearStringList()
        view.clearTextView()
        view.setCommentCount(model.getCommentListSize())
        model.updateSortView()
        view.updateNextButton(false)
        view.updateClearButtonVisibility(false)
    }

    override fun onClickScrollUp() {
        view.scrollUp()
    }

    override fun onClinkScrollDown() {
        view.scrollDown()
    }

    fun setStringFromEditText(inputString: String) {
        this.inputString = inputString
        updateAddButton()
    }

    private fun updateAddButton() =
        view.updateAddButton(hasEnteredText())


    private fun hasEnteredText(): Boolean =
        inputString.isNotEmpty()

    @TestOnly
    fun showSavedComments() {
        if (!model.isListEmpty()) {
            view.showStringToTextView(model.getAllComment())
            view.updateClearButtonVisibility(true)
        } else {
            view.updateClearButtonVisibility(false)
            view.updateNextButton(false)
        }
    }

    fun onCreated() {
        model.readFile()
        if (model.getAllComment().isEmpty()) {
            showErrorMessage(R.string.comment_not_found)
        }
    }

    fun onPaused() {
        if (!model.writeFile()) {
            showErrorMessage(R.string.file_not_found_message)
        }
    }

    fun onStarted() {
        showSavedComments()
        view.setCommentCount(model.getCommentListSize())
        view.updateNextButton(model.isListCanSort())
    }

    @TestOnly
    fun showErrorMessage(messageId: Int) {
        view.showErrorMessage(messageId)
    }
}


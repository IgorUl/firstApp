package com.example.firstapp.presenter

import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model

class MainPresenter(
    private val view: MainContract.MainView,
    private val navigator: MainContract.MainScreenNavigator,
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
    }

    fun onClickGenerateButton() {
        val commentCount = 10 //todo удалю как добавлю поле
        if (commentCount > 0) {
            model.generateComments(commentCount)
            showSavedComments()
            view.updateNextButton(true)
        } else {
            view.showWrongCommentCountToast()
        }
    }

    override fun onClickNextButton() =
        navigator.navigateToSortScreen(model.getAllComment())

    override fun onClickClearButton() {
        model.clearStringList()
        view.clearTextView()
        view.updateNextButton(false)
        view.updateClearButtonVisibility(false)
    }

    fun setStringFromEditText(inputString: String) {
        this.inputString = inputString
        updateAddButton()
    }

    private fun updateAddButton() =
        view.updateAddButton(hasEnteredText())

    private fun hasEnteredText(): Boolean =
        inputString.isNotEmpty()

    private fun showSavedComments() {
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
            showErrorMessage(R.string.commentNotFound)
        }
    }

    fun onPause() {
        if (!model.writeFile()) {
            showErrorMessage(R.string.fileNotFoundMessage)
        }
    }

    fun onStart() {
        showSavedComments()
        view.updateNextButton(model.isListCanSort())
    }

    private fun showErrorMessage(messageId: Int) {
        view.showErrorMessage(messageId)
    }
}


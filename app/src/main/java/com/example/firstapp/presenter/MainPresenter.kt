package com.example.firstapp.presenter

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
    private var commentCount = 0

    override fun onClickAddButton() {
        model.addToList(inputString)
        if (model.isListCanSort()) {
            view.updateNextButton(true)
        }
        showSavedComments()
        view.clearEditText()
    }

    fun onClickGenerateButton() {
        if (commentCount > 0) {
            model.generateComments(commentCount)
            showSavedComments()
            view.updateNextButton(true)
        } else {
            view.showWrongCommentCountToast()
        }
    }

    override fun onClickNextButton() =
        navigator.navigateToSortView()

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

    fun setNumberFromEditText(inputString: String) {
        if (inputString.isNotEmpty()) {
            this.commentCount = inputString.toInt()
        }
    }

//    @TestOnly
    private fun updateAddButton() =
        view.updateAddButton(hasEnteredText())

    @TestOnly
    fun hasEnteredText(): Boolean =
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

    fun onCreated() {           //todo костыль, без него при повороте дублируются комменты
        if (model.getAllComment().isEmpty()) {
            model.readFile()
            if(model.getAllComment().isEmpty()) {
                showErrorMessage(R.string.commentNotFound)
            }
        }
    }

    fun onPaused() {
        if (!model.writeFile()) {
            showErrorMessage(R.string.fileNotFoundMessage)
        }
    }

    fun onStarted() {
        showSavedComments()
        view.updateNextButton(model.isListCanSort())
    }

    @TestOnly
    fun showErrorMessage(messageId: Int) {
        view.showErrorMessage(messageId)
    }
}


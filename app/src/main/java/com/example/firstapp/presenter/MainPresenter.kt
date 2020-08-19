package com.example.firstapp.presenter

import android.content.Intent
import com.example.firstapp.activity.MainActivity
import com.example.firstapp.activity.SortActivity
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model

class MainPresenter(
    private val view: MainContract.MainView,
    private val model: Model
) :
    MainContract.MainPresenter {

    private var inputString = ""

    override fun onClickAddButton() {
        model.addToList(inputString)
        if (!model.isListEmpty()) {
            view.enableTextViewAndClearButton(true)
        }
        if (model.isListSorted()) {
            view.enableNextButton(true)
        }
        view.showStringToTextView(model.getComment())
        view.clearEditText()
    }

    fun onClickGenerateButton() {
        model.generateComments()
        if (!model.isListEmpty()) {
            view.enableTextViewAndClearButton(true)
        }
        view.showStringToTextView(model.getComment())
        view.enableNextButton(true)
    }

    override fun onClickNextButton(activity: MainActivity) {
        val intent = Intent(activity, SortActivity::class.java)
        intent.putExtra("STRING_FROM_TEXT_VIEW", model.getComment())
        activity.startActivity(intent)
    }

    override fun onClickClearButton() {
        model.clearStringList()
        view.clearTextView()
        view.enableNextButton(false)
    }

    fun getStringFromEditText(inputString: String) {
        this.inputString = inputString
    }

    fun updateAddButton() =
        view.enabledAddButton(hasEnteredText())

    fun isListSorted(): Boolean =
        model.isListSorted()

    private fun hasEnteredText() =
        inputString.isNotEmpty()

    fun addCommentFromFile() {
        model.checkFile()
    }
    fun showSavedComments() {
        view.showStringToTextView(model.getComment())
    }
}


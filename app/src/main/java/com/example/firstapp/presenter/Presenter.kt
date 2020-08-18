package com.example.firstapp.presenter

import android.content.res.Resources
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType
import com.example.firstapp.contracts.MainContract

class Presenter(
    private val view: MainContract.MainView,
    private val model: Model,
    private val resources: Resources
) :
    MainContract.MainPresenter {

    private var inputString = ""

    override fun onClickAddButton() {
        model.addToList(inputString)
        if (!model.isListEmpty()) {
            view.enableTextViewAndClearButton(true)
        }
        view.showStringToTextView(model.getComment())
        view.clearEditText()
        view.updateSortButtonAndRadioGroup(model.isListSorted())
    }

    override fun onClickSortButton() {
        view.showStringToTextView(model.getSortedStringWithTimeStamps(resources))
        view.updateSortButtonAndRadioGroup(false)
    }

    override fun onClickClearButton() {
        model.clearStringList()
        view.clearTextView()
        view.updateSortButtonAndRadioGroup(false)
    }

    fun getStringFromEditText(inputString: String) {
        this.inputString = inputString
    }

    fun updateAddButton() {
        view.enabledAddButton(hasEnteredText())
    }

    fun isListSorted(): Boolean = model.isListSorted()

    fun onClickMergeSortRadioButton() =
        model.setSortType(SortType.MERGE)


    fun onClickBubbleSortRadioButton() =
        model.setSortType(SortType.BUBBLE)


    private fun hasEnteredText() = inputString.isNotEmpty()

}


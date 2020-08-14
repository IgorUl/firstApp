package com.example.firstapp.mvp.presenter

import android.content.res.Resources
import com.example.firstapp.R
import com.example.firstapp.mvp.model.Model
import com.example.firstapp.common.SortType
import com.example.firstapp.contracts.MainContract
import java.util.*

class Presenter(
    private val view: MainContract.MainView,
    private val model: Model,
    private val resources: Resources
) :
    MainContract.MainPresenter {

    private var sortType: SortType =
        SortType.MERGE

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

        val startSortingTime: Long = model.getCurrentTime()
        val sortedString: String = model.getSortedString(sortType)
        val endSortingTime: Long = model.getCurrentTime()
        val sortedStringToShow: String = resources.getString(
            R.string.sortedStringWithTimeStamps,
            model.parseDateToString(Date(startSortingTime)),
            sortedString,
            model.parseDateToString(Date(endSortingTime)),
            model.getDateDifference(endSortingTime, startSortingTime).toString()
        )
        view.showStringToTextView(sortedStringToShow)
        view.updateSortButtonAndRadioGroup(false)

    }

    fun getStringFromEditText(inputString: String) {
        this.inputString = inputString
    }

    override fun onClickClearButton() {
        model.clearStringList()
        view.clearTextView()
        view.updateSortButtonAndRadioGroup(false)
    }

    fun isEditViewEmpty(string: String) {
        view.enabledAddButton(string.isNotEmpty())
    }

    fun isListSorted(): Boolean = model.isListSorted()

    fun onClickMergeSortRadioButton() {
        sortType = SortType.MERGE
    }

    fun onClickBubbleSortRadioButton() {
        sortType = SortType.BUBBLE
    }

}


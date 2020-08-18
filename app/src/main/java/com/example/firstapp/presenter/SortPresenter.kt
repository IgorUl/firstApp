package com.example.firstapp.presenter

import android.content.res.Resources
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType

class SortPresenter(
    private val view: MainContract.SortView,
    private val model: Model,
    private val resources: Resources
) :
    MainContract.SortPresenter {

    override fun onClickSortButton() {
        view.showStringToTextView(model.getSortedStringWithTimeStamps(resources))
        view.updateSortButtonAndRadioGroup(false)
    }

    fun onClickMergeSortRadioButton() =
    model.setSortType(SortType.MERGE)


    fun onClickBubbleSortRadioButton() =
        model.setSortType(SortType.BUBBLE)
}
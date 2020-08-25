package com.example.firstapp.presenter

import android.content.res.Resources
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType
import com.example.firstapp.data.SortedStringWithTimeStamps
import java.util.*

class SortPresenter(
    private val view: MainContract.SortView,
    private val model: Model,
    private val resources: Resources
) :
    MainContract.SortPresenter {

    fun onCreated() {
        view.setOutputText(model.getAllComment())
    }

    override fun onClickSortButton() {
        view.setOutputText(getSortedStringWithTimeStamps(model.initSortData()))
    }

    fun onClickMergeSortRadioButton() =
        model.setSortType(SortType.MERGE)

    fun onClickBubbleSortRadioButton() =
        model.setSortType(SortType.BUBBLE)

    private fun getSortedStringWithTimeStamps(sortedStringWithTimeStamps: SortedStringWithTimeStamps): String {
        return resources.getString(
            R.string.sortedStringWithTimeStamps,
            model.parseDateToString(Date(sortedStringWithTimeStamps.startTime)),
            sortedStringWithTimeStamps.sortedString,
            model.parseDateToString(Date(sortedStringWithTimeStamps.endTime)),
            model.getDateDifference(
                sortedStringWithTimeStamps.endTime,
                sortedStringWithTimeStamps.startTime
            ).toString()
        )
    }
}
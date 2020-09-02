package com.example.firstapp.presenter

import android.content.res.Resources
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType
import com.example.firstapp.data.SortedStringWithTimeStamps
import com.example.firstapp.data.TimeProvider
import org.jetbrains.annotations.TestOnly

class SortPresenter(
    private val view: MainContract.SortView,
    private val model: Model,
    private val resources: Resources,
    private val timeProvider: TimeProvider

) :
    MainContract.SortPresenter {

    private val sortListener: MainContract.OnScreenChangeListener = object : MainContract.OnScreenChangeListener {
        override fun onScreenChange() {
            view.setOutputText(model.getAllComment())
        }
    }

    fun onCreated() {
        model.initScreenListener(sortListener)
        view.setOutputText(model.getAllComment())
    }

    override fun onClickSortButton() {
        if (model.getAllComment().isEmpty()) {
            view.showErrorSortMessage()
        } else {
            view.setOutputText(getSortedStringWithTimeStamps(model.initSortData()))
        }
    }

    fun onClickMergeSortRadioButton() =
        model.setSortType(SortType.MERGE)

    fun onClickBubbleSortRadioButton() =
        model.setSortType(SortType.BUBBLE)

    @TestOnly
    internal fun getSortedStringWithTimeStamps(sortedStringWithTimeStamps: SortedStringWithTimeStamps): String {
        return resources.getString(
            R.string.sortedStringWithTimeStamps,
            timeProvider.parseDateToString(sortedStringWithTimeStamps.startTime),
            sortedStringWithTimeStamps.sortedString,
            timeProvider.parseDateToString(sortedStringWithTimeStamps.endTime),
            sortedStringWithTimeStamps.timeDifference.toString()
        )
    }
}
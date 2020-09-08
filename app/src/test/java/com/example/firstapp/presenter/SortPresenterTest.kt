package com.example.firstapp.presenter

import android.content.res.Resources
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType
import com.example.firstapp.data.SortedStringWithTimeStamps
import com.example.firstapp.data.TimeProvider
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.`when`

class SortPresenterTest {

    private var sortObj: SortedStringWithTimeStamps = SortedStringWithTimeStamps(1, "test", 3)

    private var model: Model = mock()
    private var view: MainContract.SortView = mock()
    private var resources: Resources = mock()
    private var timeProvider: TimeProvider = mock()
    private var presenter: SortPresenter = SortPresenter(view, model, resources, timeProvider)

    @Test
    fun onSortButtonClicked_returnSortedList() {
        val expected = "expected"
        `when`(
            resources.getString(
                eq(R.string.sorted_string_with_time_stamps),
                anyString(),
                anyString(),
                anyString(),
                anyString()
            )
        ).thenReturn(expected)
        `when`(timeProvider.parseDateToString(anyLong())).thenReturn(anyString())

        val result: String = presenter.getSortedStringWithTimeStamps(sortObj)

        assert(result == expected)
    }

    @Test
    fun onCreated_notEmptyString_invokeSetOutputTextFun() {
        `when`(model.getAllComment()).thenReturn("test")

        presenter.onCreated()

        verify(view).setOutputText(model.getAllComment())
    }

    @Test
    fun onCreated_emptyString_invokeSetOutputTextFun() {
        `when`(model.getAllComment()).thenReturn("")

        presenter.onCreated()

        verify(view).setOutputText(model.getAllComment())
    }

    @Test
    fun onMergeSortRadioButtonClicked_changeSortType() {
        presenter.onClickMergeSortRadioButton()

        verify(model).setSortType(SortType.MERGE)
    }

    @Test
    fun onBubbleSortRadioButtonClicked_changeSortType() {
        presenter.onClickBubbleSortRadioButton()

        verify(model).setSortType(SortType.BUBBLE)
    }
}
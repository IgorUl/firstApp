package com.example.firstapp

import android.content.res.Resources
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType
import com.example.firstapp.presenter.MainPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.`when`

class MockTestPresenter {

    private var model: Model = mock()
    private var view: MainContract.MainView = mock()
    private var resources: Resources = mock()
    private var presenter: MainPresenter = MainPresenter(view, model, resources)


    @Test
    fun onAddButtonClicked() {
        `when`(model.getComment()).thenReturn("test")

        presenter.onClickAddButton()

        verify(model).addToList(any())
        verify(view).clearEditText()
        verify(view).showStringToTextView(model.getComment())
    }

    @Test
    fun onSortButtonClicked() {
        `when`(model.getSortedStringWithTimeStamps(any())).thenReturn("test")

        presenter.onClickSortButton()

        verify(model).getSortedStringWithTimeStamps(any())
        verify(view).showStringToTextView(any())
        verify(view).updateSortButtonAndRadioGroup(false)
    }

    @Test
    fun onClearButtonClicked() {

        presenter.onClickClearButton()

        verify(model).clearStringList()
        verify(view).clearTextView()
        verify(view).updateSortButtonAndRadioGroup(false)
    }

    @Test
    fun onMergeSortRadioButtonClicked() {
        presenter.onClickMergeSortRadioButton()

        verify(model).setSortType(SortType.MERGE)
    }

    @Test
    fun onBubbleSortRadioButtonClicked() {
        presenter.onClickBubbleSortRadioButton()

        verify(model).setSortType(SortType.BUBBLE)
    }
}
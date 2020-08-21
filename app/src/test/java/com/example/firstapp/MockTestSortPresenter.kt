package com.example.firstapp

import android.content.res.Resources
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType
import com.example.firstapp.presenter.SortPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito

class MockTestSortPresenter {

    private var model: Model = mock()
    private var view: MainContract.SortView = mock()
    private var resources: Resources = mock()
    private var presenter: SortPresenter = SortPresenter(view, model, resources)

    @Test
    fun onSortButtonClicked() {
        Mockito.`when`(model.getSortedStringWithTimeStamps(any())).thenReturn("test")

        presenter.onClickSortButton()

        verify(model).getSortedStringWithTimeStamps(any())
        verify(view).showStringToTextView(any())
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
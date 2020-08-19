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

class MockTestMainPresenter {

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
    fun onClearButtonClicked() {
        presenter.onClickClearButton()

        verify(model).clearStringList()
        verify(view).clearTextView()
    }
}
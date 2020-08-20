package com.example.firstapp

import android.content.Context
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.presenter.MainPresenter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.`when`

class MockTestMainPresenter {

    private var model: Model = mock()
    private var view: MainContract.MainView = mock()
    private var context: Context = mock()
    private var presenter: MainPresenter = MainPresenter(view, model, context)


    @Test
    fun onAddButtonClicked() {
        `when`(model.getComment()).thenReturn("test")
        `when`(model.isListEmpty()).thenReturn(false)
        `when`(model.isListSorted()).thenReturn(true)

        presenter.onClickAddButton()

        verify(model).addToList(any())
        verify(model).isListEmpty()
        verify(view).enableClearButton(true)
        verify(model).isListSorted()
        verify(view).enableNextButton(true)
        verify(view).showStringToTextView(model.getComment())
        verify(view).clearEditText()
    }

    @Test
    fun onGenerateButtonClicked() {
        presenter.onClickGenerateButton()

        verify(model).generateComments()
        verify(model).isListEmpty()


    }

    @Test
    fun onClearButtonClicked() {
        presenter.onClickClearButton()

        verify(model).clearStringList()
        verify(view).clearTextView()
        verify(view).enableNextButton(false)
    }
}
package com.example.firstapp.presenter

import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt


class MainPresenterTest {

    private var model: Model = mock()
    private var view: MainContract.MainView = mock()
    private var navigator: MainContract.MainScreenNavigator = mock()
    private var presenter: MainPresenter = MainPresenter(view, navigator, model)

    @Test
    fun onAddButtonClicked_hasCanSortList_isNextButtonEnabled() {
        // additional checks
        mockSortedList(isEmpty = false, isCanSort = true)

        presenter.onClickAddButton()

        verify(view).updateNextButton(true)
    }

    @Test
    fun onAddButtonClicked_hasCanSortEmptyList_isNextButtonDisabled() {
        mockSortedList(isEmpty = true, isCanSort = true)

        presenter.onClickAddButton()

        verify(view).updateNextButton(false)
    }

    @Test
    fun onAddButtonClicked_hasCannotSortEmptyList_isNextButtonDisabled() {
        mockSortedList(isEmpty = true, isCanSort = false)

        presenter.onClickAddButton()

        verify(view).updateNextButton(false)
    }

    @Test
    fun onAddButtonClicked_hasCannotSortNotEmptyList_isNextButtonEnabled() {
        mockSortedList(isEmpty = false, isCanSort = false)

        presenter.onClickAddButton()

        verify(view, times(0)).updateNextButton(any())
    }

    @Test
    fun onAddButtonClicked_hasNotEmptyList_isClearButtonVisible() {
        mockSortedList(isEmpty = false)

        presenter.onClickAddButton()

        verify(view).updateClearButtonVisibility(true)
    }

    @Test
    fun onAddButtonClicked_hasEmptyList_isClearButtonInvisible() {
        mockSortedList(isEmpty = true)

        presenter.onClickAddButton()

        verify(view).updateClearButtonVisibility(false)
    }

    private fun mockSortedList(isEmpty: Boolean, isCanSort: Boolean = false) {
        `when`(model.getAllComment()).thenReturn("test")
        `when`(model.isListEmpty()).thenReturn(isEmpty)
        `when`(model.isListCanSort()).thenReturn(isCanSort)
    }

    @Test
    fun onGenerateButtonClicked() {
        presenter.onClickGenerateButton()

        verify(model).generateComments(anyInt()) // todo
        verify(view).updateClearButtonVisibility(true)
        verify(view).updateNextButton(true)
    }

    @Test
    fun onClearButtonClicked() {
        presenter.onClickClearButton()

        verify(model).clearStringList()
        verify(view).clearTextView()
        verify(view).updateNextButton(false)
    }

}
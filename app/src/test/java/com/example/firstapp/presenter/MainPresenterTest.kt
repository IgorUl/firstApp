package com.example.firstapp.presenter

import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt


class MainPresenterTest {

    private var model: Model = mock()
    private var view: MainContract.MainView = mock()
    private var navigator: MainContract.MainScreenNavigator = mock()
    private var presenter: MainPresenter = MainPresenter(view, navigator, model)

    @Test
    fun onAddButtonClicked_hasCanSortList_isNextButtonEnabled() {
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
    fun onClickAddButton_invokeOrder_isListCanSortTrue() {
        `when`(model.isListCanSort()).thenReturn(true)

        presenter.onClickAddButton()

        inOrder(model, view) {
            verify(model).addToList(anyString())
            verify(model).isListCanSort()
            verify(view).updateNextButton(true)
            verify(view).clearEditText()
        }
    }

    @Test
    fun onClickAddButton_invokeOrderWithOutNextButtonUpdate_isListCanSortFalse() {
        `when`(model.isListCanSort()).thenReturn(false)

        presenter.onClickAddButton()

        inOrder(model, view) {
            verify(model).addToList(anyString())
            verify(model).isListCanSort()
            verify(view, times(0)).updateNextButton(anyBoolean())
            verify(view).clearEditText()
        }
    }

    @Test
    fun onGenerateButtonClicked_invokeOrder_commentCountMoreZero() {
        presenter.commentCount = 1

        presenter.onClickGenerateButton()

        inOrder(model, view) {
            verify(model).generateComments(anyInt())
            verify(view).updateNextButton(true)
        }
    }

    @Test
    fun onGenerateButtonClicked_invokeOrder_commentCountLessZero() {
        presenter.commentCount = -1

        presenter.onClickGenerateButton()

        verify(view).showWrongCommentCountToast()
    }

    @Test
    fun onGenerateButtonClicked_invokeOrder_commentCountZero() {
        presenter.commentCount = 0

        presenter.onClickGenerateButton()

        verify(view).showWrongCommentCountToast()
    }

    @Test
    fun onGenerateButtonClicked_invokeWithSameArgs() {
        presenter.commentCount = 3
        presenter.onClickGenerateButton()

        verify(model).generateComments(3)
    }

    @Test
    fun onClickNextButton_invokeNavigatorFun() {
        `when`(model.getAllComment()).thenReturn("test")
        presenter.onClickNextButton()

        verify(navigator).navigateToSortScreen(model.getAllComment())
    }

    @Test
    fun onClearButtonClicked() {
        presenter.onClickClearButton()

        inOrder(model, view) {
            verify(model).clearStringList()
            verify(view).clearTextView()
            verify(view).updateNextButton(false)
            verify(view).updateClearButtonVisibility(false)
        }
    }

    @Test
    fun setStringFromEditText_setArg() {
        val expected = "test"

        presenter.setStringFromEditText("test")

        assertEquals(expected, presenter.inputString)
    }

    @Test
    fun setStringFromEditText_invokeUpdateAddButtonFun() {
        presenter.setStringFromEditText(anyString())

        verify(view).updateAddButton(anyBoolean())
    }

    @Test
    fun updateAddButton_invokeViewFun() {
        presenter.updateAddButton()

        verify(view).updateAddButton(anyBoolean())
    }

    @Test
    fun hasEnteredText_EmptyString() {
        presenter.inputString = ""
        val expected = false

        val result = presenter.hasEnteredText()

        assertEquals(expected, result)
    }

    @Test
    fun hasEnteredText_NotEmptyString() {
        presenter.inputString = "test"
        val expected = true

        val result = presenter.hasEnteredText()

        assertEquals(expected, result)
    }

    @Test
    fun showSavedComments_invokeOrderEmptyList() {
        `when`(model.isListEmpty()).thenReturn(true)

        presenter.showSavedComments()

        inOrder(view) {
            verify(view).updateClearButtonVisibility(false)
            verify(view).updateNextButton(false)
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun showSavedComments_invokeOrderNotEmptyList() {
        `when`(model.isListEmpty()).thenReturn(false)
        `when`(model.getAllComment()).thenReturn("test")

        presenter.showSavedComments()

        inOrder(view) {
            verify(view).showStringToTextView(model.getAllComment())
            verify(view).updateClearButtonVisibility(true)
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun onCreated_EmptyCommentString_invokeShowErrorMessage() {
        `when`(model.getAllComment()).thenReturn("")

        presenter.onCreated()

        verify(model).readFile()
        verify(view).showErrorMessage(anyInt())
    }

    @Test
    fun onCreated_NotEmptyCommentString_NotInvokeShowErrorMessage() {
        `when`(model.getAllComment()).thenReturn("test")

        presenter.onCreated()

        verify(model).readFile()
        verify(view, times(0)).showErrorMessage(anyInt())
    }

    @Test
    fun onPaused_writeFileError_showErrorMessage() {
        `when`(model.writeFile()).thenReturn(false)

        presenter.onPaused()

        verify(view).showErrorMessage(anyInt())
    }

    @Test
    fun onPaused_successWriteFile_doNotShowErrorMessage() {
        `when`(model.writeFile()).thenReturn(true)

        presenter.onPaused()

        verify(view, times(0)).showErrorMessage(anyInt())
    }

    @Test
    fun showErrorMessage_invokeSameArg() {
        val expectedArg = 3
        presenter.showErrorMessage(expectedArg)

        verify(view).showErrorMessage(expectedArg)
    }
}
package com.example.firstapp.presenter

import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.navigator.FragmentNavigator
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt


class MainPresenterTest {

    private var model: Model = mock()
    private var view: MainContract.MainView = mock()
    private var navigator: FragmentNavigator = mock()
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

        verify(view, never()).updateNextButton(any())
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
    fun onClickAddButton_nextButtonNeverUpdate() {
        `when`(model.isListCanSort()).thenReturn(false)

        verify(view, never()).updateNextButton(anyBoolean())
    }

    @Test
    fun onClickAddButton_isListCanSortTrue_invokeOrder() {
        `when`(model.isListCanSort()).thenReturn(true)

        presenter.onClickAddButton()
        // split
        inOrder(model, view) {
            verify(model).addToList(anyString())
            verify(model).isListCanSort()
            verify(view).updateNextButton(true)
            verify(view).clearEditText()
        }
    }

    @Test
    fun onClickAddButton_isListCanSortFalse_invokeOrderWithOutNextButtonUpdate() {
        `when`(model.isListCanSort()).thenReturn(false)

        presenter.onClickAddButton()

        inOrder(model, view) {
            verify(model).addToList(anyString())
            verify(model).isListCanSort()
            verify(view, never()).updateNextButton(anyBoolean())
            verify(view).clearEditText()
        }
    }

    @Test
    fun onClickGenerationButton_isListCanSortFalse_updateNextButtonNeverInvoke() {
        val commentCount = "2"
        `when`(model.isListCanSort()).thenReturn(false)

        presenter.onClickGenerateButton(commentCount)

        verify(view, never()).updateNextButton(anyBoolean())
    }

    @Test
    fun onClickGenerateButton_IsListCanSortTrue_invokeUpdateNextButtonTrue() {
        val commentCount = "2"
        `when`(model.isListCanSort()).thenReturn(true)

        presenter.onClickGenerateButton(commentCount)

        verify(view, times(1)).updateNextButton(true)
    }


    @Test
    fun onGenerateButtonClicked_commentCountMoreZero_invokeOrder() {
        val numberOfComment = "2"
        `when`(model.isListCanSort()).thenReturn(true)

        presenter.onClickGenerateButton(numberOfComment)

        inOrder(model, view) {
            verify(model).generateComments(numberOfComment.toInt())
            verify(view).updateNextButton(true)
        }
    }

    @Test
    fun onGenerateButtonClicked_commentCountLessZero_invokeOrder() {
        val commentCount = "-1"

        presenter.onClickGenerateButton(commentCount)

        verify(view).showWrongCommentCountToast()
    }
    @Test
    fun onGenerateButtonClicked_commentCountNotMoreZero_NotInvokeGenerateComments() {
        val commentCount = "0"

        presenter.onClickGenerateButton(commentCount)

        verify(model, never()).generateComments(commentCount.toInt())
    }

    @Test
    fun onGenerateButtonClicked_invokeOrder_commentCountZero() {
        val commentCount = "0"

        presenter.onClickGenerateButton(commentCount)

        verify(view).showWrongCommentCountToast()
    }

    @Test
    fun onGenerateButtonClicked_invokeWithSameArgs() {
        val commentCount = "3"
        presenter.onClickGenerateButton(commentCount)

        verify(model).generateComments(3)
    }

    @Test
    fun onClickNextButton_invokeNavigatorFun() {
        `when`(model.getAllComment()).thenReturn("test")
        presenter.onClickNextButton()

        verify(navigator).navigateToSortView()
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
    fun setStringFromEditText_invokeUpdateAddButtonFun() {
        presenter.setStringFromEditText(anyString())

        verify(view).updateAddButton(anyBoolean())
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
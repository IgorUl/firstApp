package com.example.firstapp.presenter

import android.content.res.Resources
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType
import com.example.firstapp.data.SortedStringWithTimeStamps
import com.nhaarman.mockitokotlin2.*
import net.bytebuddy.TypeCache
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import java.lang.IllegalStateException
import java.util.*

class SortPresenterTest {

    private var sortObj: SortedStringWithTimeStamps = SortedStringWithTimeStamps(1, "test", 3)

    private var model: Model = mock()
    private var view: MainContract.SortView = mock()
    private var resources: Resources = mock()
    private var presenter: SortPresenter = SortPresenter(view, model, resources)

    @Test
    fun onSortButtonClicked_() {
        //выписать все функции
        
    }

    @Test
    fun onCreated() {
        `when`(model.getAllComment()).thenReturn("test")

        presenter.onCreated()

        verify(view).setOutputText(model.getAllComment())
    }

    @Test
    fun onMergeSortRadioButtonClicked_() {
        presenter.onClickMergeSortRadioButton()

        verify(model).setSortType(SortType.MERGE)
    }

    @Test
    fun onBubbleSortRadioButtonClicked_() {
        presenter.onClickBubbleSortRadioButton()

        verify(model).setSortType(SortType.BUBBLE)
    }
}
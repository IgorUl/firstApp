package com.example.firstapp.presenter

import android.content.Intent
import android.content.res.Resources
import androidx.core.content.ContextCompat.startActivity
import com.example.firstapp.activity.MainActivity
import com.example.firstapp.activity.SortActivity
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.SortType


class MainPresenter(
    private val view: MainContract.MainView,
    private val model: Model,
    private val resources: Resources
) :
    MainContract.MainPresenter {

    private var inputString = ""

    override fun onClickAddButton() {
        model.addToList(inputString)
        if (!model.isListEmpty()) {
            view.enableTextViewAndClearButton(true)
        }
        if (model.isListSorted()) {
            view.enableNextButton(true)
        }
        view.showStringToTextView(model.getComment())
        view.clearEditText()
    }

    fun onClickGenerateButton() {
        model.generateComments()
        if (!model.isListEmpty()) {
            view.enableTextViewAndClearButton(true)
        }
        view.showStringToTextView(model.getComment())
        enableNextButton(true)
    }

    fun onClickNextButton(activity: MainActivity) {
        val intent = Intent(activity, SortActivity::class.java)
        intent.putExtra("STRING_FROM_TEXT_VIEW", model.getComment())
        activity.startActivity(intent)
    }

    fun enableNextButton(state: Boolean) {
        view.enableNextButton(state)
    }

    override fun onClickClearButton() {
        model.clearStringList()
        view.clearTextView()
//        view.updateSortButtonAndRadioGroup(false)
    }

    fun getStringFromEditText(inputString: String) {
        this.inputString = inputString
    }

    fun updateAddButton() {
        view.enabledAddButton(hasEnteredText())
    }

//    fun isListSorted(): Boolean = model.isListSorted()

    fun onClickMergeSortRadioButton() =
        model.setSortType(SortType.MERGE)


    fun onClickBubbleSortRadioButton() =
        model.setSortType(SortType.BUBBLE)


    private fun hasEnteredText() = inputString.isNotEmpty()

}


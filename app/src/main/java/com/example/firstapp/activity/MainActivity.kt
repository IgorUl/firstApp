package com.example.firstapp.activity

import android.content.res.Resources
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.firstapp.App
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.model.Model
import com.example.firstapp.mvp.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var presenter: Presenter
    private val editTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            presenter.isEditViewEmpty(editText.text.toString())
            presenter.getStringFromEditText(editText.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: Model = (application as App).model
        val resources: Resources = resources
        presenter = Presenter(this, model, resources)
        initClickListeners()
    }

    private fun initClickListeners() {
        addButton.setOnClickListener {
            presenter.onClickAddButton()
        }

        sortButton.setOnClickListener {
            presenter.onClickSortButton()
        }

        clearButton.setOnClickListener {
            presenter.onClickClearButton()
        }
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                mergeSortRadioButton.id -> presenter.onClickMergeSortRadioButton()
                bubbleSortRadioButton.id -> presenter.onClickBubbleSortRadioButton()
            }
        }
        editText.addTextChangedListener(editTextWatcher)
    }

    override fun onSaveInstanceState(outState: Bundle) { //TODO move saving screen state to presenter
        super.onSaveInstanceState(outState)
        outState.run {
            putString("textView", textView.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView.text = savedInstanceState.getString("textView")

        //костыль, но пока не знаю как запоминать состояние кнопок
        enableTextViewAndClearButton(textView.text.isNotEmpty())
        updateSortButtonAndRadioGroup(presenter.isListSorted())
    }

    override fun enabledAddButton(state: Boolean) {
        addButton.isEnabled = state
    }

    override fun updateSortButtonAndRadioGroup(state: Boolean) {
        sortButton.isEnabled = state
        radioGroup.isVisible = state
    }

    override fun showStringToTextView(stringToShow: String) {
        textView.text = stringToShow
    }

    override fun enableTextViewAndClearButton(state: Boolean) {
        textView.isVisible = state
        clearButton.isVisible = state
    }

    override fun clearTextView() {
        textView.text = ""
        clearButton.isVisible = false
        textView.isVisible = false
    }

    override fun clearEditText() {
        editText.setText("")
    }
}
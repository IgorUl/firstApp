package com.example.firstapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private val presenter: Presenter = Presenter(this)
    private val editTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            toggleAddButtonAndTextView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initClickListeners()
    }


    private fun initClickListeners() {
        addButton.setOnClickListener {
            presenter.add()
        }

        sortButton.setOnClickListener {
            presenter.sort()
        }

        clearButton.setOnClickListener {
            presenter.clear()
        }
        editText.addTextChangedListener(editTextWatcher)
    }

    override fun changeButtonColor() {
        sortButton.setBackgroundColor(getColor(R.color.inactiveAddButton))
    }

    override fun toggleAddButtonAndTextView() {
        addButton.isEnabled = editText.text.isNotEmpty()
        addButton.setBackgroundColor(
            getColor(
                if (addButton.isEnabled) R.color.activeAddButton else R.color.inactiveAddButton
            )
        )
        textView.isVisible = textView.text.isNotEmpty()
    }


    override fun updateSortButtonAndRadioGroup(state: Boolean) {
        sortButton.isEnabled = state
        if (sortButton.isEnabled) {
            sortButton.setBackgroundColor(getColor(R.color.activeAddButton)) // state -> color ColorStateList
        }
        radioGroup.isVisible = state

    }

    override fun getStringFromEditText(): String = editText.text.toString()

    override fun showStringToTextView(string: String) {
        textView.text = string
    }

    override fun enableTextViewAndClearButton() {
        textView.isVisible = true
        clearButton.isVisible = true
    }

    override fun getSortType(): SortType = when (radioGroup.checkedRadioButtonId) {
        R.id.bubbleSortRadioButton -> SortType.BUBBLE
        else -> SortType.MERGE
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
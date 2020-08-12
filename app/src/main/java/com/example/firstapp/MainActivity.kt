package com.example.firstapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var presenter: Presenter
    private val editTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            toggleAddButton()
            presenter.isEditViewEmpty(editText.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = (application as App).model
        presenter = Presenter(this, model)
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

    override fun toggleAddButton(state: Boolean) {
//        addButton.isEnabled =
//            editText.text.isNotEmpty() //TODO pass from presenter states and colors
        addButton.isEnabled = state
    }

    override fun updateSortButtonAndRadioGroup(state: Boolean) {
        sortButton.isEnabled = state
        radioGroup.isVisible = state
    }

    override fun getStringFromEditText(): String =
        editText.text.toString()

    override fun showStringToTextView() {
        val stringToShow: String = getStringFromEditText() + "\n"
        textView.append(stringToShow)
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

    override fun showSortedStringWithTimeStamps(
        startSortingTime: String,
        sortedString: String,
        endSortingTime: String,
        timeDifference: String
    ) {
        val sortedStringWithTimeStamps: String = getString(
            R.string.sortedStringWithTimeStamps,
            startSortingTime,
            sortedString,
            endSortingTime,
            timeDifference
        )
        textView.text = sortedStringWithTimeStamps
    }
}
package com.example.firstapp.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.firstapp.App
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.SaveData
import com.example.firstapp.data.Model
import com.example.firstapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var presenter: MainPresenter
    private val saveData: SaveData = SaveData()
    private val editTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            presenter.getStringFromEditText(editText.text.toString())
            presenter.updateAddButton()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: Model = (application as App).model
        presenter = MainPresenter(this, model)
        presenter.addCommentFromFile()
        initClickListeners()
    }

    private fun initClickListeners() {
        addButton.setOnClickListener {
            presenter.onClickAddButton()
        }

        nextButton.setOnClickListener {
            presenter.onClickNextButton(this)
        }

        clearButton.setOnClickListener {
            presenter.onClickClearButton()
        }

        generateButton.setOnClickListener {
            presenter.onClickGenerateButton()
        }
        editText.addTextChangedListener(editTextWatcher)
    }

    override fun onStop() {
        super.onStop()
        saveData.writeFile(textView.text.toString())
    }

    override fun onResume() {
        super.onResume()
        presenter.showSavedComments()
        if (textView.text.isNotEmpty()) enableTextViewAndClearButton(true)
        enableNextButton(presenter.isListSorted())
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
        enableTextViewAndClearButton(textView.text.isNotEmpty())
    }

    override fun enableNextButton(state: Boolean) {
        nextButton.isEnabled = state
    }

    override fun enabledAddButton(state: Boolean) {
        addButton.isEnabled = state
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

    override fun clearEditText() =
        editText.setText("")
}
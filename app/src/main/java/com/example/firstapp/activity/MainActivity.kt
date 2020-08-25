package com.example.firstapp.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.firstapp.App
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.presenter.MainPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView, MainContract.MainScreenNavigator {

    private lateinit var presenter: MainPresenter

    private val editTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            presenter.setStringFromEditText(inputText.text.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: Model = (application as App).model
        presenter = MainPresenter(this, this, model)

        savedCommentsView.movementMethod = ScrollingMovementMethod()
        initClickListeners()
        if (savedInstanceState == null) {
            presenter.onCreated()
        }
    }

    private fun initClickListeners() {
        addButton.setOnClickListener {
            presenter.onClickAddButton()
        }

        nextButton.setOnClickListener {
            presenter.onClickNextButton()
        }

        clearButton.setOnClickListener {
            presenter.onClickClearButton()
        }

        generateButton.setOnClickListener {
            presenter.onClickGenerateButton()
        }
        inputText.addTextChangedListener(editTextWatcher)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun updateNextButton(isEnable: Boolean) {
        nextButton.isEnabled = isEnable
    }

    override fun updateAddButton(isEnable: Boolean) {
        addButton.isEnabled = isEnable
    }

    override fun showStringToTextView(stringToShow: String) {
        savedCommentsView.text = stringToShow
    }

    override fun updateClearButtonVisibility(isVisible: Boolean) {
        clearButton.isVisible = isVisible
    }

    override fun clearTextView() {
        savedCommentsView.text = ""
    }

    override fun clearEditText() =
        inputText.setText("")

    override fun navigateToSortScreen(comments: String) {
        val intent = Intent(this, SortActivity::class.java)
        startActivity(intent)
    }

    override fun showErrorMessage(messageId: Int) {
        Snackbar.make(constLayout, messageId, Snackbar.LENGTH_LONG).show()
    }
}
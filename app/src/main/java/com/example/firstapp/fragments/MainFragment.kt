package com.example.firstapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.firstapp.App
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.navigator.FragmentNavigator
import com.example.firstapp.presenter.MainPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), MainContract.MainView {

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

    private val howMuchTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            presenter.setNumberFromEditText(how_much_comments.text.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model: Model = (activity?.application as App).model
        presenter = MainPresenter(
            this,
            FragmentNavigator(this), model
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            presenter.onCreated()
        }
        savedCommentsView.movementMethod = ScrollingMovementMethod()
        initClickListeners()
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
        how_much_comments.addTextChangedListener(howMuchTextWatcher)
    }

    override fun onPause() {
        super.onPause()
        presenter.onPaused()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStarted()
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

    override fun showErrorMessage(messageId: Int) {
        Snackbar.make(main_fragment, messageId, Snackbar.LENGTH_LONG).show()
    }

    override fun showWrongCommentCountToast() {
        Toast.makeText(context, R.string.wrongNumberComments, Toast.LENGTH_SHORT).show()
    }



    //test
    interface OnCommentAddListener {
        fun onCommentAdd(string: String)
    }
}
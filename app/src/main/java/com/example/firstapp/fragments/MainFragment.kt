package com.example.firstapp.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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
        if ((resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
            nextButton.visibility = View.GONE
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
            presenter.onClickGenerateButton(how_much_comments.text.toString())
        }
        scroll_arrow_up.setOnClickListener {
            presenter.onClickScrollUp()
        }
        scroll_arrow_down.setOnClickListener {
            presenter.onClinkScrollDown()
        }
        inputText.addTextChangedListener(editTextWatcher)
        inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.onClickAddButton()
            }
            false
        }
        how_much_comments.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.onClickGenerateButton(how_much_comments.text.toString())
            }
            false
        }
    }

    override fun onStop() {
        super.onStop()
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

    override fun setCommentCount(commentCount: Int) {
        comment_list_size.text =
            resources.getQuantityString(R.plurals.comments_count, commentCount, commentCount)
    }

    override fun updateClearButtonVisibility(isVisible: Boolean) {
        clearButton.isVisible = isVisible
    }

    override fun clearTextView() {
        savedCommentsView.text = ""
    }

    override fun clearEditText() =
        inputText.setText("")

    override fun clearCommentCount() =
        how_much_comments.setText("")

    override fun updateScrollUpVisibility(isVisible: Boolean) {
        scroll_arrow_up.isVisible = isVisible
    }

    override fun updateScrollDownVisibility(isVisible: Boolean) {
        scroll_arrow_down.isVisible = isVisible
    }

    override fun scrollDown() {
        savedCommentsView.scrollTo(0, getScrollAmount())
    }

    override fun scrollUp() {
        savedCommentsView.scrollTo(0, 0)
    }

    private fun getScrollAmount(): Int =
        savedCommentsView.layout.getLineTop(savedCommentsView.lineCount) - savedCommentsView.height

    override fun showErrorMessage(messageId: Int) {
        Snackbar.make(main_fragment, messageId, Snackbar.LENGTH_LONG).show()
    }

    override fun showWrongCommentCountToast() {
        Toast.makeText(context, R.string.wrong_number_comments, Toast.LENGTH_SHORT).show()
    }
}
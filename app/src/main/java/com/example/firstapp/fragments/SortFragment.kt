package com.example.firstapp.fragments

import android.content.res.Resources
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.firstapp.App
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.data.TimeProvider
import com.example.firstapp.presenter.SortPresenter
import kotlinx.android.synthetic.main.sort_fragment.*

class SortFragment : Fragment(), MainContract.SortView {

    private lateinit var presenter: SortPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sort_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model: Model = (activity?.application as App).model
        val resources: Resources = resources
        val timeProvider = TimeProvider()
        presenter = SortPresenter(this, model, resources, timeProvider)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sortTextView.movementMethod = ScrollingMovementMethod()
        initClickListeners()
        presenter.onCreated()
    }

    private fun initClickListeners() {
        sortButton.setOnClickListener {
            presenter.onClickSortButton()
        }
        sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                mergeSortRadioButton.id -> presenter.onClickMergeSortRadioButton()
                bubbleSortRadioButton.id -> presenter.onClickBubbleSortRadioButton()
            }
        }
        scroll_arrow_up_sort.setOnClickListener {
            presenter.onClickScrollUp()
        }
        scroll_arrow_down_sort.setOnClickListener {
            presenter.onClinkScrollDown()
        }
    }

    override fun setOutputText(stringToShow: String) {
        sortTextView.text = stringToShow
    }

    override fun scrollDown() {
        sortTextView.scrollTo(0, getScrollAmount())
    }

    override fun scrollUp() {
        sortTextView.scrollTo(0, 0)
    }

    private fun getScrollAmount(): Int =
        sortTextView.layout.getLineTop(sortTextView.lineCount) - sortTextView.height

    override fun onDetach() {
        super.onDetach()
        presenter.clearPresenterScreenListener()
    }

    override fun showErrorSortMessage() {
        Toast.makeText(context, R.string.nothing_to_sort, Toast.LENGTH_SHORT).show()
    }
}
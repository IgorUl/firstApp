package com.example.firstapp.activity

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.firstapp.App
import com.example.firstapp.R
import com.example.firstapp.contracts.MainContract
import com.example.firstapp.data.Model
import com.example.firstapp.presenter.SortPresenter
import kotlinx.android.synthetic.main.activity_sort.*

class SortActivity : AppCompatActivity(), MainContract.SortView {

    private lateinit var presenter: SortPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort)

        val model: Model = (application as App).model
        val resources: Resources = resources
        presenter = SortPresenter(this, model, resources)
        initClickListeners()
        sortView.text = intent.getStringExtra("STRING_FROM_TEXT_VIEW")
    }

    private fun initClickListeners() {
        sortButton.setOnClickListener {
            presenter.onClickSortButton()
        }
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                mergeSortRadioButton.id -> presenter.onClickMergeSortRadioButton()
                bubbleSortRadioButton.id -> presenter.onClickBubbleSortRadioButton()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putString("sortView", sortView.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        sortView.text = savedInstanceState.getString("sortView")
        updateSortButtonAndRadioGroup(presenter.isListSorted())
    }

    override fun showStringToTextView(stringToShow: String) {
        sortView.text = stringToShow
    }

    override fun updateSortButtonAndRadioGroup(state: Boolean) {
        sortButton.isEnabled = state
        radioGroup.isVisible = state
    }
}

package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val commentSorterWithTimeStamp = CommentSorterWithTimeStamp()

    //private val data = Data()
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
            showStringToTextView()
            if (commentSorterWithTimeStamp.result.isNotEmpty()) {
                textView.isVisible = true
                clearButton.isVisible = true
            }
            if (commentSorterWithTimeStamp.result.size > 1 && !commentSorterWithTimeStamp.isSorted) {
                updateSortButtonAndRadioGroup(true)
            }
        }

        sortButton.setOnClickListener {

            sortingBy(
                getSortTypeByButtonId(radioGroup.checkedRadioButtonId)
            )
            updateSortButtonAndRadioGroup(false)
            sortButton.setBackgroundColor(getColor(R.color.inactiveAddButton))
        }

        clearButton.setOnClickListener {
            clearStringListAndTextView()
            updateSortButtonAndRadioGroup(false)
            sortButton.setBackgroundColor(getColor(R.color.inactiveAddButton))
        }
        editText.addTextChangedListener(editTextWatcher)
    }


    private fun getSortTypeByButtonId(radioButtonId: Int): SortType = when(radioButtonId) {
        R.id.bubbleSortRadioButton -> SortType.BUBBLE
        else -> SortType.MERGE
    }

    private fun showStringToTextView() {
        commentSorterWithTimeStamp.addStringToList(editText.text.toString())
        textView.text = commentSorterWithTimeStamp.result.joinToString("\n", "", "")
        editText.setText("")
    }

    private fun toggleAddButtonAndTextView() {
        addButton.isEnabled = editText.text.isNotEmpty()
        addButton.setBackgroundColor(
            getColor(
                if (addButton.isEnabled) R.color.activeAddButton else R.color.inactiveAddButton
            )
        )
        textView.isVisible = textView.text.isNotEmpty()
    }

    private fun sortingBy(sortType: SortType) {
        textView.text = commentSorterWithTimeStamp.getSortedStringWithTime(sortType)
    }

    private fun updateSortButtonAndRadioGroup(state: Boolean) {
        sortButton.isEnabled = state
        if (sortButton.isEnabled) {
            sortButton.setBackgroundColor(getColor(R.color.activeAddButton)) // state -> color ColorStateList
        }
        radioGroup.isVisible = state

    }

    private fun clearStringListAndTextView() {
        textView.text = ""
        commentSorterWithTimeStamp.clearStringList()
        clearButton.isVisible = false
        textView.isVisible = false
    }

    override fun onStart() {
        super.onStart()
        //  textView.text = data.readFile()
        Log.i("MainActivity", "onStart() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart() called")
    }

    override fun onResume() {
        super.onResume()
        // textView.text = data.readFile()
        Log.i("MainActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()

        Log.i("MainActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        // data.writeFile(stringHandler.inputStringList.joinToString("\n", "",""))
        Log.i("MainActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy() called")
    }

}


// 1) App: Application -> model
// 2) MVP
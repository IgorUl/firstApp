package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.RadioButton
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val stringWithTime = StringWithTime()
    private val data = Data()
    private val editTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            addButton.isEnabled = editText.text.isNotEmpty()
            textView.isVisible = textView.text.isNotEmpty()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener {
            stringWithTime.addStringToList(editText.text.toString())
            textView.text = stringWithTime.inputStringList.joinToString("\n", "", "")
            editText.setText("")
            if (stringWithTime.inputStringList.size > 0) {
                textView.isVisible = true
                clearButton.isVisible = true
            }
            if (stringWithTime.inputStringList.size > 1 && !stringWithTime.isSorted) {
                switchSortButtonAndRadioGroup(true)
            }
        }

        sortButton.setOnClickListener {
            sortingBy(findViewById(radioGroup.checkedRadioButtonId))
            switchSortButtonAndRadioGroup(false)
        }

        clearButton.setOnClickListener {
            clearStringListAndTextView()
            switchSortButtonAndRadioGroup(false)
        }
        editText.addTextChangedListener(editTextWatcher)
    }

    private fun sortingBy(radiobutton: RadioButton) {
        textView.text = if (radiobutton.id == R.id.bubbleSortRadioButton) {
            stringWithTime.getSortedStringWithTime("bubble")
        } else {
            stringWithTime.getSortedStringWithTime("merge")
        }
    }

    private fun switchSortButtonAndRadioGroup(state: Boolean) {
        sortButton.isEnabled = state
        radioGroup.isVisible = state
    }

    private fun clearStringListAndTextView() {
        textView.text = ""
        stringWithTime.inputStringList.clear()
        clearButton.isVisible = false
        textView.isVisible = false
    }

    override fun onStart() {
        super.onStart()
        textView.text = data.readFile()
        Log.i("MainActivity", "onStart() called")
    }

    override fun onRestart() {
        super.onRestart()
        textView.text = data.readFile()
        Log.i("MainActivity", "onRestart() called")
    }

    override fun onResume() {
        super.onResume()
        textView.text = data.readFile()
        Log.i("MainActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()

        Log.i("MainActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        data.writeFile(stringWithTime.inputStringList.toString())
        Log.i("MainActivity", "onStop() called")
    }

    //
    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy() called")
    }
}

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

    private val stringHandler = StringHandler()
//    private val data = Data()
    private val editTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            enabledAddButtonAndTextView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener {
            showStringToTextView()
            if (stringHandler.inputStringList.size > 0) {
                textView.isVisible = true
                clearButton.isVisible = true
            }
            if (stringHandler.inputStringList.size > 1 && !stringHandler.isSorted) {
                switchSortButtonAndRadioGroup(true)
            }
        }

        sortButton.setOnClickListener {
            sortBy(findViewById(radioGroup.checkedRadioButtonId))
            switchSortButtonAndRadioGroup(false)
            sortButton.setBackgroundColor(getColor(R.color.inactiveAddButton))

        }

        clearButton.setOnClickListener {
            clearStringListAndTextView()
            switchSortButtonAndRadioGroup(false)
            sortButton.setBackgroundColor(getColor(R.color.inactiveAddButton))


        }
        editText.addTextChangedListener(editTextWatcher)
    }

    private fun showStringToTextView() {
        stringHandler.addStringToList(editText.text.toString())
        textView.text = stringHandler.inputStringList.joinToString("\n", "", "")
        editText.setText("")
    }

    private fun enabledAddButtonAndTextView() {
        addButton.isEnabled = editText.text.isNotEmpty()
        if (addButton.isEnabled) {
            addButton.setBackgroundColor(getColor(R.color.activeAddButton))
        } else {
            addButton.setBackgroundColor(getColor(R.color.inactiveAddButton))
        }
        textView.isVisible = textView.text.isNotEmpty()
    }

    private fun sortBy(radiobutton: RadioButton) {
        textView.text = when (radiobutton.id) {
            R.id.bubbleSortRadioButton -> stringHandler.getSortedStringWithTime(SortTypes.BUBBLE)
            else -> stringHandler.getSortedStringWithTime(SortTypes.MERGE)
        }
    }

    private fun switchSortButtonAndRadioGroup(state: Boolean) {
        sortButton.isEnabled = state
        if (sortButton.isEnabled) {
            sortButton.setBackgroundColor(getColor(R.color.activeAddButton))
        }
        radioGroup.isVisible = state

    }

    private fun clearStringListAndTextView() {
        textView.text = ""
        stringHandler.inputStringList.clear()
        clearButton.isVisible = false
        textView.isVisible = false
    }

    override fun onStart() {
        super.onStart()
//        textView.text = data.readFile()
        Log.i("MainActivity", "onStart() called")
    }

    override fun onRestart() {
        super.onRestart()
        //textView.text = data.readFile()
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
       // data.writeFile(stringWithTime.inputStringList.toString())
        Log.i("MainActivity", "onStop() called")
    }

    //
    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy() called")
    }

}

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

    private lateinit var selectedRadioButton: RadioButton
    private val stringWithTime = StringWithTime()

    //    private val data = Data()
    private val editTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            if (editText.text.isNotEmpty()) {
                addButton.isEnabled = true
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (editText.text.isEmpty())
                addButton.isEnabled = false
        }
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            clearButton.isVisible = true
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (textView.text.isEmpty())
                clearButton.isVisible = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener {
//            logic.addStringToTextView(textView, editText)
            stringWithTime.addStringToList(editText.text.toString())
            textView.text = stringWithTime.inputStringList.joinToString("\n", "", "")
            editText.setText("")
            addButton.isEnabled = false
            if (stringWithTime.inputStringList.size > 0) {
                textView.isVisible = true
                clearButton.isVisible = true
            }
            if (stringWithTime.inputStringList.size > 1 && !stringWithTime.isSort) {
                sortButton.isEnabled = true
                radioGroup.isVisible = true
            }
        }
        sortButton.setOnClickListener {
            selectedRadioButton = findViewById(radioGroup.checkedRadioButtonId)

            textView.text = if (selectedRadioButton.id == R.id.bubbleSortRadioButton) {
                //костыль, позже исправлю
                stringWithTime.getSortedStringWithTime("bubble")
            } else {
                stringWithTime.getSortedStringWithTime("merge")
            }
            sortButton.isEnabled = false
            radioGroup.isVisible = false
        }

        editText.addTextChangedListener(editTextWatcher)
        //почему работает и без этого
        textView.addTextChangedListener(textWatcher)

        clearButton.setOnClickListener {
            textView.text = ""
            stringWithTime.inputStringList.clear()
            clearButton.isVisible = false
            sortButton.isEnabled = false
            radioGroup.isVisible = false
        }
        textView.addTextChangedListener(editTextWatcher)
    }

    override fun onStart() {
        super.onStart()
//        data.readFile()
        Log.i("MainActivity", "onStart() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume() called")
    }

    override fun onPause() {
        super.onPause()

        Log.i("MainActivity", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
//        data.writeFile(logic.inputStringList.toString())
        Log.i("MainActivity", "onStop() called")
    }

    //
    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy() called")
    }
}

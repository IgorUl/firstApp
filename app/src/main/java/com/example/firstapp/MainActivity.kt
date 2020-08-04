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
    private val logic = Logic()
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
            logic.addString(textView, editText)
            addButton.isEnabled = false
            if (logic.stringList.size > 0) {
                textView.isVisible = true
                clearButton.isVisible = true
            }
            if (logic.stringList.size > 1 && !logic.isSort) {
                sortButton.isEnabled = true
                radioGroup.isVisible = true
            }
        }
        sortButton.setOnClickListener {
            selectedRadioButton = findViewById(radioGroup.checkedRadioButtonId)
            logic.sort(textView, selectedRadioButton)
            sortButton.isEnabled = false
            radioGroup.isVisible = false
        }
        editText.addTextChangedListener(editTextWatcher)

        clearButton.setOnClickListener {
            textView.text = ""
            logic.stringList.clear()
            clearButton.isVisible = false
            //костыль что бы кнопка сортировки отключалась
//            sortButton.isEnabled = false
        }
        textView.addTextChangedListener(editTextWatcher)
    }

    override fun onStart() {
        super.onStart()
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
        Log.i("MainActivity", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy() called")
    }
}

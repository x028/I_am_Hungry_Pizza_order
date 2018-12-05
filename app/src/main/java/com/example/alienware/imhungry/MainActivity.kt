package com.example.alienware.imhungry

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_rotate_cw))

        var sharedPrefrences= PreferenceManager.getDefaultSharedPreferences(this)
        val tip = sharedPrefrences.getFloat("TIP", 0f)
        tipEditText.setText(tip.toString())


        pizzaradioGroup.setOnCheckedChangeListener { group, checkedId ->
            order()
        }
        pepsiecheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            order()
        }
        garlincheckBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            order()
        }
        friescheckBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            order()
        }
        tipEditText.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                order()
                val sharedPrefrences= PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
                val editor = sharedPrefrences.edit()
                val tiptostring = tipEditText.text.toString()
                if (tiptostring.isNotEmpty())5
                {
                    val tip = tiptostring.toFloat()
                    editor.putFloat("TIP",tip)
                }
                else
                {
                    editor.putFloat("TIP",0f)
                }
                editor.apply()
            }

        })

    }

    fun order() {

        var prise: Float = 0f

        when (pizzaradioGroup.checkedRadioButtonId) {

            susageradioButton.id -> {
                prise += 8f
            }
            appleradioButton2.id -> {
                prise += 20f
            }
            cheeseradioButton3.id -> {
                prise += 5f
            }
            olivesradioButton4.id -> {
                prise += 6.5f
            }
        }

        if (pepsiecheckBox.isChecked) {
            prise += 0.5f
        }
        if (friescheckBox2.isChecked) {
            prise += 2f
        }
        if (garlincheckBox3.isChecked) {
            prise += 0.2f
        }

        var tip: Float = 0f
        var tipstring: String = tipEditText.text.toString()
        if (tipstring.isNotEmpty()) {
            tip = tipstring.toFloat()
            prise += tip

            Toast.makeText(this@MainActivity,"Thanks for your Tip",Toast.LENGTH_LONG).show()

        }

        resulttextView5.text = "$${prise}"



    }
}

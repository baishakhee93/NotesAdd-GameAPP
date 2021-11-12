package com.findmyage.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSubmit.setOnClickListener {
            buttonGetAge()
           /* val  userDOB: Int =Integer.parseInt(etAge.text.toString())
            val  currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
            val userAgeInYear:Int=currentYear-userDOB
            textView.text="Your Age is $userAgeInYear years"*/
        }
    }


    fun buttonGetAge(){
        val  userDOB: Int =Integer.parseInt(etAge.text.toString())
        val  currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
        val userAgeInYear:Int=currentYear-userDOB
        textView.text="Your Age is $userAgeInYear years"
        Log.d("LogsMe","Your Age is $userAgeInYear years")
    }
}
package com.findmyage.com

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class StartAcivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
    fun fAge(view: View){
        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun myNote(view: View){
        var intent=Intent(this,MyNotesActivity::class.java)
        startActivity(intent)
    }
    fun game(view: View){
        var intent=Intent(this,TicTacToyActivity::class.java)
        startActivity(intent)
    }
}
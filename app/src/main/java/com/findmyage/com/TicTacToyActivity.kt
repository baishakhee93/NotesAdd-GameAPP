package com.findmyage.com

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tictac.*
import java.util.*
import kotlin.collections.ArrayList

class TicTacToyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tictac)

    }
    fun buClick(view : View){
        val buSelected :Button=view as Button
        var  cellId=0
        when(buSelected.id){
            R.id.bu1->cellId=1
            R.id.bu2->cellId=2
            R.id.bu3->cellId=3
            R.id.bu4->cellId=4
            R.id.bu5->cellId=5
            R.id.bu6->cellId=6
            R.id.bu7->cellId=7
            R.id.bu8->cellId=8
            R.id.bu9->cellId=9
        }
        playGame(cellId,buSelected)
        Log.d("buClick",buSelected.id.toString())
    }

    var activePlayer=1
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()
    fun playGame(cellId: Int, buSelected: Button) {
        if (activePlayer==1){
            buSelected.text="X"
         //   buSelected.setBackgroundResource(R.color.blue)
            buSelected.setBackgroundTintList(getResources().getColorStateList(R.color.blue));

            player1.add(cellId)
            activePlayer=2
            autoPlay()
        }else{
            buSelected.text="0"
      //      buSelected.setBackgroundResource(R.color.green)
            buSelected.setBackgroundTintList(getResources().getColorStateList(R.color.green));

            player2.add(cellId)
            activePlayer=1
        }
        buSelected.isEnabled=false
        checkWinner()
    }


    fun checkWinner() {
        var winer=-1
        //row1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winer=1
        }
       else if(player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winer=2
        }


        //row1
        else if(player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winer=1
        }
        else if(player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winer=2
        }

        //row1
        else if(player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winer=1
        }
        else if(player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winer=2
        }


        //con1
        else  if(player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winer=1
        }
        else if(player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winer=2
        }

        //con2

        else if(player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winer=1
        }
        else if(player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winer=2
        }
        //con3

        else if(player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winer=1
        }
        else if(player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winer=2
        }
        else if(player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winer=1
        }
        else if(player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winer=2
        }
        else if(player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winer=1
        }
        else if(player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winer=2
        }

        if (winer==1){
            player1WinCount+=1

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Winner")
            builder.setMessage("Player 1 win the game ")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                restartGame()

                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()

            }
            builder.show()
            /*Toast.makeText(this,"Player 1 win the game ",Toast.LENGTH_LONG).show()
            restartGame()*/
        }else if(winer==2){
            player2WinCount+=1
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Winner")
            builder.setMessage("Player 2 win the game ")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                restartGame()
                finish()
                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
            }
            builder.show()
            /*  Toast.makeText(this,"Player 2 win the game ",Toast.LENGTH_LONG).show()
              restartGame()*/
        } /*else if (winer!=1 && winer!=2){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Winner")
            builder.setMessage("Game is Drow")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                restartGame()

                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()

            }
            builder.show()
        }*/


    }
    fun autoPlay() {

        var emptyCells=ArrayList<Int>()
        for (cellId : Int in 1..9){
            if (!(player1.contains(cellId) || player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }
        if (emptyCells.size==0){
            restartGame()
        }


        val r= Random()
        val randIndexer:Int=r.nextInt(emptyCells.size)
        val cellId:Int=emptyCells[randIndexer]
        var buSelected:Button?
        buSelected = when(cellId){
            1->bu1
            2->bu2
            3->bu3
            4->bu4
            5->bu5
            6->bu6
            7->bu7
            8->bu8
            9->bu9
            else -> {bu1}
        }
        playGame(cellId,buSelected)
    }

    var player1WinCount=0
    var player2WinCount=0
    /*val textView3:TextView=findViewById(R.id.textView3)  as TextView
    val textView5:TextView=findViewById(R.id.textView5)  as TextView*/


    fun restartGame(){
        activePlayer=1
        player1.clear()
        player2.clear()
        for (cellId:Int in 1..9){
            var buSelected:Button?
            buSelected = when(cellId){
                1->bu1
                2->bu2
                3->bu3
                4->bu4
                5->bu5
                6->bu6
                7->bu7
                8->bu8
                9->bu9
                else -> {bu1}
            }
            buSelected!!.text=""
          //  buSelected.setBackgroundResource(R.color.white)
            buSelected.setBackgroundTintList(getResources().getColorStateList(R.color.white));

            buSelected.isEnabled=true
        }
        textView3.setText("$player1WinCount")
        textView5.setText("$player2WinCount")
  Toast.makeText(this,"Player 1: $player1WinCount, Player 2: $player2WinCount ",Toast.LENGTH_LONG).show()

}

}

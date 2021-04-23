package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var PLAYER = true
    var TURN_COUNT = 0

    var boardStatus = Array(3) { IntArray(3) { 2 } }

    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )

        for (i: Array<Button> in board) {
            for (button: Button in i) {
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()
        resetBtn.setOnClickListener {
            TURN_COUNT = 0
            initializeBoardStatus()
            PLAYER = true
        }


    }

    private fun initializeBoardStatus() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER
        if (PLAYER) {
            updateDisplay("PLAYER X TURN")
        } else {
            updateDisplay("PLAYER O TURN")
        }
        if (TURN_COUNT == 9) {
            updateDisplay("GAME DRAW")
        }
        checkWinner()
    }

    private fun checkWinner() {

        //for horizontal
        for (i in 0..2) {
            if ((boardStatus[i][0] == boardStatus[i][1]) && (boardStatus[i][1] == boardStatus[i][2]) && (boardStatus[i][0] == 1 || boardStatus[i][0] == 0)) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }

        //for vertical
        for (j in 0..2) {
            if ((boardStatus[0][j] == boardStatus[1][j]) && (boardStatus[1][j] == boardStatus[2][j]) && (boardStatus[0][j] == 1 || boardStatus[0][j] == 0)) {
                if (boardStatus[0][j] == 1) {
                    updateDisplay("Player X Winner")
                    break
                } else if (boardStatus[0][j] == 0) {
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        //for Diagonal 1

        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player X Winner")
            } else if (boardStatus[0][0] == 0) {
                updateDisplay("Player O Winner")
            }
        }

        //Diagonal 2

        else if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X Winner")
            } else if (boardStatus[0][2] == 0) {
                updateDisplay("Player O Winner")
            }
        }

    }

    private fun disableButton() {
        for (i: Array<Button> in board) {
            for (button: Button in i) {
                button.isEnabled = false
            }
        }
    }

    private fun updateDisplay(text: String) {
        displayTv.text = text
        if (text.contains("Winner"))
            disableButton()
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value
    }

}
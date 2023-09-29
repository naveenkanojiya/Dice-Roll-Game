package com.example.diceroll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.diceroll.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var currentPlayer: Int = 1
    private var playerOneScore: Int = 0
    private var playerTwoScore: Int = 0
    private var winningScore: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        currentPlayer = 1

        binding.rollButton.setOnClickListener {
            val random = Random.nextInt(6) + 1

            val drawableResource = when (random) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            binding.dice.setImageResource(drawableResource)

            // update score player base on current player
            if (currentPlayer == 1) {
                playerOneScore += random
                binding.firstPlayer.text = playerOneScore.toString()
                currentPlayer = 2
            } else {
                playerTwoScore += random
                binding.secondPlayer.text = playerTwoScore.toString()
                currentPlayer = 1
            }
            binding.currentPlayer.text = currentPlayer.toString()

            // check if any player has reached the winning score
            if (playerOneScore >= winningScore){
                binding.winningText.visibility = View.VISIBLE
              //  binding.winningText.text = " Player One Won 💫💫💫💫"
                startActivity(Intent(this, WiningActivity::class.java))
            }else if (playerTwoScore >= winningScore){
                binding.winningText.visibility = View.VISIBLE
               // binding.winningText.text = " Player Two Won 💫💫💫💫"
                startActivity(Intent(this, WiningActivity::class.java))
            }

        }
    }
}
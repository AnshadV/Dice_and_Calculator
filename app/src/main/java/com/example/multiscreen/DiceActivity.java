package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DiceActivity extends AppCompatActivity {
    private ImageView imageView;
    private int[] diceArray = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    private int randomDiceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        imageView = findViewById(R.id.imageView);

        setTitle("Dice");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });

    }
    private void rollDice() {
        randomDiceValue = (int) (Math.random() * 6) + 1;
        imageView.setImageResource(diceArray[randomDiceValue - 1]);
        Toast.makeText(this, "Dice value: " + randomDiceValue, Toast.LENGTH_SHORT).show();
    }
}
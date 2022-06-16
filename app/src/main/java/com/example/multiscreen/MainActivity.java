package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_dice, btn_calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dice = findViewById(R.id.btn_dice);
        btn_calculator = findViewById(R.id.btn_calculator);

        btn_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to dice activity
                startActivity(new Intent(MainActivity.this, DiceActivity.class));

            }
        });

        btn_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked calculator", Toast.LENGTH_SHORT).show();
                //go to CalcActivity
                startActivity(new Intent(MainActivity.this, CalcActivity.class));
            }
        });



    }
}
package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_add, btn_sub, btn_mul, btn_div, btn_dot, btn_mod, btn_equal, btn_clear;
    TextView edit_text;
    boolean add, sub, mul, div, remain, decimal;
    double in1 = 0, in2 = 0;
    double result = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        setTitle("Calculator");

        //implement calculator
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_0 = findViewById(R.id.btn_0);
        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_dot = findViewById(R.id.btn_dot);
        btn_equal = findViewById(R.id.btn_equal);
        btn_clear = findViewById(R.id.btn_clear);
        edit_text = findViewById(R.id.edit_text);
        btn_mod = findViewById(R.id.btn_mod);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "1");
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "2");
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "3");
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "4");
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "5");
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "6");
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "7");
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "8");
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "9");
            }
        });
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText(edit_text.getText() + "0");
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_text.getText().length() != 0) {
                    in1 = Float.parseFloat(edit_text.getText()+ "");
                    add = true;
                    decimal = false;
                    edit_text.setText(null);
                }
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_text.getText().length() != 0) {
                    in1 = Float.parseFloat(edit_text.getText() + "");
                    sub = true;
                    decimal = false;
                    edit_text.setText(null);
                }
            }
        });
        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_text.getText().length() != 0) {
                    in1 = Float.parseFloat(edit_text.getText() + "");
                    mul = true;
                    decimal = false;
                    edit_text.setText(null);
                }
            }
        });
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_text.getText().length() != 0) {
                    in1 = Float.parseFloat(edit_text.getText() + "");
                    div = true;
                    decimal = false;
                    edit_text.setText(null);
                }
            }
        });
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(decimal) {

                }else{
                    edit_text.setText(edit_text.getText() + ".");
                    decimal = true;
                }
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_text.setText("");
            }
        });

        btn_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_text.getText().length() != 0) {
                    in1 = Float.parseFloat(edit_text.getText() + "");
                    remain = true;
                    decimal = false;
                    edit_text.setText(null);
                }
            }
        });

        //solve number format exception


        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method for adding numbers
                if(edit_text.getText().length() != 0) {
                    in2 = Float.parseFloat(edit_text.getText() + "");
                    if (add) {
                        result = in1 + in2;
                        edit_text.setText(result + "");
                        add = false;
                    }
                    //method for subtracting numbers
                    if (sub) {
                        result = in1 - in2;
                        edit_text.setText(result + "");
                        sub = false;
                    }
                    //method for multiplying numbers
                    if (mul) {
                        result = in1 * in2;
                        edit_text.setText(result + "");
                        mul = false;
                    }
                    //method for dividing numbers
                    if (div) {
                        result = in1 / in2;
                        edit_text.setText(result + "");
                        div = false;
                    }
                    //method for finding remainder
                    if (remain) {
                        result = in1 % in2;
                        edit_text.setText(result + "");
                        remain = false;
                    }

                }

            }
        });

    }
}
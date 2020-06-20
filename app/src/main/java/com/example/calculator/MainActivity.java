package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CalculatorModel calculator;

    private TextView text;


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putSerializable("calc", calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (CalculatorModel) savedInstanceState.getSerializable("calc");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

                int[] numberIds = new int[]{
                        R.id.zero,
                        R.id.one,
                        R.id.two,
                        R.id.three,
                        R.id.four,
                        R.id.five,
                        R.id.six,
                        R.id.seven,
                        R.id.eight,
                        R.id.nine,

                };

                int[] actionsIds = new int[]{
                        R.id.plus,
                        R.id.minus,
                        R.id.multiply,
                        R.id.divide,
                        R.id.equal
                };

                text = findViewById(R.id.text);

                calculator = new CalculatorModel();

                View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.onNumPressed(view.getId());
                        text.setText(calculator.getText());
                    }
                };
                View.OnClickListener actionButtonOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.onActionPressed(view.getId());
                        text.setText(calculator.getText());
                    }
                };
                for (int i = 0; i < numberIds.length; i++) {
                    findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);

                }
                for (int i = 0; i < actionsIds.length; i++) {
                    findViewById(actionsIds[i]).setOnClickListener(actionButtonOnClickListener);

                }

                findViewById(R.id.clean).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calculator.clean();
                        text.setText(calculator.getText());
                    }
                });
            }
        });
    }
}
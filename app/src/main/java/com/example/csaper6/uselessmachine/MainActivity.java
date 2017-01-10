package com.example.csaper6.uselessmachine;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Switch uselessSwitch;
    private Button destructButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //wires widgets
        uselessSwitch = (Switch) findViewById(R.id.switch_useless);
        destructButton = (Button) findViewById(R.id.button_destruct);
        //setup countdown timer for self destruct
        final CountDownTimer destructTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                destructButton.setText(l/1000 + "");
            }

            @Override
            public void onFinish() {
                finish();
            }
        };

        //set up a countdown timer using aN ANONYMOUS INNER CLASS
        final CountDownTimer timer = new CountDownTimer(2000, 50) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                //check if off & turn off
                if(uselessSwitch.isChecked()) {
                    uselessSwitch.setChecked(false);
                }
            }
        };
        //creates the anonymous inner class to serve as the event listener
        uselessSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Toast.makeText(MainActivity.this, b + "", Toast.LENGTH_SHORT).show();
                Log.d("SWITCH:", "onCheckedChanged: we clicked the thing");
                timer.start();
            }
        });

        //anonymous lister class
        //subclass of View.OnClickListener
        destructButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                destructTimer.start();
                destructButton.setClickable(false);
            }
        });
    }
}

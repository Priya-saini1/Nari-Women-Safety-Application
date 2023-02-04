package com.example.nari_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class fakecallamain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakecallamain);
        ImageButton button=(ImageButton) findViewById(R.id.imageButton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkswitch();
            }
        });
    }

    public void checkswitch()
    {

        Switch btn=(Switch) findViewById(R.id.switch1);
            btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Intent intent = new Intent(getApplicationContext(), incomingcall.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), Dial_call.class);
                    startActivity(intent);
                }
            }
        });

    }
}
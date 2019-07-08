package com.adielreuven.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button startBtn;
    private Button instructionBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instructionBtn = (Button) findViewById(R.id.insBtn);
        startBtn = (Button) findViewById(R.id.playBtn);

        instructionBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.insBtn:
                intent = new Intent(this, InstructionsActivity.class);
                startActivity(intent);
                break;

            case R.id.playBtn:
                intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                break;
        }
    }

}// end MainActivity class
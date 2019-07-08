package com.adielreuven.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InstructionsActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button startBtn;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        TextView instructionTxv = (TextView) findViewById(R.id.instrTextView);
        instructionTxv.setMovementMethod(new ScrollingMovementMethod());

        startBtn = (Button)findViewById(R.id.btnStartPlay);
        startBtn.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        if( v.getId() == R.id.btnStartPlay)
        {
            Intent intent  = new Intent(this, GameActivity.class);
            startActivity(intent);
        }
    }

}// end InstructionsActivity class

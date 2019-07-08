package com.adielreuven.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static com.adielreuven.tictactoe.GameBoard.O_WON;
import static com.adielreuven.tictactoe.GameBoard.X_WON;

public class GameActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView xScoreTxv, drawsTxv, oScoreTxv, turnTxv;
    public Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22, btnPlayAgain;
    private GameBoard game;
    private String strCurrentPlayer = "";
    private int[] strick;
    private MediaPlayer mediaPlayer;

    private int Xscore , Oscore , draws, currentXscore, currentOscore, currentDraws;

    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        strick = new int[3];
        mediaPlayer = MediaPlayer.create(this, R.raw.applause);

        xScoreTxv = findViewById(R.id.txvXscore);
        drawsTxv = findViewById(R.id.txvDraws);
        oScoreTxv = findViewById(R.id.txvOscore);
        turnTxv = findViewById(R.id.txvTurn);

        btn00 = findViewById(R.id.btn00ID);
        btn01 = findViewById(R.id.btn01ID);
        btn02 = findViewById(R.id.btn02ID);

        btn10 = findViewById(R.id.btn10ID);
        btn11 = findViewById(R.id.btn11ID);
        btn12 = findViewById(R.id.btn12ID);

        btn20 = findViewById(R.id.btn20ID);
        btn21 = findViewById(R.id.btn21ID);
        btn22 = findViewById(R.id.btn22ID);

        btnPlayAgain = findViewById(R.id.btnPlayAgainID);

        btn00.setOnClickListener(this);
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);

        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);

        btn20.setOnClickListener(this);
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);

        btnPlayAgain.setOnClickListener(this);

        game = new GameBoard();

        turnTxv.setText(game.getcurrentPlayerMark() + " Turn");

        btnPlayAgain.setEnabled(false);
        //Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v)
    {
        int row = 0, col = 0, gameStatus;
        strCurrentPlayer = Character.toString(game.getcurrentPlayerMark());

        switch (v.getId())
        {
            case R.id.btn00ID:
                btn00.setText(strCurrentPlayer);
                row = 0;
                col = 0;
                btn00.setEnabled(false);
                btn00.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btn01ID:
                btn01.setText(strCurrentPlayer);
                row = 0;
                col = 1;
                btn01.setEnabled(false);
                btn01.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btn02ID:
                btn02.setText(strCurrentPlayer);
                row = 0;
                col = 2;
                btn02.setEnabled(false);
                btn02.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btn10ID:
                btn10.setText(strCurrentPlayer);
                row = 1;
                col = 0;
                btn10.setEnabled(false);
                btn10.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btn11ID:
                btn11.setText(strCurrentPlayer);
                row = 1;
                col = 1;
                btn11.setEnabled(false);
                btn11.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btn12ID:
                btn12.setText(strCurrentPlayer);
                row = 1;
                col = 2;
                btn12.setEnabled(false);
                btn12.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btn20ID:
                btn20.setText(strCurrentPlayer);
                row = 2;
                col = 0;
                btn20.setEnabled(false);
                btn20.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btn21ID:
                btn21.setText(strCurrentPlayer);
                row = 2;
                col = 1;
                btn21.setEnabled(false);
                btn21.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btn22ID:
                btn22.setText(strCurrentPlayer);
                row = 2;
                col = 2;
                btn22.setEnabled(false);
                btn22.setTextColor(Color.parseColor("#000000"));
                break;

            case R.id.btnPlayAgainID:

                //if(mediaPlayer.isPlaying())
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Xscore = game.getXscore();
                Oscore = game.getOscore();
                draws = game.getDraws();

                game = new GameBoard();
                game.setXscore(Xscore);
                game.setOscore(Oscore);
                game.setDraws(draws);
                setButtons(true); // enable use of buttons
                cleanTextButtons();

                btnPlayAgain.setEnabled(false);
                turnTxv.setText("X" + " Turn");
                return;
        }

        game.turnPlayer(row, col); // assign mark in requested location

        gameStatus = game.isGameOver( game.getcurrentPlayerMark());

        if( gameStatus == X_WON || gameStatus == O_WON ||game.isDraw() == true ) // indicate to game over
        {
            Toast.makeText(this, "Game Over!", Toast.LENGTH_LONG).show();
            setButtons(false);
            btnPlayAgain.setEnabled(true);
        }

        if( gameStatus == X_WON)
        {

            strick=game.getWinningStrick(game.getcurrentPlayerMark());
            markStrick(strick);
            turnTxv.setText("X Won!");
            xScoreTxv.setText(game.getXscore() + ""); // update the num of wins for X player
            mediaPlayer.start();
            return;

        }
        else if( gameStatus == O_WON )
        {
            strick = game.getWinningStrick(game.getcurrentPlayerMark());
            markStrick(strick);
            turnTxv.setText("O Won!");
            oScoreTxv.setText(game.getOscore() + ""); // update the num of wins for O player
            mediaPlayer.start();
            return;

        }
        else if( game.isDraw() == true )
        {
            turnTxv.setText("Draw - No Winner!");
            drawsTxv.setText(game.getDraws() + "");
            return;

        }
        else // case that no win or draw
        {
            game.changePlayer();
            strCurrentPlayer = Character.toString(game.getcurrentPlayerMark());
            turnTxv.setText( strCurrentPlayer + " Turn");
        }
    }

    protected void onPause()
    {
        super.onPause();

        // save data into sheardPrefrences
        SharedPreferences sp = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("lastXscore", game.getXscore());
        editor.putInt("lastOscore", game.getOscore());
        editor.putInt("lastDraws", game.getDraws());
        editor.commit();
    }

    protected void onResume()
    {
        super.onResume();

        // read saved data from sheardPrefrences
        SharedPreferences sp = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
        currentXscore = sp.getInt("lastXscore",0);
        currentOscore = sp.getInt("lastOscore",0);
        currentDraws = sp.getInt("lastDraws",0);

        xScoreTxv.setText(currentXscore + "");
        oScoreTxv.setText(currentOscore + "");
        drawsTxv.setText(currentDraws + "");
    }

    public void setButtons(boolean state)
    {
        btn00.setEnabled(state);
        btn01.setEnabled(state);
        btn02.setEnabled(state);

        btn10.setEnabled(state);
        btn11.setEnabled(state);
        btn12.setEnabled(state);

        btn20.setEnabled(state);
        btn21.setEnabled(state);
        btn22.setEnabled(state);
    }

    public void cleanTextButtons()
    {
        btn00.setText("");
        btn01.setText("");
        btn02.setText("");

        btn10.setText("");
        btn11.setText("");
        btn12.setText("");

        btn20.setText("");
        btn21.setText("");
        btn22.setText("");
    }

    private void markStrick(int[] strick)
    {
        for(int i=0;i<3;i++)
        {
            if(strick[i]==0)
            {
                btn00.setTextColor(Color.BLUE);
            }
            else if(strick[i]==1)
            {
                btn01.setTextColor(Color.BLUE);
            }
            else if(strick[i]==2)
            {
                btn02.setTextColor(Color.BLUE);
            }
            else if(strick[i]==3)
            {
                btn10.setTextColor(Color.BLUE);
            }
            else if(strick[i]==4)
            {
                btn11.setTextColor(Color.BLUE);
            }
            else if(strick[i]==5)
            {
                btn12.setTextColor(Color.BLUE);
            }
            else if(strick[i]==6)
            {
                btn20.setTextColor(Color.BLUE);
            }
            else if(strick[i]==7)
            {
                btn21.setTextColor(Color.BLUE);
            }
            else if(strick[i]==8)
            {
                btn22.setTextColor(Color.BLUE);
            }
        }
    }

}// end GameActivity class
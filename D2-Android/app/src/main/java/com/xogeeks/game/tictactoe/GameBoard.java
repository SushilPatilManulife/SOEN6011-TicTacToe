package com.xogeeks.game.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameBoard extends AppCompatActivity implements View.OnClickListener {
    int turn=1;
    int setImageXorO;
    private int buttonLength=9;
    public Button gameBoardButton[]=new Button[buttonLength];
    public void checkButton() {

        gameBoardButton[0] = (Button) findViewById(R.id.boardButton1);
        gameBoardButton[1] = (Button) findViewById(R.id.boardButton2);
        gameBoardButton[2] = (Button) findViewById(R.id.boardButton3);
        gameBoardButton[3] = (Button) findViewById(R.id.boardButton4);
        gameBoardButton[4] = (Button) findViewById(R.id.boardButton5);
        gameBoardButton[5] = (Button) findViewById(R.id.boardButton6);
        gameBoardButton[6] = (Button) findViewById(R.id.boardButton7);
        gameBoardButton[7] = (Button) findViewById(R.id.boardButton8);
        gameBoardButton[8] = (Button) findViewById(R.id.boardButton9);

        for(int i=0;i<9;i++)
        gameBoardButton[i].setOnClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        checkButton();
    }
    public int checkTurn(){
        if(turn==0)
            turn=0;
     return turn;
    }
    @Override
    public void onClick(View v) {
        if(checkTurn()==1) {
            setImageXorO = R.drawable.xnew;
            turn=0;
        }
        else {
            setImageXorO = R.drawable.onew;
            turn=1;
        }
        switch (v.getId())
        {
            case R.id.boardButton1:
                gameBoardButton[0].setBackgroundResource(setImageXorO);
                gameBoardButton[0].setEnabled(false);
                break;
            case R.id.boardButton2:
                gameBoardButton[1].setBackgroundResource(setImageXorO);
                gameBoardButton[1].setEnabled(false);
                break;
            case R.id.boardButton3:
                gameBoardButton[2].setBackgroundResource(setImageXorO);
                gameBoardButton[2].setEnabled(false);
                break;
            case R.id.boardButton4:
                gameBoardButton[3].setBackgroundResource(setImageXorO);
                gameBoardButton[3].setEnabled(false);
                break;
            case R.id.boardButton5:
                gameBoardButton[4].setBackgroundResource(setImageXorO);
                gameBoardButton[4].setEnabled(false);
                break;
            case R.id.boardButton6:
                gameBoardButton[5].setBackgroundResource(setImageXorO);
                gameBoardButton[5].setEnabled(false);
                break;
            case R.id.boardButton7:
                gameBoardButton[6].setBackgroundResource(setImageXorO);
                gameBoardButton[6].setEnabled(false);
                break;
            case R.id.boardButton8:
                gameBoardButton[7].setBackgroundResource(setImageXorO);
                gameBoardButton[7].setEnabled(false);
                break;
            case R.id.boardButton9:
                gameBoardButton[8].setBackgroundResource(setImageXorO);
                gameBoardButton[8].setEnabled(false);
                break;
        }
    }
}

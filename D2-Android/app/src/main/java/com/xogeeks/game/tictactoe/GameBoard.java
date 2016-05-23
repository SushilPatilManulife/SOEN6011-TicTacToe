package com.xogeeks.game.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xogeeks.game.control.Controller;

public class GameBoard extends AppCompatActivity implements View.OnClickListener {
    int turn=1;
    int setImageX,setImageO;
    private int buttonLength=9;
    public Button gameBoardButton[]=new Button[buttonLength];
    Button checkButton1;
    static int checkPlayer=0;
    //static String turn;
    static String name1, name2;
    static String mark1;
    static String mark2;
    static String mark;
    static String btnValue[] = new String[9];
    int totalRound;
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
    public void setPlayers(){
        name1 = Controller.getPlayer1Name();
        name2 = Controller.getPlayer2Name();
        mark1 = Controller.getPlayer1Mark();
        mark2 = Controller.getPlayer2Mark();
        totalRound = Controller.getTotalRound();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        //String name1=getIntent().getExtras().getString("name1");
        setPlayers();
        checkButton();
    }
    public int checkTurn(){
        if(turn==0)
            turn=0;
     return turn;
    }
    public void setMarks(){
        if(mark1.equals("X")) {
            setImageX = R.drawable.xnew;
            setImageO = R.drawable.onew;
        }
        else {
            setImageX = R.drawable.onew;
            setImageO = R.drawable.xnew;
        }
    }
    @Override
    public void onClick(View v) {
        setMarks();
        String token;
        checkButton1=(Button)findViewById(v.getId());
        for (int i = 0; i < 9 ; i++) {
            if(checkButton1==gameBoardButton[i]&& checkPlayer < 9){
                if(checkPlayer % 2 == 0){
                    gameBoardButton[i].setBackgroundResource(setImageX);
                    btnValue[i] = mark1;
                    token = mark1;
                }
                else{
                    gameBoardButton[i].setBackgroundResource(setImageO);
                    btnValue[i] = mark2;
                    token = mark2;
                }
            }
        }

//        switch (v.getId())
//        {
//            case R.id.boardButton1:
//                Toast.makeText(this,""+v.getId()+":"+gameBoardButton[0].getId(),Toast.LENGTH_SHORT).show();
//                gameBoardButton[0].setBackgroundResource(setImageXorO);
//                gameBoardButton[0].setEnabled(false);
//                break;
//            case R.id.boardButton2:
//                gameBoardButton[1].setBackgroundResource(setImageXorO);
//                gameBoardButton[1].setEnabled(false);
//                break;
//            case R.id.boardButton3:
//                gameBoardButton[2].setBackgroundResource(setImageXorO);
//                gameBoardButton[2].setEnabled(false);
//                break;
//            case R.id.boardButton4:
//                gameBoardButton[3].setBackgroundResource(setImageXorO);
//                gameBoardButton[3].setEnabled(false);
//                break;
//            case R.id.boardButton5:
//                gameBoardButton[4].setBackgroundResource(setImageXorO);
//                gameBoardButton[4].setEnabled(false);
//                break;
//            case R.id.boardButton6:
//                gameBoardButton[5].setBackgroundResource(setImageXorO);
//                gameBoardButton[5].setEnabled(false);
//                break;
//            case R.id.boardButton7:
//                gameBoardButton[6].setBackgroundResource(setImageXorO);
//                gameBoardButton[6].setEnabled(false);
//                break;
//            case R.id.boardButton8:
//                gameBoardButton[7].setBackgroundResource(setImageXorO);
//                gameBoardButton[7].setEnabled(false);
//                break;
//            case R.id.boardButton9:
//                gameBoardButton[8].setBackgroundResource(setImageXorO);
//                gameBoardButton[8].setEnabled(false);
//                break;
//        }

    }
}

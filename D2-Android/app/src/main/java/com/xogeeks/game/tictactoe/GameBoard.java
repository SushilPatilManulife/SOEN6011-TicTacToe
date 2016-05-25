package com.xogeeks.game.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Process;
import android.renderscript.Short4;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xogeeks.game.control.Controller;

import java.util.Arrays;

public class GameBoard extends AppCompatActivity implements View.OnClickListener {
    //int turn=1;
    static Context context;
    int setImageX,setImageO;
    private static int buttonLength=9;
    public static Button gameBoardButton[]=new Button[buttonLength];
    TextView player1ScoreTextView,
             player2ScoreTextView,
             playersTurnTextView;
    Button checkButton1,
            backButton,
            exitButton,
            nextRound;
    ImageView xImageView;
    static int checkPlayer=0;
    static int currentRound;
    static String turn;
    static String name1, name2;
    static String mark1;
    static String mark2;
    static String mark;
    static String btnValue[] = new String[9];
    int totalRound;
    private static GameBoard gameBoard=null;
    GameBoard(){
        gameBoard=GameBoard.this;
    }
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

        backButton=(Button)findViewById(R.id.backButton);
        exitButton=(Button)findViewById(R.id.exitButton);
        nextRound=(Button)findViewById(R.id.startNextRoundButton);
        backButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        nextRound.setOnClickListener(this);
    }
    public void setPlayers(){
        name1 = Controller.getPlayer1Name();
        name2 = Controller.getPlayer2Name();
        mark1 = Controller.getPlayer1Mark();
        mark2 = Controller.getPlayer2Mark();
        totalRound = Controller.getTotalRound();
        turn=name1;
        mark=mark1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //String name1=getIntent().getExtras().getString("name1");
        setPlayers();
        player1ScoreTextView=(TextView)findViewById(R.id.player1ScoreTextView);
        player2ScoreTextView=(TextView)findViewById(R.id.player2ScoreTextView);
        player1ScoreTextView.setText(name1+": 0");
        player2ScoreTextView.setText(name2+": 0");
        playersTurnTextView=(TextView)findViewById(R.id.playersTurnTextView);
        playersTurnTextView.setText(turn+"'s turn");
        xImageView=(ImageView)findViewById(R.id.xImageView);
        if(mark1=="O")
            xImageView.setImageResource(R.drawable.o);
        int rounds= getIntent().getIntExtra("rounds",1);
        nextRound=(Button)findViewById(R.id.startNextRoundButton);
        nextRound.setVisibility(View.INVISIBLE);
        checkButton();

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
    private static void changePlayerTurn() {
        if(checkPlayer % 2 == 0) {
            turn = name2;
            mark=mark2;
        }
        else {
            turn = name1;
            mark=mark1;
        }

    }
    public void showAlert(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok",null);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
           @Override
            public void onClick(DialogInterface dialog,int which){
                dialog.dismiss();
           }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    public static void roundWon(int[] line,String token){

        gameBoard.showAlert("Result","Congratulations!! "+turn+" wins this round");
        for (int j = 0; j < 3 ; j++) {
            if(token.equals("X"))
                gameBoardButton[line[j]].setBackgroundResource(R.drawable.xwin);
            else
                gameBoardButton[line[j]].setBackgroundResource(R.drawable.owin);
        }
        gameBoard.nextRound.setVisibility(View.VISIBLE);
    }
    public static void roundTie(){
            gameBoard.showAlert("Result","It's a Tie!!!");
    }
    public static void gameWon(String result){
            gameBoard.showAlert("Game Winner",result);
            gameBoard.nextRound.setVisibility(View.INVISIBLE);
    }
    private void startNextRound(){
        changePlayerTurn();
        //resetBoard();
        Arrays.fill(btnValue, null);
        checkPlayer=0;
        currentRound++;
        nextRound.setVisibility(View.INVISIBLE);
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
                    gameBoardButton[i].setEnabled(false);
                    btnValue[i] = mark1;
                    token = mark1;
                }
                else{
                    gameBoardButton[i].setBackgroundResource(setImageO);
                    gameBoardButton[i].setEnabled(false);
                    btnValue[i] = mark2;
                    token = mark2;
                }
                Controller.checkStatus(btnValue, token, checkPlayer);
                changePlayerTurn();
                if(mark=="X") {
                    xImageView.setImageResource(R.drawable.x);
                }
                else {
                    xImageView.setImageResource(R.drawable.o);
                }
                playersTurnTextView.setText(turn+"'s turn");
                checkPlayer++;
            }
        }
        if(v.getId()==R.id.startNextRoundButton){
            startNextRound();
            recreate();
        }
        if(v.getId()==R.id.backButton){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Return to Menu ");
            builder
                    .setMessage("Are you Sure? \n you want to end your game")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            for(int i = 0 ; i < 9 ; i++){
                                gameBoardButton[i].setEnabled(true);
                                btnValue[i]="";
                                checkPlayer=0;
                            }
                            Intent goBack=new Intent("android.intent.action.PLAYERPROPERTIES");
                            finish();
                            startActivity(goBack);
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if(v.getId()==R.id.exitButton){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Exit Game");
            builder
                    .setMessage("Are you Sure? \n you want to Exit")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            for(int i = 0 ; i < 9 ; i++){
                                gameBoardButton[i].setEnabled(true);
                                btnValue[i]="";
                                checkPlayer=0;
                            }
                           finish();
                            moveTaskToBack(true);
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}

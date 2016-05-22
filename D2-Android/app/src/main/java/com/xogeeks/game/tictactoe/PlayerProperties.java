package com.xogeeks.game.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayerProperties extends AppCompatActivity {
    public Button startGameButton;
    public void startNewGame(){
        startGameButton=(Button)findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGame=new Intent("android.intent.action.GAMEBOARD" );
                startActivity(startGame);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_properties);
        startNewGame();
    }
}

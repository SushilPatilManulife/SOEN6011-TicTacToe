package com.xogeeks.game.tictactoe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/** <h1>Tic Tac Toe</h1>
 * Deliverable 2 is a Android Application that is able to play full tic tac toe game for two human players.
 * Functional requirements Completed are  <ol>
 *                                        <li> User can start a new game </li>
 *  									  <li> System displays the mark of the player who has the next move.</li>
 * 										  <li> System determines win, lose, or tied state</li>
 * 										  <li> Multiple rounds in one game</li>
 * 										  <li> System displays player’s scores</li>
 * 										  <li> Extra Features<ol><ul>Animations after player wins the game.</ul>
 * 										  <ul>Gift for winner</ul></ol></li>
 * 										  </ol>
 * <br>This class is First screen application when application starts.
 * @author Sushilpatil
 * @author Shidokht Hejazi Sepehr.
 * @version 2.0
 */
public class FirstActivity extends AppCompatActivity {
    public Button playButton;

    /**
     * This method creates button to start new activity to set player properties.
     */
    public void playNow(){
        playButton=(Button)findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setPlayerProperties= new Intent("android.intent.action.PLAYERPROPERTIES");
                startActivity(setPlayerProperties);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
            return;
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        playNow();
    }
}

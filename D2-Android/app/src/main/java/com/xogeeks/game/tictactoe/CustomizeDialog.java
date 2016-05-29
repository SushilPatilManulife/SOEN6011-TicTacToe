package com.xogeeks.game.tictactoe;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * This is customized dialog box with gui.
 * version 2.0
 */
public class CustomizeDialog extends Dialog implements OnClickListener {
    Button yesButton,noButton;
    Context context;
    TextView title = null;
    TextView message = null;
    View view = null;
    String checkOption;
    public CustomizeDialog(Context context,String checkOption) {
        super(context);
        this.context = context;
        this.checkOption = checkOption;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home);
        view = getWindow().getDecorView();
        view.setBackgroundResource(android.R.color.transparent);
        title = (TextView) findViewById(R.id.dialogTitle);
        message = (TextView) findViewById(R.id.dialogMessage);
        yesButton = (Button) findViewById(R.id.OkButton);
        noButton = (Button) findViewById(R.id.NoButton);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == yesButton && checkOption.equals("back")) {
            ((Activity) context).finish();
        }
        if(view == yesButton && checkOption.equals("exit")) {
            Intent intent = new Intent(context, FirstActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Exit me", true);
            ((Activity) context).startActivity(intent);
            ((Activity) context).finish();
        }

        if(view == noButton)
            cancel();
    }
    @Override
    public void setTitle(CharSequence title1) {
        super.setTitle(title1);
        title.setText(title1);
    }
    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        title.setText(context.getResources().getString(titleId));
    }
    /**
     * Set the message text for this dialog's window.
     *
     * @param message1
     *      - The new message to display in the title.
     */
    public void setMessage(CharSequence message1) {
        message.setText(message1);
        message.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
    /**
     * Set the message text for this dialog's window.
     *
     * @param messageId
     *      - the message's text
     */
    public void setMessage(int messageId) {
        message.setText(context.getResources().getString(messageId));
        message.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
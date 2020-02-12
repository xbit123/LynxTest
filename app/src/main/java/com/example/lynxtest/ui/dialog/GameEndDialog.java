package com.example.lynxtest.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lynxtest.R;

public class GameEndDialog extends Dialog {
    private final String TAG = this.getClass().getSimpleName();
    private final String msg;

    public GameEndDialog(@NonNull Activity activity, String msg) {
        super(activity);
        this.msg = msg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_game_end);
        try { getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); }
        catch (NullPointerException e) {
            Log.d(TAG, "No background");
        }
        TextView tvGameEnd = findViewById(R.id.tv_game_end);
        ImageView ivAgain = findViewById(R.id.iv_again);
        tvGameEnd.setText(msg);
        ivAgain.setOnClickListener((View view) -> dismiss());
    }
}

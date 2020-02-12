package com.example.lynxtest.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lynxtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.iv_game)
    ImageView ivGame;
    @BindView(R.id.iv_webview)
    ImageView ivWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ivGame.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });
        ivWebview.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, DollarActivity.class);
            startActivity(intent);
        });
    }
}

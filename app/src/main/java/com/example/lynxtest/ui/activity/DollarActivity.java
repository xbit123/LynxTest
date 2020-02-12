package com.example.lynxtest.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lynxtest.R;
import com.example.lynxtest.ui.viewmodel.DollarViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lynxtest.utils.AppConstants.CURRENT_USD;

public class DollarActivity extends AppCompatActivity {
    @BindView(R.id.iv_spinner)
    ImageView ivSpinner;

    private RotateAnimation rotate;
    private DollarViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dollar);
        ButterKnife.bind(this);

        viewModel = new ViewModelProvider(this).get(DollarViewModel.class);
        viewModel.getUsdLD().observe(this, (doubleStateData) -> {
            switch (doubleStateData.getStatus()) {
                case LOADING:
                    showSpinner();
                    break;
                case ERROR:
                    showError(doubleStateData.getError().getMessage());
                    break;
                case SUCCESS:
                    Intent intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra(CURRENT_USD, doubleStateData.getData());
                    startActivity(intent);
                    break;
            }
        });

        setupAnimation();
    }

    private void setupAnimation() {
        rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(900);
        rotate.setRepeatCount(Animation.INFINITE);
    }

    private void showSpinner() {
        ivSpinner.setVisibility(View.VISIBLE);
        ivSpinner.startAnimation(rotate);
    }

    private void hideSpinner() {
        rotate.cancel();
        ivSpinner.setVisibility(View.INVISIBLE);
    }

    private void showError(String msg) {
        hideSpinner();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}

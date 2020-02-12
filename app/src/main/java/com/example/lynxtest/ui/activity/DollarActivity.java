package com.example.lynxtest.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lynxtest.network.OkHttpSingleton;
import com.example.lynxtest.R;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.lynxtest.utils.AppConstants.CURRENT_USD;

public class DollarActivity extends AppCompatActivity {

    private static final String URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    private static final String VALUTE = "Valute";
    private static final String USD = "USD";
    private static final String VALUE = "Value";

    @BindView(R.id.iv_spinner)
    ImageView ivSpinner;

    private RotateAnimation rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dollar);
        ButterKnife.bind(this);

        rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(900);
        rotate.setRepeatCount(Animation.INFINITE);

        query();
    }

    private void showSpinner() {
        ivSpinner.setVisibility(View.VISIBLE);
        ivSpinner.startAnimation(rotate);
    }

    private void hideSpinner() {
        ivSpinner.getAnimation().cancel();
        ivSpinner.setVisibility(View.INVISIBLE);
    }

    private void showError(String msg) {
        runOnUiThread(() -> {
            hideSpinner();
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        });
    }

    private void query() {
        final Intent intent = new Intent(this, WebViewActivity.class);
        Request request = new Request.Builder().url(URL).build();
        showSpinner();

        OkHttpSingleton.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                showError("Connection error");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful())
                    showError("Unexpected code " + response);

                try {
                    String in = response.body().string();
                    JSONObject reader = new JSONObject(in);
                    double value = reader.getJSONObject(VALUTE).getJSONObject(USD).getDouble(VALUE);
                    intent.putExtra(CURRENT_USD, value);
                    startActivity(intent);
                } catch (NullPointerException e) {
                    showError("Null pointer exception " + e.getMessage());
                } catch (JSONException e) {
                    showError("Json parsing error: " + e.getMessage());
                } catch (IOException e) {
                    showError("IO exception" + e.getMessage());
                }
            }
        });
    }
}

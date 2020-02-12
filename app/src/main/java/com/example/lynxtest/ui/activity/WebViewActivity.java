package com.example.lynxtest.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lynxtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lynxtest.utils.AppConstants.CURRENT_USD;

public class WebViewActivity extends AppCompatActivity {
    private static final String BANKIRU = "https://www.banki.ru/products/currency/cash/moskva/";

    @BindView(R.id.tv_current_usd)
    TextView tvCurrentUsd;
    @BindView(R.id.btn_compare)
    Button btnCompare;
    @BindView(R.id.wv_bankiru)
    WebView wvBankiru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        try {
            Double value = getIntent().getExtras().getDouble(CURRENT_USD);
            tvCurrentUsd.setText(getString(R.string.current_usd, value));
        }
        catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.current_usd_unspecified), Toast.LENGTH_LONG).show();
        }

        btnCompare.setOnClickListener((View v) -> wvBankiru.loadUrl(BANKIRU));
    }
}

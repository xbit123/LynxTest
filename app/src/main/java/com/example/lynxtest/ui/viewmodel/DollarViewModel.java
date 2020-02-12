package com.example.lynxtest.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.lynxtest.network.OkHttpSingleton;
import com.example.lynxtest.utils.StateLiveData;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class DollarViewModel extends ViewModel {
    private static final String URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    private static final String VALUTE = "Valute";
    private static final String USD = "USD";
    private static final String VALUE = "Value";

    private StateLiveData<Double> usdLD;

    public DollarViewModel() {
        usdLD = new StateLiveData<>();
        query();
    }

    public StateLiveData<Double> getUsdLD() {
        return usdLD;
    }

    private void query() {
        usdLD.postLoading();

        Request request = new Request.Builder().url(URL).build();
        OkHttpSingleton.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                usdLD.postError(new Throwable("Connection error"));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (!response.isSuccessful())
                    usdLD.postError(new Throwable("Unexpected code " + response));

                try {
                    String in = response.body().string();
                    JSONObject reader = new JSONObject(in);
                    double value = reader
                            .getJSONObject(VALUTE)
                            .getJSONObject(USD)
                            .getDouble(VALUE);
                    usdLD.postSuccess(value);
                } catch (Exception e) {
                    usdLD.postError(new Throwable("Error parsing JSON"));
                }
            }
        });
    }
}

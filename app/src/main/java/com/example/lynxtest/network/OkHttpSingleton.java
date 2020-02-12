package com.example.lynxtest.network;

import okhttp3.OkHttpClient;

//Lazy init singleton for okHttp
public class OkHttpSingleton extends OkHttpClient {
    private static class LazyHolder {
        private static final OkHttpSingleton instance = new OkHttpSingleton();
    }

    public static OkHttpSingleton getInstance() {
        return LazyHolder.instance;
    }
}
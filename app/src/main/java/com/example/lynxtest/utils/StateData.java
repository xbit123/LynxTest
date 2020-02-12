package com.example.lynxtest.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StateData<T> {
    @NonNull
    private DataStatus status;

    @Nullable
    private T data;

    @Nullable
    private ErrorType error;

    StateData() {
        this.status = DataStatus.CREATED;
        this.data = null;
        this.error = null;
    }

    StateData<T> loading() {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    StateData<T> success(@NonNull T data) {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    StateData<T> error(@NonNull ErrorType error) {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    @NonNull
    public DataStatus getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public ErrorType getError() {
        return error;
    }

    public enum DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        LOADING,
    }

    public enum ErrorType {
        DOWNLOAD_ERROR,
        JSON_ERROR
    }
}
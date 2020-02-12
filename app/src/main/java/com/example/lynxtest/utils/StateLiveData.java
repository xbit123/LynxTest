package com.example.lynxtest.utils;

import androidx.lifecycle.MutableLiveData;

public class StateLiveData<T> extends MutableLiveData<StateData<T>> {
    public void postLoading() {
        postValue(new StateData<T>().loading());
    }

    public void postError(StateData.ErrorType error) {
        postValue(new StateData<T>().error(error));
    }

    public void postSuccess(T data) {
        postValue(new StateData<T>().success(data));
    }
}

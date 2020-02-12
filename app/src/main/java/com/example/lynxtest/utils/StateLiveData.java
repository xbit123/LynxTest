package com.example.lynxtest.utils;

import androidx.lifecycle.MutableLiveData;

public class StateLiveData<T> extends MutableLiveData<StateData<T>> {
    public void postLoading() {
        postValue(new StateData<T>().loading());
    }

    public void postError(Throwable throwable) {
        postValue(new StateData<T>().error(throwable));
    }

    public void postSuccess(T data) {
        postValue(new StateData<T>().success(data));
    }

    public void postComplete() {
        postValue(new StateData<T>().complete());
    }

}

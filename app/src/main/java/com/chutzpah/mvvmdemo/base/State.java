package com.chutzpah.mvvmdemo.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by xiaowu on 2019/03/07.
 */
public class State<T> {

    public static final int SUCCESS = 0;

    public static final int ERROR = 1;

    public static final int LOADING = 2;

    private int status;

    private String message;

    private T data;

    private State(@NonNull int status, @Nullable T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> State<T> success(T data) {
        return new State<>(0, data, null);
    }


    public static <T> State<T> error(String msg) {
        return new State<>(1, null, msg);
    }

    public static <T> State<T> error(String msg,T t) {
        return new State<>(1, t, msg);
    }



    public static <T> State<T> loading() {
        return new State<>(2, null, null);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}

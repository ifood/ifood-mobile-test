package com.rafamatias.nlp.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Resource<T> {
    public enum State {
        SUCCESS,
        ERROR,
        LOADING
    }

    @NonNull public final State state;
    @Nullable public final T data;
    @Nullable public final String message;
    private Resource(@NonNull State state, @Nullable T data, @Nullable String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(State.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg) {
        return new Resource<>(State.ERROR, null, msg);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(State.LOADING, null, null);
    }
}

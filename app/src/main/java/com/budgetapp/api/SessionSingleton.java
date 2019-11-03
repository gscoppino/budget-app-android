package com.budgetapp.api;

import com.auth0.android.jwt.JWT;

public class SessionSingleton {
    private static SessionSingleton instance;
    private JWT jwt;

    private SessionSingleton() {}

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public static synchronized SessionSingleton getInstance() {
        if (instance == null) {
            instance = new SessionSingleton();
        }

        return instance;
    }
}

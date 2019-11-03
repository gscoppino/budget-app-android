package com.budgetapp.api;

import com.auth0.android.jwt.JWT;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiTokenAuthenticationInterceptor implements Interceptor {
    private static final String AUTH_PREFIX = "Bearer ";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        SessionSingleton sessionSingleton = SessionSingleton.getInstance();

        JWT jwt = sessionSingleton.getJwt();
        if (jwt == null) {
            response = chain.proceed(chain.request());
        } else {
            response = chain.proceed(request.newBuilder()
                    .addHeader("Authorization", AUTH_PREFIX + jwt.toString())
                    .method(request.method(), request.body())
                    .build());
        }

        String authorizationHeader = response.header("Authorization");
        if (authorizationHeader == null) {
            // TODO: Set JWT to null here; however, all API's must return the header
            // back in order for this to work correctly
            return response;
        }

        if (authorizationHeader.startsWith(AUTH_PREFIX)) {
            sessionSingleton.setJwt(new JWT(authorizationHeader.substring(AUTH_PREFIX.length())));
        }

        return response;
    }
}

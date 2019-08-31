package com.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

public class LandingActivity extends AppCompatActivity implements
        LoginFragment.OnFragmentInteractionListener,
        RegistrationFragment.OnFragmentInteractionListener,
        DownloadCallback<String> {

    private NetworkFragment fragmentCheckUsernameAvailable;
    private boolean downloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);

        fragmentCheckUsernameAvailable = NetworkFragment.getInstance(
                getSupportFragmentManager(),
                "https://10.0.2.2:8080/api/users");
    }

    public void onLogin(View view, String username, String password) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onRegister(View view, String username, String password) {
        if (fragmentCheckUsernameAvailable != null && !downloading) {
            fragmentCheckUsernameAvailable.startDownload();
            downloading = true;
        }
    }

    public void onBegin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateFromDownload(String result) {
        //Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
        System.out.println(result);
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            case Progress.ERROR:
                break;
            case Progress.CONNECT_SUCCESS:
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                break;
        }
    }

    @Override
    public void finishDownloading() {
        downloading = false;
        if (fragmentCheckUsernameAvailable != null) {
            fragmentCheckUsernameAvailable.cancelDownload();
        }
    }
}

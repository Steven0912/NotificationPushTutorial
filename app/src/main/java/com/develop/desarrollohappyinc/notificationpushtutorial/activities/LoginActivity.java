package com.develop.desarrollohappyinc.notificationpushtutorial.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.develop.desarrollohappyinc.notificationpushtutorial.R;
import com.develop.desarrollohappyinc.notificationpushtutorial.fragments.LoginFragment;
import com.develop.desarrollohappyinc.notificationpushtutorial.utils.LoginInteractor;
import com.develop.desarrollohappyinc.notificationpushtutorial.utils.LoginPresenter;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements LoginFragment.Callback {

    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1;
    private FirebaseAuth mFirebaseAuth;
    private LoginPresenter mPresenter;
    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mLoginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nsv_login_container);
        if (mLoginFragment == null) {
            mLoginFragment = LoginFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.nsv_login_container, mLoginFragment)
                    .commit();
        }

        // Obtener instancia de firebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        LoginInteractor loginInteractor = new LoginInteractor(getApplicationContext(), mFirebaseAuth);
        mPresenter = new LoginPresenter(mLoginFragment, loginInteractor);
    }

    @Override
    public void onInvokeGooglePlayServices(int codeError) {
        showPlayServicesErrorDialog(codeError);
    }

    void showPlayServicesErrorDialog(
            final int errorCode) {
        Dialog dialog = GoogleApiAvailability.getInstance()
                .getErrorDialog(
                        LoginActivity.this,
                        errorCode,
                        REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }
}
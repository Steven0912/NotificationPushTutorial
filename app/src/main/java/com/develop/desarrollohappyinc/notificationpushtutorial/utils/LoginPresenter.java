package com.develop.desarrollohappyinc.notificationpushtutorial.utils;

import android.support.annotation.NonNull;

import com.develop.desarrollohappyinc.notificationpushtutorial.interfaces.LoginContract;

/**
 * Created by DESARROLLO HAPPY INC on 23/03/2017.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginInteractor.Callback {

    private final LoginContract.View mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenter(@NonNull LoginContract.View loginView,
                          @NonNull LoginInteractor loginInteractor) {
        mLoginView = loginView;
        loginView.setPresenter(this);
        mLoginInteractor = loginInteractor;
    }

    @Override
    public void start() {

    }

    @Override
    public void onEmailError(String msg) {
        mLoginView.showProgress(false);
        mLoginView.setEmailError(msg);
    }

    @Override
    public void onPasswordError(String msg) {
        mLoginView.showProgress(false);
        mLoginView.setPasswordError(msg);
    }

    @Override
    public void onNetworkConnectFailed() {
        mLoginView.showProgress(false);
        mLoginView.showNetworkError();
    }

    @Override
    public void onBeUserResolvableError(int errorCode) {
        mLoginView.showProgress(false);
        mLoginView.showGooglePlayServiceDialog(errorCode);
    }

    @Override
    public void onGooglePlayServicesFailed() {
        mLoginView.showGooglePlayServiceError();
    }

    @Override
    public void onAuthFailed(String msg) {
        mLoginView.showProgress(false);
        mLoginView.showLoginError(msg);
    }

    @Override
    public void onAuthSuccess() {
        mLoginView.showPushNotifications();
    }

    @Override
    public void attemptLogin(String email, String password) {
        mLoginView.showProgress(true);
        mLoginInteractor.login(email, password, this);
    }
}

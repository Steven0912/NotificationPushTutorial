package com.develop.desarrollohappyinc.notificationpushtutorial.interfaces;


/**
 * Created by DESARROLLO HAPPY INC on 21/03/2017.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showProgress(boolean show);

        void setEmailError(String error);

        void setPasswordError(String error);

        void showLoginError(String error);

        void showPushNotifications();

        void showGooglePlayServiceDialog(int errorCode);

        void showGooglePlayServiceError();

        void showNetworkError();
    }

    interface Presenter extends BasePresenter {
        void attemptLogin(String email, String password);
    }
}

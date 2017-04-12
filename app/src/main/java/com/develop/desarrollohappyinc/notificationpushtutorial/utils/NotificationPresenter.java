package com.develop.desarrollohappyinc.notificationpushtutorial.utils;

import android.text.TextUtils;

import com.develop.desarrollohappyinc.notificationpushtutorial.datas.PushNotificationModel;
import com.develop.desarrollohappyinc.notificationpushtutorial.datas.PushNotificationRepository;
import com.develop.desarrollohappyinc.notificationpushtutorial.interfaces.NotificacionContract;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

/**
 * Created by DESARROLLO HAPPY INC on 24/03/2017.
 */

public class NotificationPresenter implements NotificacionContract.Presenter {

    private final NotificacionContract.View mNotificationView;
    private final FirebaseMessaging mFCMInteractor;

    public NotificationPresenter(NotificacionContract.View mNotificationView, FirebaseMessaging mFCMInteractor) {
        this.mNotificationView = mNotificationView;
        this.mFCMInteractor = mFCMInteractor;

        mNotificationView.setPresenter(this);
    }

    @Override
    public void start() {
        registerAppClient();
        loadNotifications();
    }

    @Override
    public void registerAppClient() {
        mFCMInteractor.subscribeToTopic("test");
    }

    @Override
    public void loadNotifications() {
        PushNotificationRepository.getInstance().getPushNotifications(
                new PushNotificationRepository.LoadCallback() {
                    @Override
                    public void onLoaded(ArrayList<PushNotificationModel> notifications) {
                        if (notifications.size() > 0) {
                            mNotificationView.showEmptyState(false);
                            mNotificationView.showNotifications(notifications);
                        } else {
                            mNotificationView.showEmptyState(true);
                        }
                    }
                }
        );
    }

    @Override
    public void savePushMessage(String title, String description, String expiryDate, String discount) {
        PushNotificationModel pushMessage = new PushNotificationModel();
        pushMessage.setTitle(title);
        pushMessage.setDescription(description);
        pushMessage.setExpiryDate(expiryDate);
        pushMessage.setDiscount(TextUtils.isEmpty(discount) ? 0 : Float.parseFloat(discount));

        PushNotificationRepository.getInstance().savePushNotification(pushMessage);

        mNotificationView.showEmptyState(false);
        mNotificationView.popPushNotification(pushMessage);
    }
}

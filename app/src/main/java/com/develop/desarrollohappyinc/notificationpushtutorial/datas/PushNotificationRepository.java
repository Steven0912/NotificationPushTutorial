package com.develop.desarrollohappyinc.notificationpushtutorial.datas;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;

/**
 * Created by DESARROLLO HAPPY INC on 24/03/2017.
 */

public class PushNotificationRepository {
    private static ArrayMap<String, PushNotificationModel> LOCAL_PUSH_NOTIFICATIONS = new ArrayMap<>();
    private static PushNotificationRepository INSTANCE;

    private PushNotificationRepository() {
    }

    public static PushNotificationRepository getInstance() {
        if (INSTANCE == null) {
            return new PushNotificationRepository();
        } else {
            return INSTANCE;
        }
    }

    public void getPushNotifications(LoadCallback callback) {
        callback.onLoaded(new ArrayList<>(LOCAL_PUSH_NOTIFICATIONS.values()));
    }

    public void savePushNotification(PushNotificationModel notification) {
        LOCAL_PUSH_NOTIFICATIONS.put(notification.getId(), notification);
    }

    public interface LoadCallback {
        void onLoaded(ArrayList<PushNotificationModel> notifications);
    }
}

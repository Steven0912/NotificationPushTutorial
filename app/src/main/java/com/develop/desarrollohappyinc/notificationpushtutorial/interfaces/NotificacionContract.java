package com.develop.desarrollohappyinc.notificationpushtutorial.interfaces;

import com.develop.desarrollohappyinc.notificationpushtutorial.datas.PushNotificationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DESARROLLO HAPPY INC on 24/03/2017.
 */

public interface NotificacionContract {
    interface View extends BaseView<Presenter>{

        void showNotifications(ArrayList<PushNotificationModel> notifications);

        void showEmptyState(boolean empty);

        void popPushNotification(PushNotificationModel pushMessage);
    }

    interface Presenter extends BasePresenter{

        void registerAppClient();

        void loadNotifications();

        void savePushMessage(String title, String description,
                             String expiryDate, String discount);
    }
}

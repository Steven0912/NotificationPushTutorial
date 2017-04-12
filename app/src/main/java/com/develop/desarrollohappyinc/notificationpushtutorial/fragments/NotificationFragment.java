package com.develop.desarrollohappyinc.notificationpushtutorial.fragments;


import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.develop.desarrollohappyinc.notificationpushtutorial.R;
import com.develop.desarrollohappyinc.notificationpushtutorial.adapters.NotificationAdapter;
import com.develop.desarrollohappyinc.notificationpushtutorial.datas.PushNotificationModel;
import com.develop.desarrollohappyinc.notificationpushtutorial.interfaces.NotificacionContract;
import com.develop.desarrollohappyinc.notificationpushtutorial.utils.NotificationPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment implements NotificacionContract.View {

    public static final String ACTION_NOTIFY_NEW_PROMO = "NOTIFY_NEW_PROMO";
    private BroadcastReceiver mNotificationsReceiver;

    private RecyclerView mRecyclerView;
    private LinearLayout mNoMessagesView;
    private NotificationAdapter mAdapter;

    private NotificationPresenter mPresenter;


    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        // Setup de Argumentos
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Gets de argumentos
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        mAdapter = new NotificationAdapter();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_notifications_list);
        mNoMessagesView = (LinearLayout) v.findViewById(R.id.noMessages);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mNotificationsReceiver, new IntentFilter(ACTION_NOTIFY_NEW_PROMO));
    }

    @Override
    public void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(getActivity())
                .unregisterReceiver(mNotificationsReceiver);
    }

    @Override
    public void setPresenter(NotificacionContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = (NotificationPresenter) presenter;
        } else {
            throw new RuntimeException("El presenter de notificaciones no puede ser null");
        }
    }

    @Override
    public void showNotifications(ArrayList<PushNotificationModel> notifications) {
        mAdapter.setUpList(notifications);
    }

    @Override
    public void showEmptyState(boolean empty) {
        mRecyclerView.setVisibility(empty ? View.GONE : View.VISIBLE);
        mNoMessagesView.setVisibility(empty ? View.VISIBLE : View.GONE);
    }

    @Override
    public void popPushNotification(PushNotificationModel pushMessage) {
        mAdapter.addItem(pushMessage);
    }
}

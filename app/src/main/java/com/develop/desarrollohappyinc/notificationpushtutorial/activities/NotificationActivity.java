package com.develop.desarrollohappyinc.notificationpushtutorial.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.develop.desarrollohappyinc.notificationpushtutorial.R;
import com.develop.desarrollohappyinc.notificationpushtutorial.fragments.NotificationFragment;
import com.develop.desarrollohappyinc.notificationpushtutorial.utils.NotificationPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationActivity extends AppCompatActivity {

    private static final String TAG = NotificationActivity.class.getSimpleName();

    private NotificationFragment mNotificationsFragment;
    private NotificationPresenter mNotificationsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.title_activity_notifications));

        // ¿Existe un usuario logueado?
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        mNotificationsFragment =
                (NotificationFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.notifications_container);
        if (mNotificationsFragment == null) {
            mNotificationsFragment = NotificationFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.notifications_container, mNotificationsFragment)
                    .commit();
        }

        mNotificationsPresenter = new NotificationPresenter(
                mNotificationsFragment, FirebaseMessaging.getInstance());
    }
}

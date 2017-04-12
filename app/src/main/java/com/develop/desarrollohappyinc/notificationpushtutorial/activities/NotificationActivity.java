package com.develop.desarrollohappyinc.notificationpushtutorial.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    /*
    * // token steven
fMn2LBKTplw:APA91bFlMhgAILv3OMqlVV6HjmSOuyXITnSmsSSaC7mvBpWGLDWiR4Y2LUOlKjn29GuHWFtIDd0mUU1tQoRvETa5oZXSMYzUVF-VXSFlCLbl_yaWdclKKOQuWKRU3muOC0ghRqbIYcXi


// token andres
dKpHM_SU3U0:APA91bEocswlK7JZaoE_CDcTGHJLp8IYIjkwIXOjrE8q_FOGu2yHKKbl8jZwdqGxhcI2es73jmCRdNAYegkD_Pd7217Urutm_L5s2bvA4GgnB0uRZWfI0a0J8rjpsE2q3noxUFIpgA6c
    *
    * */

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

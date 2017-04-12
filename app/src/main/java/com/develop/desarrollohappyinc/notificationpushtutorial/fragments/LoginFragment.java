package com.develop.desarrollohappyinc.notificationpushtutorial.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.develop.desarrollohappyinc.notificationpushtutorial.R;
import com.develop.desarrollohappyinc.notificationpushtutorial.activities.LoginActivity;
import com.develop.desarrollohappyinc.notificationpushtutorial.activities.NotificationActivity;
import com.develop.desarrollohappyinc.notificationpushtutorial.interfaces.LoginContract;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private Button mSignInButton;
    private View mLoginForm;
    private View mLoginProgress;
    private TextInputLayout mEmailError;
    private TextInputLayout mPasswordError;
    private Callback mCallback;
    LoginContract.Presenter mPresenter;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public interface Callback {
        void onInvokeGooglePlayServices(int codeError);
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        // Setup de argumentos en caso de que los haya
        return fragment;
    }

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Extracción de argumentos en caso de que los haya
        }

        // Obtener instancia FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    showPushNotifications();
                } else {
                    Log.d("test", "logout");
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        mLoginForm = root.findViewById(R.id.ll_login_form);
        mLoginProgress = root.findViewById(R.id.pb_login_progress);

        mEmail = (TextInputEditText) root.findViewById(R.id.tv_email);
        mPassword = (TextInputEditText) root.findViewById(R.id.tv_password);
        mEmailError = (TextInputLayout) root.findViewById(R.id.til_email_error);
        mPasswordError = (TextInputLayout) root.findViewById(R.id.til_password_error);

        mSignInButton = (Button) root.findViewById(R.id.b_sign_in);

        // Eventos
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mEmailError.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPasswordError.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " debe implementar Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void attemptLogin() {
        mPresenter.attemptLogin(
                mEmail.getText().toString(),
                mPassword.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case LoginActivity.REQUEST_GOOGLE_PLAY_SERVICES:
                attemptLogin();
                break;
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        } else {
            throw new RuntimeException("El presenter no puede ser nulo");
        }
    }

    @Override
    public void showProgress(boolean show) {
        mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setEmailError(String error) {
        mEmailError.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        mPasswordError.setError(error);
    }

    @Override
    public void showLoginError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPushNotifications() {
        startActivity(new Intent(getActivity(), NotificationActivity.class));
        getActivity().finish();
    }

    @Override
    public void showGooglePlayServiceDialog(int errorCode) {
        mCallback.onInvokeGooglePlayServices(errorCode);
    }

    @Override
    public void showGooglePlayServiceError() {
        Toast.makeText(getActivity(),
                "Se requiere Google Play Services para usar la app", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getActivity(),
                "La red no está disponible. Conéctese y vuelva a intentarlo", Toast.LENGTH_LONG)
                .show();
    }
}


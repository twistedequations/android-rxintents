package com.twistedequations.rxintent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.twistedequations.rxintent.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rx_intent_button)
    Button button;

    @BindView(R.id.rx_permissions)
    Button permissionsButton;

    @BindView(R.id.rx_fingerprint)
    Button fingerprintButton;

    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        compositeSubscription.add(moveToRxIntentActivity(button));
        compositeSubscription.add(moveToRxPermissionsActivity(permissionsButton));
        compositeSubscription.add(moveToRxFingerprintActivity(fingerprintButton));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.clear();
    }

    private Subscription moveToRxIntentActivity(View view) {
        return RxView.clicks(view)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        RxIntentActivity.start(MainActivity.this);
                    }
                });
    }

    private Subscription moveToRxPermissionsActivity(View view) {
        return RxView.clicks(view)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        RxPermissionActivity.start(MainActivity.this);
                    }
                });
    }

    private Subscription moveToRxFingerprintActivity(View view) {
        return RxView.clicks(view)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                      RxFingerprintActivity.start(MainActivity.this);
                    }
                });
    }
}

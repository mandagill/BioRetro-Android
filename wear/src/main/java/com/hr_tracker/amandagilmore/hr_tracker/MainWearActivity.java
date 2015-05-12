package com.hr_tracker.amandagilmore.hr_tracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Wearable;

public class MainWearActivity extends Activity {


    private static final String TAG = "hrTracker";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        Log.d(TAG, "onCreate");

        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
//              This findByViewID is kind of slow, so this stores the view tree in TextView
                mTextView = (TextView) stub.findViewById(R.id.text);

                Log.d(TAG, "Instantiating API");

                GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(MainWearActivity.this)
                        .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                            @Override
                            public void onConnected(Bundle connectionHint) {
                                Log.d(TAG, "onConnected: " + connectionHint);
                                // Now you can use the Data Layer API
                            }
                            @Override
                            public void onConnectionSuspended(int cause) {
                                Log.d(TAG, "onConnectionSuspended: " + cause);
                            }
                        })
                        .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                Log.d(TAG, "onConnectionFailed: " + result);
                            }
                        })
                                // Request access only to the Wearable API
                        .addApi(Wearable.API)
                        .build();

                        mGoogleApiClient.connect();

            }
        });
    }
}

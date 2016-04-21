package cz.codecamp.seven;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Application class
 * Created by David Bilik[david.bilik@ackee.cz] on {21/04/16}
 **/
public class App extends Application {
    public static final String TAG = App.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

package fastpace.com.appme;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import fastpace.com.appme.database.ButtonTable;
import fastpace.com.appme.model.AppMeButton;

public class PersistService extends Service {

    public static final String PERSIST_BUTTON = "PERSIST_BUTTON";

    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        if (intent == null)
            return START_STICKY;

        String action = intent.getAction();
        if (action.equals(PERSIST_BUTTON))
            persistButton(intent.getExtras());

        return START_STICKY;
    }

    private void persistButton(Bundle extras) {

        AppMeButton button = (AppMeButton) extras.get(ButtonTable.TABLE_NAME);


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

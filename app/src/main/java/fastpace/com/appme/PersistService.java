package fastpace.com.appme;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;

import java.util.ArrayList;

import fastpace.com.appme.database.ButtonTable;
import fastpace.com.appme.database.Provider;
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

        @SuppressWarnings("unchecked")
        ArrayList<AppMeButton> buttons = (ArrayList<AppMeButton>) extras.get(ButtonTable.BUTTON_LIST);

        for (AppMeButton button : buttons) {
            getContentResolver().insert(Provider.BUTTON_CONTENT_URI, button.getContentValues());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

package fastpace.com.appme;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;

import java.util.ArrayList;

import fastpace.com.appme.database.ButtonTable;
import fastpace.com.appme.database.Provider;
import fastpace.com.appme.database.ScreenTable;
import fastpace.com.appme.model.AppMeButton;
import fastpace.com.appme.model.AppMeScreen;

public class PersistService extends Service {

    public static final String PERSIST_SCREEN = "PERSIST_SCREEN";

    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        if (intent == null)
            return START_STICKY;

        String action = intent.getAction();
        if (action.equals(PERSIST_SCREEN)) {
            persistScreen(intent.getExtras());
        }

        return START_STICKY;
    }

    private void persistScreen(Bundle extras) {
        AppMeScreen screen = (AppMeScreen) extras.getParcelable(ScreenTable.TABLE_NAME);
        persistButton(screen.getButtons());
    }

    private void persistButton(ArrayList<AppMeButton> buttons) {
        for (AppMeButton button : buttons) {

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

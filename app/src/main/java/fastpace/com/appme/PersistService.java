package fastpace.com.appme;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import java.util.ArrayList;

import fastpace.com.appme.database.ButtonTable;
import fastpace.com.appme.database.Provider;
import fastpace.com.appme.database.Provider.Button;
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
        AppMeScreen screen = extras.getParcelable(ScreenTable.TABLE_NAME);

        ContentValues contentValues = new ContentValues();
        contentValues.put(ScreenTable.APP_UUID, screen.getAppUuid());
        contentValues.put(ScreenTable.MAIN_SCREEN, screen.isMainScreen());

        if (screen.getDbId() != 0L) {
            getContentResolver().insert(Provider.SCREEN_CONTENT_URI, contentValues);
        }
        else
            getContentResolver().update(Provider.SCREEN_CONTENT_URI, contentValues, ScreenTable.WHERE_ID_EQUALS,
                    new String[] {Long.toString(screen.getDbId())});

        persistButton(screen.getButtons());
    }

    private void persistButton(ArrayList<AppMeButton> buttons) {
        for (AppMeButton button : buttons) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(ButtonTable.SCREEN_UUID, button.getScreenUuid());
            contentValues.put(ButtonTable.VIEW_ID, button.getViewId());
            contentValues.put(ButtonTable.PARENT, button.getParent());
            contentValues.put(ButtonTable.VIEW_TYPE, button.getType());
            contentValues.put(ButtonTable.POSITION, button.getPositionString());
            contentValues.put(ButtonTable.DRAWABLE, button.getDrawable());
            contentValues.put(ButtonTable.TEXT, button.getText());

            if (button.getDbId() != 0L) {
                getContentResolver().update(Provider.BUTTON_CONTENT_URI, contentValues, ButtonTable.WHERE_ID_EQUALS,
                        new String[] {Long.toString(button.getDbId())});
            } else {
                getContentResolver().insert(Provider.BUTTON_CONTENT_URI, contentValues);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

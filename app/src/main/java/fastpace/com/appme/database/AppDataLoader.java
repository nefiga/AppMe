package fastpace.com.appme.database;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

import fastpace.com.appme.LoadAppFinishedListener;
import fastpace.com.appme.model.AppMeButton;
import fastpace.com.appme.model.AppMeScreen;
import fastpace.com.appme.model.Position;

public class AppDataLoader extends IntentService{
    public static final String APP_DATA = "appData";
    public static final String LOAD_EDIT_DATA = "LOAD_EDIT_DATA";

    private static LoadAppFinishedListener mLoadListener;

    private static boolean mLocked;

    public AppDataLoader() {
        super("AppDataLoader");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        loadScreens(extras.getString(APP_DATA));
        unlock();
    }

    private void loadScreens(String appUuid) {
        Cursor cursor = getContentResolver().query(Provider.SCREEN_CONTENT_URI, new String[]{ScreenTable.MAIN_SCREEN, ScreenTable.UUID},
                ScreenTable.EDIT_APP_UUID + "=?", new String[]{appUuid}, null);
        while(cursor.moveToNext()) {
            boolean isMainScreen = cursor.getInt(cursor.getColumnIndex(ScreenTable.MAIN_SCREEN)) == 1;
            if (isMainScreen) {
                String uuid = cursor.getString(cursor.getColumnIndex(ScreenTable.UUID));
                loadScreen(new AppMeScreen(uuid, true));
            } else {
                String uuid = cursor.getString(cursor.getColumnIndex(ScreenTable.UUID));
                loadScreen(new AppMeScreen(uuid, false));
            }
        }
        cursor.close();
    }

    private void loadScreen(AppMeScreen screen) {
        setScreenButtons(screen);

        if (screen.isMainScreen())
            mLoadListener.loadMainScreenFinished(screen);
        else
            mLoadListener.loadScreenFinished(screen);
    }

    private void setScreenButtons(AppMeScreen screen) {
        Cursor cursor = getContentResolver().query(Provider.BUTTON_CONTENT_URI, ButtonTable.ALL_COLUMNS, ButtonTable.SCREEN_UUID + "=?", new String[] {screen.getUuid()}, null);
        ArrayList<AppMeButton> buttons = new ArrayList<>();
        while(cursor.moveToNext()) {
            int parent = cursor.getInt(cursor.getColumnIndex(ButtonTable.PARENT));
            int id = cursor.getInt(cursor.getColumnIndex(ButtonTable.VIEW_ID));
            String position = cursor.getString(cursor.getColumnIndex(ButtonTable.POSITION));
            AppMeButton button = new AppMeButton(parent, id, new Position(position));
            buttons.add(button);
        }
        cursor.close();

        screen.addButtons(buttons);
    }

    private void unlock() {
        mLocked = false;
    }

    public static boolean getLock() {
        if (mLocked)
            return false;

        return mLocked = true;
    }

    public static void setLoadListener(LoadAppFinishedListener loadListener) {
        mLoadListener = loadListener;
    }
}

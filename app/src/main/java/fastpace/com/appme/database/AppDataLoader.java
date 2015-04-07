package fastpace.com.appme.database;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import fastpace.com.appme.edit.EditFragment;
import fastpace.com.appme.edit.EditFragmentData;
import fastpace.com.appme.model.AppMeButton;
import fastpace.com.appme.model.AppMeScreen;

public class AppDataLoader extends IntentService{
    public static final String APP_DATA = "appData";
    public static final String LOAD_EDIT_DATA = "LOAD_EDIT_DATA";

    private String mMainScreen;

    private List<String> mScreenUuids;

    private EditFragmentData mEditFragmentData;

    public AppDataLoader() {
        super("AppDataLoader");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mEditFragmentData = new EditFragmentData();
        Bundle extras = intent.getExtras();
        getScreenUuids(extras.getString(APP_DATA));
    }

    private void getScreenUuids(String uuid) {
        mScreenUuids = new ArrayList<>();
        Cursor cursor = getContentResolver().query(Provider.SCREEN_CONTENT_URI, new String[]{ScreenTable.MAIN_SCREEN, ScreenTable.UUID},
                ScreenTable.PRIVATE_APP_UUID + "=?", new String[]{uuid}, null);
        while(cursor.moveToNext()) {
            boolean isMainScreen = cursor.getInt(cursor.getColumnIndex(ScreenTable.MAIN_SCREEN)) == 1;
            if (isMainScreen) {
                mMainScreen = cursor.getString(cursor.getColumnIndex(ScreenTable.UUID));
                loadMainScreen();
            } else {
                mScreenUuids.add(cursor.getString(cursor.getColumnIndex(ScreenTable.UUID)));
            }
        }
        cursor.close();
    }

    private void loadMainScreen() {
        Cursor cursor = getContentResolver().query(Provider.BUTTON_CONTENT_URI, ButtonTable.ALL_COLUMNS, ButtonTable.SCREEN_UUID + "=?", new String[] {mMainScreen}, null);
        ArrayList<AppMeButton> buttons = new ArrayList<>();
        while(cursor.moveToNext()) {
            int parent = cursor.getInt(cursor.getColumnIndex(ButtonTable.PARENT));
            int id = cursor.getInt(cursor.getColumnIndex(ButtonTable.ID));
            String position = cursor.getString(cursor.getColumnIndex(ButtonTable.POSITION));
            AppMeButton button = new AppMeButton(parent, id, position);
            buttons.add(button);
        }
        cursor.close();

        AppMeScreen mainScreen = new AppMeScreen(mMainScreen, true);

        mEditFragmentData.setMainScreen(mainScreen);
    }
}

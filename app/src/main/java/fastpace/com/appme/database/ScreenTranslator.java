package fastpace.com.appme.database;

import android.content.ContentValues;

import fastpace.com.appme.model.AppMeScreen;

public class ScreenTranslator {

    public ScreenTranslator() {

    }

    public ContentValues getContentValues(AppMeScreen screen) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ScreenTable.MAIN_SCREEN, screen.isMainScreen());
        contentValues.put(ScreenTable.APP_UUID, screen.getAppUuid());

        return contentValues;
    }
}

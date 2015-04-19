package fastpace.com.appme.edit;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import fastpace.com.appme.PersistService;
import fastpace.com.appme.database.ButtonTable;
import fastpace.com.appme.database.ScreenTable;
import fastpace.com.appme.model.AppMeButton;
import fastpace.com.appme.model.AppMeScreen;

public class IntentLauncher {

    public static void saveScreen(Context context, AppMeScreen screen) {
        Intent intent = new Intent(context, PersistService.class);
        intent.setAction(PersistService.PERSIST_SCREEN);
        intent.putExtra(ScreenTable.TABLE_NAME, screen);

        context.startService(intent);
    }
}

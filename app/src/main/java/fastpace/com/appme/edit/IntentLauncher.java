package fastpace.com.appme.edit;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import fastpace.com.appme.PersistService;
import fastpace.com.appme.database.ButtonTable;
import fastpace.com.appme.model.AppMeButton;

public class IntentLauncher {

    public static void saveButtons(Context context, ArrayList<AppMeButton> buttons) {
        Intent intent = new Intent(context, PersistService.class);
        intent.putExtra(ButtonTable.TABLE_NAME, buttons);
    }
}

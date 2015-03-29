package fastpace.com.appme.edit;

import android.content.Context;
import android.content.Intent;

import fastpace.com.appme.PersistService;
import fastpace.com.appme.model.AppMeButton;

public class IntentLauncher {

    public static void saveButton(Context context, AppMeButton button) {
        Intent intent = new Intent(context, PersistService.class);

    }
}

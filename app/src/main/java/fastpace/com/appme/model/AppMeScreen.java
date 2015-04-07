package fastpace.com.appme.model;

import java.util.ArrayList;

import fastpace.com.appme.edit.EditFragment;

public class AppMeScreen {
    private final boolean mMainScreen;

    private String mUuid;

    private ArrayList<AppMeButton> mButtons;

    public AppMeScreen(String uuid, boolean mainScreen) {
        mUuid = uuid;
        mMainScreen = mainScreen;
    }

    public boolean isMatch(String uuid) {
        return mUuid.equals(uuid);
    }

    public void loadViews(EditFragment fragment) {

    }

    public void addButtons(ArrayList<AppMeButton> buttons) {
        mButtons = buttons;
    }
}

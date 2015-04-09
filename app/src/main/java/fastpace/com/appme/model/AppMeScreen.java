package fastpace.com.appme.model;

import java.util.ArrayList;

public class AppMeScreen {
    private final boolean mMainScreen;

    private String mUuid;

    private ArrayList<AppMeButton> mButtons;

    public AppMeScreen(String uuid, boolean mainScreen) {
        mUuid = uuid;
        mMainScreen = mainScreen;
    }

    public boolean isMainScreen() {
        return mMainScreen;
    }

    public boolean isMatch(String uuid) {
        return mUuid.equals(uuid);
    }

    public String getUuid() {
        return mUuid;
    }

    public ArrayList<AppMeButton> getButtons() {
        return mButtons;
    }

    public void addButtons(ArrayList<AppMeButton> buttons) {
        mButtons = buttons;
    }
}

package fastpace.com.appme.edit;

import java.util.ArrayList;
import java.util.HashMap;

import fastpace.com.appme.action.Action;
import fastpace.com.appme.model.AppMeButton;
import fastpace.com.appme.model.AppMeScreen;
import fastpace.com.appme.model.AppMeView;
import fastpace.com.appme.model.Position;

public class EditFragmentData {
    public static final String TAG = "EditClass";
    private static final int ACTION_LIMIT = 40;

    private AppMeScreen mCurrentScreen;

    /**
     * Use screens uuid to store it in the HashMap
     */
    private HashMap<String, AppMeScreen> mScreens;

    private static ArrayList<Action> mActions;

    public EditFragmentData() {
        mActions = new ArrayList<>();
    }

    public void setCurrentScreen(String uuid) {
        mCurrentScreen = mScreens.get(uuid);
    }

    public void addScreen(AppMeScreen screen){
        if (mScreens == null)
            mScreens = new HashMap<>();

        mScreens.put(screen.getUuid(), screen);
    }

    public void undo() {
        mActions.get(mActions.size() -1).undo();
    }

    public AppMeScreen getCurrentScreen() {
        return mCurrentScreen;
    }

    public ArrayList<AppMeScreen> getScreens() {
        ArrayList<AppMeScreen> screens = new ArrayList<>();
        for (String uuid : mScreens.keySet()) {
            screens.add(mScreens.get(uuid));
        }

        return screens;
    }

    public void addView(Position position, int type) {
        switch (type) {
            case AppMeView.BUTTON:
                mCurrentScreen.addButton(new AppMeButton(10, 10, position));
        }
    }

    public static void addAction(Action action) {
        if (mActions.size() <= ACTION_LIMIT)
            action.setList(mActions);
    }
}

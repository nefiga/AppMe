package fastpace.com.appme.edit;

import java.util.ArrayList;
import java.util.List;

import fastpace.com.appme.action.Action;
import fastpace.com.appme.model.AppMeScreen;

public class EditFragmentData {
    public static final String TAG = "EditClass";
    private static final int ACTION_LIMIT = 40;

    private AppMeScreen mMainScreen;

    private List<AppMeScreen> screens;
    private static ArrayList<Action> mActions;

    public EditFragmentData() {
        mActions = new ArrayList<>();
    }

    public void setMainScreen(AppMeScreen mainScreen) {
        mMainScreen = mainScreen;
    }

    public void undo() {
        mActions.get(mActions.size() -1).undo();
    }

    public static void addAction(Action action) {
        if (mActions.size() <= ACTION_LIMIT)
            action.setList(mActions);
    }
}

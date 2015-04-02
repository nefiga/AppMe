package fastpace.com.appme.edit;

import java.util.ArrayList;

import fastpace.com.appme.action.Action;

public class Edit {
    private static final int ACTION_LIMIT = 40;

    private static ArrayList<Action> mActions;

    public Edit() {
        mActions = new ArrayList<>();
    }

    public void undo() {
        mActions.get(mActions.size() -1).undo();
    }

    public static void addAction(Action action) {
        if (mActions.size() <= ACTION_LIMIT)
            action.setList(mActions);
    }
}

package fastpace.com.appme.action;

import android.view.View;

import java.util.ArrayList;

public abstract class Action {
    public static final int CREATE = 0;
    public static final int CHANGE_TEXT = 1;
    public static final int CHANGE_POSITION = 2;
    public static final int CHANGE_DRAWABLE = 3;

    private ArrayList<Action> mActionList;

    protected int mActionType;
    private boolean mLocked;

    protected String mText;
    protected String mPosition;

    protected int mDrawable;

    /**
     * When a Action is created it will  automatically add itself to the list passed in the constructor.
     * The Action will also automatically remove itself from the list when undo is called on the Action.
     */
    public Action(ArrayList<Action> actionList) {
        mActionList = actionList;
        mActionList.add(this);
    }

    public abstract void create(View view);

    public void changeText(String text) {
        if (!mLocked) {
            mActionType = CHANGE_TEXT;
            mText = text;
            mLocked = true;
        }
    }

    public void changePosition(String position) {
        if (!mLocked) {
            mActionType = CHANGE_POSITION;
            mPosition = position;
            mLocked = true;
        }
    }

    public void channgeDrawable(int drawable) {
        if (!mLocked) {
            mActionType =  CHANGE_DRAWABLE;
            mDrawable = drawable;
            mLocked = true;
        }
    }

    public void undo() {
        mActionList.remove(this);
    }
}

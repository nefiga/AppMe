package fastpace.com.appme.action;

import java.util.ArrayList;

public abstract class Action {
    public static final int CREATE = 0;
    public static final int CHANGE_TEXT = 1;
    public static final int CHANGE_POSITION = 2;
    public static final int CHANGE_DRAWABLE = 3;

    private ArrayList<Action> mActionList;

    protected int mActionType;
    private boolean mLocked;

    protected String mOldText;
    protected String mOldPosition;

    protected int mOldDrawable;

    public void setList(ArrayList<Action> actionList) {
        actionList.add(this);
        mActionList = actionList;
    }

    public void setActionCreate() {
        if (!mLocked) {
            mActionType = CREATE;
            mLocked = true;
        }
    }

    public void setActionChangeText(String text) {
        if (!mLocked) {
            mActionType = CHANGE_TEXT;
            mOldText = text;
            mLocked = true;
        }
    }

    public void setActionChangePosition(String position) {
        if (!mLocked) {
            mActionType = CHANGE_POSITION;
            mOldPosition = position;
            mLocked = true;
        }
    }

    public void setActionChanngeDrawable(int drawable) {
        if (!mLocked) {
            mActionType =  CHANGE_DRAWABLE;
            mOldDrawable = drawable;
            mLocked = true;
        }
    }

    public void undo() {
        mActionList.remove(this);
    }
}

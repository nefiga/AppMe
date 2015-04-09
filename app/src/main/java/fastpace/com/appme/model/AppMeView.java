package fastpace.com.appme.model;

import java.io.Serializable;

public class AppMeView implements Serializable {

    public static final int BUTTON = 0;
    public static final int TEXT_VIEW = 1;
    public static final int EDIT_TEXT = 2;
    public static final int IMAGE_VIEW = 3;
    public static final int SPINNER = 4;
    public static final int LIST_VIEW = 5;

    public static final int NULL_VALUE = 0;

    protected int mViewId;
    protected int mType;
    protected int mParent;
    protected int mX;
    protected int mY;
    protected int mWidth;
    protected int mHeight;

    protected String mPosition;

    public AppMeView(int parent, int viewId, int type, String position) {
        mParent = parent;
        mViewId = viewId;
        mType = type;
        setPosition(position);
    }

    public AppMeView(int parent, int viewId, int type, int x, int y, int width, int height) {
        mParent = parent;
        mViewId = viewId;
        mType = type;
        setPosition(x, y, width, height);
    }

    public void setPosition(String position) {
        mPosition = position;
        String[] data = position.split(",");
        mX = Integer.valueOf(data[0]);
        mY = Integer.valueOf(data[1]);
        mWidth = Integer.valueOf(data[2]);
        mHeight = Integer.valueOf(data[3]);
    }

    public void setPosition(int x, int y, int width, int height) {
        String posistion = "";
        posistion += x + "," + y + "," + width + "," + height;
        mX = x;
        mY = y;
        mWidth = width;
        mHeight = height;
    }

    public String getPosition() {
        return mPosition;
    }

    public int getParent() {
        return mParent;
    }

    public int getId() {
        return mViewId;
    }

    public int getType() {
        return mType;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getmWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}

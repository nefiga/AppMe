package fastpace.com.appme.model;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Button;

import fastpace.com.appme.database.ButtonTable;

public class AppMeButton extends AppMeView{

    private int mDrawable;

    private String mText;

    public AppMeButton(int parent, int viewId, String position) {
        super(parent, viewId, AppMeView.BUTTON, position);
    }

    public AppMeButton(int parent, int viewId, int x, int y, int width, int height) {
        super(parent, viewId, AppMeView.BUTTON, x, y, width, height);
    }

    public void setDrawable(int drawable) {
        mDrawable = drawable;
    }

    public void setText(String text) {
        mText = text;
    }

    public Button getAndroidButton(Context context) {
        Button button = new Button(context);
        button.setX(mX);
        button.setY(mY);
        button.setWidth(mWidth);
        button.setHeight(mHeight);
        button.setId(mViewId);

        return button;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public String getText() {
        return mText;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ButtonTable.PARENT, mParent);
        contentValues.put(ButtonTable.VIEW_ID, mViewId);
        contentValues.put(ButtonTable.VIEW_TYPE, mType);
        contentValues.put(ButtonTable.POSITION, mPosition);

        if (mDrawable != NULL_VALUE)
            contentValues.put(ButtonTable.DRAWABLE, mDrawable);
        if (mText != null)
            contentValues.put(ButtonTable.TEXT, mText);

        return contentValues;
    }
}

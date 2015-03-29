package fastpace.com.appme.model;

import android.content.ContentValues;

import fastpace.com.appme.database.ButtonTable;

public class AppMeButton extends AppMeView{

    private int mDrawable;

    private String mText;

    public AppMeButton(int parent, String position) {
        super(parent, AppMeView.BUTTON, position);
    }

    public AppMeButton(int parent, int x, int y, int width, int height) {
        super(parent, AppMeView.BUTTON, x, y, width, height);
    }

    public void setDrawable(int drawable) {
        mDrawable = drawable;
    }

    public void setText(String text) {
        mText = text;
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
        contentValues.put(ButtonTable.VIEW_TYPE, mType);
        contentValues.put(ButtonTable.POSITION, mPosition);

        if (mDrawable != NULL_VALUE)
            contentValues.put(ButtonTable.DRAWABLE, mDrawable);
        if (mText != null)
            contentValues.put(ButtonTable.TEXT, mText);

        return contentValues;
    }
}

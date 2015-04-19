package fastpace.com.appme.model;

import android.content.ContentValues;
import android.content.Context;
import android.os.Parcel;
import android.widget.Button;

import fastpace.com.appme.database.ButtonTable;

public class AppMeButton extends AppMeView {

    private int mDrawable;

    private String mText;

    public AppMeButton(int parent, int viewId, Position position) {
        super(parent, viewId, AppMeView.BUTTON, position);
    }

    public AppMeButton(int parent, Button button) {
        super(parent, button.getId(), AppMeView.BUTTON, new Position(button.getX(), button.getY(), button.getWidth(), button.getHeight()));
    }

    public void setDrawable(int drawable) {
        mDrawable = drawable;
    }

    public void setText(String text) {
        mText = text;
    }

    public Button getAndroidButton(Context context) {
        Button button = new Button(context);
        button.setX(mPosition.getX());
        button.setY(mPosition.getY());
        button.setWidth(mPosition.getWidth());
        button.setHeight(mPosition.getHeight());
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
        contentValues.put(ButtonTable.POSITION, mPosition.getPositionString());

        if (mDrawable != NULL_VALUE)
            contentValues.put(ButtonTable.DRAWABLE, mDrawable);
        if (mText != null)
            contentValues.put(ButtonTable.TEXT, mText);

        return contentValues;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
        parcel.writeInt(mDrawable);
        parcel.writeString(mText);
    }

    @Override
    protected void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        mDrawable = parcel.readInt();
        mText = parcel.readString();
    }
}

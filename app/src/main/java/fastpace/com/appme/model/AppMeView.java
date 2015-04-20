package fastpace.com.appme.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AppMeView implements Parcelable {

    public static final int BUTTON = 0;
    public static final int TEXT_VIEW = 1;
    public static final int EDIT_TEXT = 2;
    public static final int IMAGE_VIEW = 3;
    public static final int SPINNER = 4;
    public static final int LIST_VIEW = 5;

    public static final int NULL_VALUE = 0;

    protected int mDbId;
    protected int mViewId;
    protected int mType;
    protected int mParent;
    protected String mScreenUuid;

    protected Position mPosition;

    public AppMeView(int parent, int viewId, int type, Position position) {
        mParent = parent;
        mViewId = viewId;
        mType = type;
        mPosition = position;
    }

    public void setPosition(Position position) {
        mPosition = position;
    }

    public void setPosition(float x, float y, int width, int height) {
        mPosition = new Position(x, y, width, height);
    }

    public void setDbId(int dbId) {
        mDbId = dbId;
    }

    public void setScreenUuid(String screenUuid) {
        mScreenUuid = screenUuid;
    }

    public Position getPosition() {
        return mPosition;
    }

    public int getParent() {
        return mParent;
    }

    public int getDbId() {
        return mDbId;
    }

    public int getId() {
        return mViewId;
    }

    public int getType() {
        return mType;
    }

    public String getScreenUuid() {
        return mScreenUuid;
    }

    public float getX() {
        return mPosition.getX();
    }

    public float getY() {
        return mPosition.getY();
    }

    public int getmWidth() {
        return mPosition.getWidth();
    }

    public int getHeight() {
        return mPosition.getHeight();
    }

    //------- Parcel Methods --------

    public AppMeView(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mDbId);
        parcel.writeInt(mViewId);
        parcel.writeInt(mType);
        parcel.writeInt(mParent);
        parcel.writeString(mScreenUuid);
        parcel.writeParcelable(mPosition, flags);
    }

    protected void readFromParcel(Parcel parcel) {
        mDbId = parcel.readInt();
        mViewId = parcel.readInt();
        mType = parcel.readInt();
        mParent = parcel.readInt();
        mScreenUuid = parcel.readString();
        mPosition = parcel.readParcelable(Position.class.getClassLoader());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AppMeView createFromParcel(Parcel parcel) {
            return new AppMeView(parcel);
        }

        public AppMeView[] newArray(int size) {
            return new AppMeView[size];
        }
    };
}

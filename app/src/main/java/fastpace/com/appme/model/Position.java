package fastpace.com.appme.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Position implements Parcelable {

    private final int POSITION_X = 0, POSITION_Y = 1, POSITION_WIDTH = 2, POSITION_HEIGHT = 3;

    private float mX;
    private float mY;
    private int mWidth;
    private int mHeight;

    private String mPosition;

    public Position(String position) {
        mPosition = position;
        String[] positionData = position.split(",");
        mX = Float.valueOf(positionData[POSITION_X]);
        mY = Float.valueOf(positionData[POSITION_Y]);
        mWidth = Integer.valueOf(positionData[POSITION_WIDTH]);
        mHeight = Integer.valueOf(positionData[POSITION_HEIGHT]);
    }

    public Position(float x, float y, int width, int height) {
        mX = x;
        mY = y;
        mWidth = width;
        mHeight = height;
        mPosition = getPositionString(x, y, width, height);
    }

    public Position(Parcel parcel) {
        readFromParcel(parcel);
    }
    
    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public String getPositionString() {
        return mPosition;
    }

    public static String getPositionString(float x, float y, int width, int height) {
        return  x + "," + y + "," + width + "," + height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeFloat(mX);
        parcel.writeFloat(mY);
        parcel.writeInt(mWidth);
        parcel.writeInt(mHeight);
        parcel.writeString(mPosition);
    }

    private void readFromParcel(Parcel parcel) {
        mX = parcel.readFloat();
        mY = parcel.readFloat();
        mWidth = parcel.readInt();
        mHeight = parcel.readInt();
        mPosition = parcel.readString();
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Position createFromParcel(Parcel parcel) {
                    return new Position(parcel);
                }

                public Position[] newArray(int size) {
                    return new Position[size];
                }
            };
}

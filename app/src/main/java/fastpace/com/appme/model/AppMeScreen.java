package fastpace.com.appme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

public class AppMeScreen implements Parcelable {
    private boolean mMainScreen;
    private boolean mChanged;

    private long mDbId;

    private String mAppUuid;
    private String mUuid;

    private ArrayList<AppMeButton> mButtons;

    public AppMeScreen(String uuid, boolean mainScreen) {
        mUuid = uuid;
        mMainScreen = mainScreen;
    }

    public void addButtons(ArrayList<AppMeButton> buttons) {
        mButtons = buttons;
    }

    public void addButton(AppMeButton button) {
        if (mButtons == null)
            mButtons = new ArrayList<>();

        button.setScreenUuid(mUuid);
        mButtons.add(button);
        mChanged = true;
    }

    public void setDbId(long dbId) {
        mDbId = dbId;
    }

    public void setAppUuid(String appUuid) {
        mAppUuid = appUuid;
    }

    public boolean isMainScreen() {
        return mMainScreen;
    }

    public boolean hasChanged() {
        return mChanged;
    }

    public boolean isMatch(String uuid) {
        return mUuid.equals(uuid);
    }

    public long getDbId() {
        return mDbId;
    }

    public String getAppUuid() {
        return mAppUuid;
    }

    public String getUuid() {
        return mUuid;
    }

    public ArrayList<AppMeButton> getButtons() {
        return mButtons;
    }

    private AppMeButton[] getButtonsArray() {
        return mButtons.toArray(new AppMeButton[mButtons.size()]);
    }

    private ArrayList<AppMeButton> getButtonArrayList(int size, Parcel parcel) {
        AppMeButton[] buttons = new AppMeButton[size];
        parcel.readTypedArray(buttons, AppMeButton.CREATOR);
        return new ArrayList<>(Arrays.asList(buttons));
    }

    //------Parcel Methods------

    public AppMeScreen(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(mDbId);
        parcel.writeInt(mMainScreen ? 1 : 0);
        parcel.writeString(mUuid);
        parcel.writeInt(mButtons.size());
        parcel.writeTypedArray(getButtonsArray(), flags);
    }

    protected void readFromParcel(Parcel parcel) {
        mDbId = parcel.readLong();
        mMainScreen = parcel.readInt() == 1;
        mUuid = parcel.readString();
        mButtons = getButtonArrayList(parcel.readInt(), parcel);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AppMeScreen createFromParcel(Parcel parcel) {
            return new AppMeScreen(parcel);
        }

        public AppMeScreen[] newArray(int size) {
            return new AppMeScreen[size];
        }
    };
}

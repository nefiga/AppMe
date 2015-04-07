package fastpace.com.appme.utils;

public class AppState {
    //TODO Just using constants for the app uuids for now until the back end is setup
    private static final String APP_UUID = "JDvoad92412asdfhja1";
    private static final String PRIVATE_APP_UUID = "jsaov8914jgqie0334nv";
    //TODO When logged the users app date should be loaded into static variables in this class

    public static String getUuid() {
        return APP_UUID;
    }

    public static String getPrivateUuid() {
        return PRIVATE_APP_UUID;
    }
}

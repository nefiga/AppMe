package fastpace.com.appme.utils;

public class Utils {
    //TODO Just using constants for the app uuids for now until the back end is setup
    public static final String APP_UUID = "JDvoad92412asdfhja1";
    public static final String PRIVATE_APP_UUID = "jsaov8914jgqie0334nv";

    public static final int VIEW_HOLDER_ID = 0;
    public static final String BLANK_STRING = "";

    private static int idCount = 0;

    public static int getNextId() {
        idCount++;
        return idCount;
    }
}

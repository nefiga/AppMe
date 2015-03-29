package fastpace.com.appme.utils;

public class Utils {

    public static final String BLANK_STRING = "";

    private static int idCount = 0;

    public static int getNextId() {
        idCount++;
        return idCount;
    }
}

package fastpace.com.appme.database;

public class ScreenTable extends TableBuilder {
    public static final String TABLE_NAME = begin("screen");

    public static final String MAIN_SCREEN = appendBoolean("main");
    public static final String APP_UUID = appendText("app_uuid");

    public static final String[] ALL_COLUMNS = retrieveAllColumnsArray();

    public static final String CREATE = retrieveCreateString();
}

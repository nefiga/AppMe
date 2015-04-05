package fastpace.com.appme.database;

public class ScreenTable extends TableBuilder {
    public static final String TABLE_NAME = beginCreate("screen");

    public static final String MAIN_SCREEN = appendToCreate("main", DB_BOOLEAN);
    public static final String APP_UUID = appendToCreate("app_uuid", DB_TEXT);
    public static final String PRIVATE_APP_UUID = appendToCreate("private_app_uuid", DB_TEXT);
}

package fastpace.com.appme.database;

public class ScreenTable extends TableBuilder {
    public static final String TABLE_NAME = begin("screen");

    public static final String MAIN_SCREEN = append("main", DB_BOOLEAN);
    public static final String APP_UUID = append("app_uuid", DB_TEXT);
    public static final String PRIVATE_APP_UUID = append("private_app_uuid", DB_TEXT);
}

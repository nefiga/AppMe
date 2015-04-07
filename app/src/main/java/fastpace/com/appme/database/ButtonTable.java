package fastpace.com.appme.database;

public class ButtonTable extends TableBuilder {
    public static final String TABLE_NAME = begin("button");

    public static final String SCREEN_UUID = append("screen_uuid", DB_TEXT);
    public static final String VIEW_ID = append("view_id", DB_INTEGER); // ID's should be unique to the screen
    public static final String PARENT = append("parent", DB_INTEGER);
    public static final String VIEW_TYPE = append("view_type", DB_INTEGER);
    public static final String POSITION = append("position", DB_TEXT);
    public static final String DRAWABLE = append("drawable", DB_INTEGER);
    public static final String TEXT = end("text", DB_TEXT);

    public static final String[] ALL_COLUMNS = retrieveAllColumnsArray();

    public static String CREATE = retrieveCreateString();
}

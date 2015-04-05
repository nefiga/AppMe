package fastpace.com.appme.database;

public class ButtonTable extends TableBuilder {
    public static final String TABLE_NAME = beginCreate("button");

    public static final String SCREEN_UUID = appendToCreate("screen_uuid", DB_TEXT);
    public static final String VIEW_ID = appendToCreate("view_id", DB_INTEGER); // ID's should be unique to the screen
    public static final String PARENT = appendToCreate("parent", DB_INTEGER);
    public static final String VIEW_TYPE = appendToCreate("view_type", DB_INTEGER);
    public static final String POSITION = appendToCreate("position", DB_TEXT);
    public static final String DRAWABLE = appendToCreate("drawable", DB_INTEGER);
    public static final String TEXT = endCreate("text", DB_TEXT);

    public static final String[] ALL_COLUMNS = retrieveAllColumnsArray();

    public static String CREATE = retrieveCreateString();
}

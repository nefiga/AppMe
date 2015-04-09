package fastpace.com.appme.database;

public class ButtonTable extends TableBuilder {
    public static final String TABLE_NAME = begin("button");

    public static final String SCREEN_UUID = appendText("screen_uuid");
    public static final String VIEW_ID = appendInt("view_id"); // ID's should be unique to the screen
    public static final String PARENT = appendInt("parent");
    public static final String VIEW_TYPE = appendInt("view_type");
    public static final String POSITION = appendText("position");
    public static final String DRAWABLE = appendInt("drawable");
    public static final String TEXT = end("text", DB_TEXT);

    public static final String BUTTON_LIST = "buttonList";

    public static final String[] ALL_COLUMNS = retrieveAllColumnsArray();

    public static String CREATE = retrieveCreateString();
}

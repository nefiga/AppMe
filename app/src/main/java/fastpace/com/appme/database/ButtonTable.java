package fastpace.com.appme.database;

public class ButtonTable extends TableBuilder {
    public static final String TABLE_NAME = beginCreate("button");

    public static final String PARENT = appendToCreate("parent", DB_INTEGER);
    public static final String VIEW_TYPE = appendToCreate("view_type", DB_INTEGER);
    public static final String POSITION = appendToCreate("position", DB_TEXT);
    public static final String DRAWABLE = appendToCreate("drawable", DB_INTEGER);
    public static final String TEXT = endCreate("text", DB_TEXT);

    public static final String[] ALL_COLUMNS = new String[] {
            ID,
            UUID,
            PARENT,
            VIEW_TYPE,
            POSITION,
            DRAWABLE,
            TEXT
    };

    public static String CREATE = retrieveCreateString();
}

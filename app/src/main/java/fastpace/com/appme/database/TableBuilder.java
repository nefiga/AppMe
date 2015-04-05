package fastpace.com.appme.database;

import java.util.ArrayList;

public class TableBuilder {
    public static final String DB_INTEGER = "INTEGER";
    public static final String DB_TEXT = "TEXT";
    public static final String DB_BOOLEAN = "BOOLEAN";

    public static final String ID = "_id";
    public static final String UUID = "uuid";
    public static final String APP_UUID = "app_uuid";

    public static final String WHERE_ID_EQUALS = ID + "=?";

    private static final String SPACE = " ";
    private static final String PERIOD = ".";
    private static final String COMMA = ",";

    private static StringBuilder createString;

    private static String currentTable;

    private static ArrayList<String> columns;

    public static String beginCreate(String tableName) {
        createString = new StringBuilder();
        columns = new ArrayList<>();
        currentTable = tableName;

        createString.append("CREATE TABLE ");
        createString.append(currentTable);
        createString.append(" ( ");
        createString.append(ID);
        createString.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        createString.append(UUID);
        createString.append(SPACE);
        createString.append(DB_TEXT);
        createString.append(COMMA);
        createString.append(APP_UUID);
        createString.append(DB_TEXT);
        createString.append(SPACE);
        createString.append(COMMA);

        columns.add(ID);
        columns.add(UUID);
        columns.add(APP_UUID);

        return tableName;
    }

    public static String appendToCreate(String columnName, String type) {
        createString.append(columnName);
        createString.append(SPACE);
        createString.append(type);
        createString.append(COMMA);

        columns.add(columnName);

        return columnName;
    }

    public static String endCreate(String columnName, String type) {
        createString.append(columnName);
        createString.append(SPACE);
        createString.append(type);
        createString.append(")");

        columns.add(columnName);

        return columnName;
    }

    public static String retrieveCreateString() {
        return createString.toString();
    }

    public static String[] retrieveAllColumnsArray() {
        String[] allColumns = new String[1];
        return columns.toArray(allColumns);
    }
}

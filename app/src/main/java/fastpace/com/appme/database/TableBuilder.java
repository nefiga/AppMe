package fastpace.com.appme.database;

public class TableBuilder {
    public static final String DB_INTEGER = "INTEGER";
    public static final String DB_TEXT = "TEXT";

    public static final String ID = "_id";
    public static final String UUID = "uuid";

    public static final String WHERE_ID_EQUALS = ID + "=?";

    private static final String SPACE = " ";
    private static final String PERIOD = ".";
    private static final String COMMA = ",";

    private static StringBuilder createString;

    private static String currentTable;

    public static String beginCreate(String tableName) {
        createString = new StringBuilder();
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

        return tableName;
    }

    public static String appendToCreate(String columnName, String type) {
        createString.append(columnName);
        createString.append(SPACE);
        createString.append(type);
        createString.append(COMMA);

        return columnName;
    }

    public static String endCreate(String columnName, String type) {
        createString.append(columnName);
        createString.append(SPACE);
        createString.append(type);
        createString.append(")");

        return columnName;
    }

    public static String retrieveCreateString() {
        return createString.toString();
    }
}

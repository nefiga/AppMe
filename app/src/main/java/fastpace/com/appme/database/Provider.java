package fastpace.com.appme.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class Provider extends ContentProvider{
    public static final String TAG = "appmeprovider";

    public static final String AUTHORITY = "fastpace.com.appme.database.Provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final Uri BUTTON_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, Button.CONTENT_PATH);
    public static final Uri SCREEN_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, Screen.CONTENT_PATH);

    private static final int BUTTON_DIR = 1;
    private static final int BUTTON_ID = 2;
    private static final int SCREEN_DIR = 3;
    private static final int SCREEN_ID = 4;

    private static final UriMatcher URI_MATCHER;

    private Database mDatabase;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        addToUriMatcher(Button.CONTENT_PATH, BUTTON_DIR, BUTTON_ID);
        addToUriMatcher(Screen.CONTENT_PATH, SCREEN_DIR, SCREEN_ID);
    }

    @Override
    public boolean onCreate() {
        mDatabase = new Database(getContext());
        return true;
    }

    private static void addToUriMatcher(String contentPath, int dir, int id) {
        URI_MATCHER.addURI(AUTHORITY, contentPath, dir);
        URI_MATCHER.addURI(AUTHORITY, contentPath + "/#", id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        final SQLiteDatabase databaseConnection = mDatabase.getWritableDatabase();

        switch (URI_MATCHER.match(uri)) {
            case BUTTON_ID:
                queryBuilder.appendWhere(ButtonTable.ID + "=" + uri.getPathSegments().get(1));
            case BUTTON_DIR:
                queryBuilder.setTables(ButtonTable.TABLE_NAME);
                break;
            case SCREEN_ID:
                queryBuilder.appendWhere(ScreenTable.ID + "=" + uri.getPathSegments().get(1));
            case SCREEN_DIR:
                queryBuilder.setTables(ScreenTable.TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        Cursor cursor = queryBuilder.query(databaseConnection, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case BUTTON_DIR:
                return Button.CONTENT_TYPE;
            case BUTTON_ID:
                return Button.CONTENT_ITEM_TYPE;
            case SCREEN_DIR:
                return Screen.CONTENT_TYPE;
            case SCREEN_ID:
                return Screen.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase databaseConnection  = mDatabase.getWritableDatabase();

        try {
            databaseConnection.beginTransaction();

            switch (URI_MATCHER.match(uri)) {
                case BUTTON_DIR:
                case BUTTON_ID:
                    return insert(databaseConnection, ButtonTable.TABLE_NAME, BUTTON_CONTENT_URI, values);
                case SCREEN_DIR:
                case SCREEN_ID:
                    return insert(databaseConnection, ScreenTable.TABLE_NAME, SCREEN_CONTENT_URI, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.endTransaction();
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return null;
    }

    private Uri insert(SQLiteDatabase dataBaseConnection, String tableName, Uri contentUri, ContentValues values)
            throws SQLException {
        final long id = dataBaseConnection.insertOrThrow(tableName, null, values);
        final Uri uri = ContentUris.withAppendedId(contentUri, id);
        getContext().getContentResolver().notifyChange(uri, null);
        dataBaseConnection.setTransactionSuccessful();
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase databaseConnection = mDatabase.getWritableDatabase();
        int deleteCount = 0;

        try {
            databaseConnection.beginTransaction();

            switch (URI_MATCHER.match(uri)) {
                case BUTTON_DIR:
                    deleteCount = deleteSelection(databaseConnection, ButtonTable.TABLE_NAME, selection, selectionArgs);
                    break;
                case BUTTON_ID:
                    deleteCount = deleteId(databaseConnection, ButtonTable.TABLE_NAME, uri);
                    break;
                case SCREEN_DIR:
                    deleteCount = deleteSelection(databaseConnection, ScreenTable.TABLE_NAME, selection, selectionArgs);
                    break;
                case SCREEN_ID:
                    deleteCount = deleteId(databaseConnection, ScreenTable.TABLE_NAME, uri);
                    break;
            }
        } finally {
            databaseConnection.endTransaction();
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleteCount;
    }

    private int deleteSelection(SQLiteDatabase databaseConnection, String tableName, String selection, String[] selectionArgs) {
        int deleteCount = databaseConnection.delete(tableName, selection, selectionArgs);
        databaseConnection.setTransactionSuccessful();
        return deleteCount;
    }

    private int deleteId(SQLiteDatabase databaseConnection, String tableName, Uri uri) {
        int deleteCount = databaseConnection.delete(tableName, TableBuilder.WHERE_ID_EQUALS, new String[] {uri.getPathSegments().get(1)});
        databaseConnection.setTransactionSuccessful();
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase databaseConnection = mDatabase.getWritableDatabase();
        int updateCount = 0;

        try {
            databaseConnection.beginTransaction();;

            switch (URI_MATCHER.match(uri)) {
                case BUTTON_DIR:
                    updateCount = updateSelection(databaseConnection, ButtonTable.TABLE_NAME, values, selection, selectionArgs);
                    break;
                case BUTTON_ID:
                    updateCount = updateId(databaseConnection, ButtonTable.TABLE_NAME, values, uri, selection, selectionArgs);
                    break;
                case SCREEN_DIR:
                    updateCount = updateSelection(databaseConnection, ScreenTable.TABLE_NAME, values, selection, selectionArgs);
                    break;
                case SCREEN_ID:
                    updateCount = updateId(databaseConnection, ScreenTable.TABLE_NAME, values, uri, selection, selectionArgs);
                    break;
            }
        } finally {
            databaseConnection.endTransaction();
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return updateCount;
    }

    private int updateSelection(SQLiteDatabase databaseConnection, String tableName, ContentValues values, String selection, String[] selectionArgs) {
        int updateCount = databaseConnection.update(tableName, values, selection, selectionArgs);
        databaseConnection.setTransactionSuccessful();
        return updateCount;
    }

    private int updateId(SQLiteDatabase databaseConnection, String tableName, ContentValues values, Uri uri, String selection, String[] selectionArgs) {
        final Long id = ContentUris.parseId(uri);
        int updateCount = databaseConnection.update(tableName, values, TableBuilder.ID + "=" + id + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")"), selectionArgs);
        databaseConnection.setTransactionSuccessful();
        return updateCount;
    }

    //---------- INNER CLASSES -----------

    public static String appendContentTypeToPath(String type) {
        return "vnd.android.cursor.dir/vnd.appme." + type;
    }

    public static String appendContentItemTypeToPath(String type) {
        return "vnd.android.cursor.item/vnd.appme." + type;
    }

    public static final class Button {
        public static final String CONTENT_PATH = "button";
        public static final String CONTENT_TYPE = appendContentTypeToPath(CONTENT_PATH);
        public static final String CONTENT_ITEM_TYPE = appendContentItemTypeToPath(CONTENT_PATH);
    }

    public static final class Screen {
        public static final String CONTENT_PATH = "screen";
        public static final String CONTENT_TYPE = appendContentTypeToPath(CONTENT_PATH);
        public static final String CONTENT_ITEM_TYPE = appendContentItemTypeToPath(CONTENT_PATH);
    }
}

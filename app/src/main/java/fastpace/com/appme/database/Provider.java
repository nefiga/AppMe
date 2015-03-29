package fastpace.com.appme.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class Provider extends ContentProvider{
    public static final String TAG = "appmeprovider";

    public static final String AUTHORITY = "fastpace.com.appme.database.Provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static final Uri BUTTON_CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, Buttons.CONTENT_PATH);

    private static final int BUTTON_DIR = 1;
    private static final int BUTTON_ID = 2;

    private static final UriMatcher URI_MATCHER;

    private Database mDatabase;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, Buttons.CONTENT_PATH, BUTTON_DIR);
        URI_MATCHER.addURI(AUTHORITY, Buttons.CONTENT_PATH + "/#", BUTTON_ID);
    }

    @Override
    public boolean onCreate() {
        mDatabase = new Database(getContext());
        return true;
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
                return Buttons.CONTENT_TYPE;
            case BUTTON_ID:
                return Buttons.CONTENT_ITEM_TYPE;
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
                    final long buttonId = databaseConnection.insertOrThrow(ButtonTable.TABLE_NAME, null, values);
                    final Uri button = ContentUris.withAppendedId(BUTTON_CONTENT_URI, buttonId);
                    getContext().getContentResolver().notifyChange(button, null);
                    databaseConnection.setTransactionSuccessful();
                    return button;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.endTransaction();
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase databaseConnection = mDatabase.getWritableDatabase();
        int deleteCount = 0;

        try {
            databaseConnection.beginTransaction();

            switch (URI_MATCHER.match(uri)) {
                case BUTTON_DIR:
                    deleteCount  = databaseConnection.delete(ButtonTable.TABLE_NAME, selection, selectionArgs);
                    databaseConnection.setTransactionSuccessful();
                    break;
                case BUTTON_ID:
                    deleteCount = databaseConnection.delete(ButtonTable.TABLE_NAME, ButtonTable.WHERE_ID_EQUALS, new String[] {uri.getPathSegments().get(1)});
                    databaseConnection.setTransactionSuccessful();
                    break;
            }
        } finally {
            databaseConnection.endTransaction();
            getContext().getContentResolver().notifyChange(uri, null);
        }

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
                    updateCount = databaseConnection.update(ButtonTable.TABLE_NAME, values, selection, selectionArgs);
                    databaseConnection.setTransactionSuccessful();
                    break;
                case BUTTON_ID:
                    final Long buttonId = ContentUris.parseId(uri);
                    updateCount = databaseConnection.update(ButtonTable.TABLE_NAME, values, ButtonTable.ID + "=" + buttonId + (TextUtils.isEmpty(selection) ? "" : " AND (" + selection + ")"), selectionArgs);
                    databaseConnection.setTransactionSuccessful();
                    break;
            }
        } finally {
            databaseConnection.endTransaction();
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return updateCount;
    }

    public static final class Buttons {
        public static final String CONTENT_PATH = "button";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.appme.button";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.appme.button";
    }
}

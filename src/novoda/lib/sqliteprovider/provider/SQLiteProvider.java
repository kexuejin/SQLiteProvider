
package novoda.lib.sqliteprovider.provider;

import novoda.lib.sqliteprovider.sqlite.ExtendedSQLiteOpenHelper;
import novoda.lib.sqliteprovider.util.UriUtils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class SQLiteProvider extends ContentProvider {

    private ExtendedSQLiteOpenHelper db;

    /**
     * @see android.content.ContentProvider#delete(Uri,String,String[])
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * @see android.content.ContentProvider#getType(Uri)
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * @see android.content.ContentProvider#insert(Uri,ContentValues)
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    /**
     * @see android.content.ContentProvider#onCreate()
     */
    @Override
    public boolean onCreate() {
        db = new ExtendedSQLiteOpenHelper(getContext());
        return true;
    }

    /**
     * @see android.content.ContentProvider#query(Uri,String[],String,String[],String)
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {

        final SQLiteQueryBuilder builder = getSQLiteQueryBuilder();
        final String tableName = UriUtils.getItemDirID(uri);
        builder.setTables(tableName);
        
        if (UriUtils.isNumberedEntryWithinCollection(uri)) {
            builder.appendWhere("_id=" + uri.getLastPathSegment());
        }
        
        return builder.query(getReadableDatabase(), projection, selection, selectionArgs, null,
                null, sortOrder, null);
    }

    /**
     * @see android.content.ContentProvider#update(Uri,ContentValues,String,String[])
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    protected SQLiteDatabase getReadableDatabase() {
        return db.getReadableDatabase();
    }

    // for testing
    SQLiteQueryBuilder getSQLiteQueryBuilder() {
        return new SQLiteQueryBuilder();
    }
}

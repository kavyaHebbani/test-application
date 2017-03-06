package kavya.sample.categorylibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public static class Columns implements BaseColumns {

        static final String TABLE_NAME = "CategoryTable";
        static final String CATEGORY_NAME = "CategoryName";
        static final String CATEGORY_WEIGHT = "CategoryWeight";
    }

    private static DatabaseHelper mInstance;

    private DatabaseHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @NonNull
    public static DatabaseHelper getInstance(@NonNull Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(context);
        }
        return mInstance;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Columns.TABLE_NAME + " (" +
                   Columns._ID + " INTEGER PRIMARY KEY," +
                   Columns.CATEGORY_NAME + " TEXT," +
                   Columns.CATEGORY_WEIGHT + " INTEGER)");
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Columns.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

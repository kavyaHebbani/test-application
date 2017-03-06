package kavya.sample.categorylibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kavya.sample.categorylibrary.model.Category;
import kavya.sample.categorylibrary.database.DatabaseHelper.Columns;

import static kavya.sample.categorylibrary.database.DatabaseHelper.Columns.CATEGORY_WEIGHT;
import static kavya.sample.categorylibrary.database.DatabaseHelper.Columns.TABLE_NAME;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class DatabaseWrapper {

    @NonNull
    private DatabaseHelper mDatabaseHelper;

    private static DatabaseWrapper mInstance;

    private static int refCount = 0;

    private DatabaseWrapper(@NonNull DatabaseHelper databaseHelper) {
        mDatabaseHelper = databaseHelper;
    }

    @NonNull
    public static DatabaseWrapper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseWrapper(DatabaseHelper.getInstance(context));
        }
        return mInstance;
    }

    public void saveCategory(@NonNull Category category, boolean update) {
        ContentValues values = new ContentValues();
        values.put(Columns.CATEGORY_NAME, category.getName());
        values.put(Columns.CATEGORY_WEIGHT, category.getNumberOfClicks());

        try {
            SQLiteDatabase db = getWriteableDB();
            if (update) {
                db.update(TABLE_NAME, values, Columns._ID + " = ? ",
                          new String[]{Integer.toString(category.getId())});
            } else {
                db.insert(TABLE_NAME, null, values);
            }
            close();
        } catch (SQLException e) {
            Log.e(getClass().getName(), "Error Saving category: " + e.getMessage());
        }
    }

    public int getCategoryNumberOfClicks(int index) {
        int numOfClicks = 0;
        try {
            SQLiteDatabase db = getReadableDB();
            String query = "Select * from " + TABLE_NAME + " where "
                           + Columns._ID + " = " + index;
            try (Cursor cursor = db.rawQuery(query, null)) {
                if (cursor.getCount() > 0) {
                    numOfClicks = cursor.getInt(cursor.getColumnIndex(CATEGORY_WEIGHT));
                }
                cursor.close();
            }
            close();
        } catch (SQLException e) {
            Log.e(getClass().getName(), "Error checking data: " + e.getMessage());
        }

        return numOfClicks;
    }

    @NonNull
    public List<Category> getCategories() {
        String query = "SELECT * FROM " + TABLE_NAME;

        List<Category> categories = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDB();
            try (Cursor cursor = db.rawQuery(query, null)) {
                if (cursor != null && cursor.getCount() != 0) {
                    int idIndex = cursor.getColumnIndex(Columns._ID);
                    int nameIndex = cursor.getColumnIndex(Columns.CATEGORY_NAME);
                    int weightIndex = cursor.getColumnIndex(Columns.CATEGORY_WEIGHT);

                    while (cursor.moveToNext()) {
                        categories.add(new Category(cursor.getInt(idIndex),
                                                    cursor.getString(nameIndex),
                                                    cursor.getInt(weightIndex)));
                    }
                }
                cursor.close();
                close();
            }
        } catch (SQLException e) {
            Log.e(getClass().getName(), "Error Getting category: " + e.getMessage());
        }

        return categories;
    }

    private SQLiteDatabase getReadableDB() {
        refCount++;
        return mDatabaseHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWriteableDB() {
        refCount++;
        return mDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        if (--refCount == 0) {
            mDatabaseHelper.close();
        }
    }
}

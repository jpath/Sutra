package sutra2.chantapps.jhughes.sutra2;
/**
 * Created by jhughes on 02/10/16.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new SQLiteOpenHelper(context) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of sutras
     */
    public List<String> getSutras() {
        List<String> list = new ArrayList<>();
        /* TODO: alternate readings, (eg. tatra added)
        * table alternate_readings, with foreign key sutra_id */
        Cursor cursor = database.rawQuery("SELECT phonetic FROM sutras", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    public int getChapterStartDbId(int chapterNum) {
        int startId;
        String query;
        
        query = String.format("SELECT _id FROM sutras WHERE pada = %d LIMIT 1", chapterNum);
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        startId = cursor.getInt(0);
        return startId;
    }
}

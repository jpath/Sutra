package sutra.chantapps.jhughes.sutra2;
/**
 * Created by jhughes on 02/10/16.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


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
        this.openHelper = new DatabaseOpenHelper(context) {
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
    @TargetApi(14)
    public void open() {
        this.database = openHelper.getWritableDatabase();
        if(this.database.isOpen()) {
            Log.d("opened", this.database.getPath());
        }
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
     * Read all sutras from the database.
     *
     * @return a List of sutras
     */
   public ArrayList<Sutra> getSutras() {
        this.open();
        ArrayList<Sutra> list = new ArrayList<>();
        /* TODO: alternate readings, (eg. tatra added)
       * table alternate_readings, with foreign key sutra_id */
        Cursor cursor = database.rawQuery("SELECT _id, phonetic, devanagari, pada FROM sutras", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String phonetic = cursor.getString(1);
            String devanagari = cursor.getString(2);
            int pada = cursor.getInt(3);
            Sutra s = new Sutra(id, phonetic, devanagari, pada);
            list.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        this.close();
        return list;
   }


    public int getChapterStartId(int chapterNum) {
        int startId;
        String query;
        this.open();
        query = String.format("SELECT _id FROM sutras WHERE pada = %d LIMIT 1", chapterNum);
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        startId = cursor.getInt(0);
        cursor.close();
        this.close();
        return startId;
    }
}


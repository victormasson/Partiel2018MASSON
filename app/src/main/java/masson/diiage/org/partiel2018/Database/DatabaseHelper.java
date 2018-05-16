package masson.diiage.org.partiel2018.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import masson.diiage.org.partiel2018.Entities.Release;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "partiel2018.db";

    public static final String TABLE_RELEASE = "release";
    public static final String TABLE_RELEASE_ID = "id";
    public static final String TABLE_RELEASE_STATUS = "status";
    public static final String TABLE_RELEASE_THUMB = "thumb";
    public static final String TABLE_RELEASE_FORMAT = "format";
    public static final String TABLE_RELEASE_TITLE = "title";
    public static final String TABLE_RELEASE_CATNO = "catno";
    public static final String TABLE_RELEASE_YEAR = "year";
    public static final String TABLE_RELEASE_RESOURCEURL = "resourceUrl";
    public static final String TABLE_RELEASE_ARTIST = "artist";

    public static  final String CREATE_TABLE_RELEASE = "CREATE TABLE `release` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `status` TEXT, `thumb` TEXT, `format` TEXT, `title` TEXT, `catno` TEXT, `year` INTEGER, `resourceUrl` TEXT, `artist` TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RELEASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addRelease(SQLiteDatabase db, Release release) {
        db.insert(TABLE_RELEASE, null, release.toContentValues());
    }

    public ArrayList<Release> getRelease(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_RELEASE,
                new String[] {TABLE_RELEASE_ID, TABLE_RELEASE_TITLE},
                null,
                null,
                null,
                null,
                null);

        ArrayList<Release> listRelease = new ArrayList<Release>();
        while(cursor.moveToNext()){
            long releaseId = cursor.getLong(0);
            String title = cursor.getString(1);

            Release release = new Release(releaseId, title);
            listRelease.add(release);
        }
        return listRelease;
    }
}

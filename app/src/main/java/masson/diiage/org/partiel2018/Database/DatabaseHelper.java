package masson.diiage.org.partiel2018.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public static final String TABLE_RELEASE_ARTIST_ID = "artistId";

    public static final String CREATE_TABLE_RELEASE = "CREATE TABLE `release` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `status` TEXT, `thumb` TEXT, `format` TEXT, `title` TEXT, `catno` TEXT, `year` INTEGER, `resourceUrl` TEXT, `artist` TEXT );";

    public static final String TABLE_ARTIST = "artist";
    public static final String TABLE_ARTIST_ID = "id";
    public static final String TABLE_ARTIST_STATUS = "name";

    public static final String CREATE_TABLE_ARTIST = "CREATE TABLE `artist` ( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `name` TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        baseUpdateTo(db, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 2; i <= newVersion; i++) {
            baseUpdateTo(db, i);
        }
        Log.d("db", "Base mise à jour");
    }

    public void baseUpdateTo(SQLiteDatabase db, int version) {
        switch (version) {
            case 1:
                db.execSQL(CREATE_TABLE_RELEASE);
            case 2:
                db.execSQL(CREATE_TABLE_ARTIST);
                db.execSQL("ALTER TABLE " + TABLE_RELEASE + " ADD " + TABLE_RELEASE_ARTIST_ID + ";");
                break;
            default:
                break;
        }
    }

    public void addRelease(SQLiteDatabase db, Release release) {
        db.insert(TABLE_RELEASE, null, release.toContentValues());
    }

    public ArrayList<Release> getRelease(SQLiteDatabase db) {
        Cursor cursor = db.query(TABLE_RELEASE,
                new String[] {TABLE_RELEASE_ID, TABLE_RELEASE_TITLE, TABLE_RELEASE_ARTIST, TABLE_RELEASE_YEAR, TABLE_RELEASE_CATNO},
                null,
                null,
                null,
                null,
                null);

        ArrayList<Release> listRelease = new ArrayList<Release>();
        while(cursor.moveToNext()){
            final long releaseId = cursor.getLong(0);
            final String title = cursor.getString(1);
            final String artist = cursor.getString(2);
            final long year = cursor.getLong(3);
            final String catno = cursor.getString(4);

            Release release = new Release() {{
                setId(releaseId);
                setTitle(title);
                setArtist(artist);
                setYear(year);
                setCatno(catno);
            }};
            listRelease.add(release);
        }
        return listRelease;
    }

    public Release getRelease(SQLiteDatabase db, long idRelease) {
        Cursor cursor = db.query(TABLE_RELEASE,
                new String[] {TABLE_RELEASE_ID, TABLE_RELEASE_TITLE, TABLE_RELEASE_ARTIST, TABLE_RELEASE_YEAR, TABLE_RELEASE_CATNO},
                TABLE_RELEASE_ID + " = ?",
                new String[] { String.valueOf(idRelease) },
                null,
                null,
                null);

        Release release = new Release();
        while(cursor.moveToNext()){
            final long releaseId = cursor.getLong(0);
            final String title = cursor.getString(1);
            final String artist = cursor.getString(2);
            final long year = cursor.getLong(3);
            final String catno = cursor.getString(4);

            release = new Release() {{
                setId(releaseId);
                setTitle(title);
                setArtist(artist);
                setYear(year);
                setCatno(catno);
            }};
        }
        return release;
    }
}

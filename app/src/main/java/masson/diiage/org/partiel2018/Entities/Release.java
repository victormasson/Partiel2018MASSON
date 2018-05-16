package masson.diiage.org.partiel2018.Entities;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import masson.diiage.org.partiel2018.Database.DatabaseHelper;

public class Release {
    public static class ReleaseBuilder {
        public static Release builder(JSONObject jsonObject) throws JSONException {
            Release release = new Release();

            release.setStatus(jsonObject.getString("status"));
            release.setThumb(jsonObject.getString("thumb"));
            release.setFormat(jsonObject.getString("format"));
            release.setTitle(jsonObject.getString("title"));
            release.setCatno(jsonObject.getString("catno"));
            release.setYear(jsonObject.getInt("year"));
            release.setResourceUrl(jsonObject.getString("resource_url"));
            release.setArtist(jsonObject.getString("artist"));
            release.setId(jsonObject.getInt("id"));

            return release;
        }
    }

    private String status;
    private String thumb;
    private String format;
    private String title;
    private String catno;
    private int year;
    private String resourceUrl;
    private String artist;
    private long id;

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TABLE_RELEASE_ID, this.getId());
        contentValues.put(DatabaseHelper.TABLE_RELEASE_STATUS, this.getStatus());
        contentValues.put(DatabaseHelper.TABLE_RELEASE_THUMB, this.getThumb());
        contentValues.put(DatabaseHelper.TABLE_RELEASE_FORMAT, this.getFormat());
        contentValues.put(DatabaseHelper.TABLE_RELEASE_TITLE, this.getTitle());
        contentValues.put(DatabaseHelper.TABLE_RELEASE_CATNO, this.getCatno());
        contentValues.put(DatabaseHelper.TABLE_RELEASE_YEAR, this.getYear());
        contentValues.put(DatabaseHelper.TABLE_RELEASE_RESOURCEURL, this.getResourceUrl());
        contentValues.put(DatabaseHelper.TABLE_RELEASE_ARTIST, this.getArtist());
        return contentValues;
    }

    public static ContentValues toContentValues(String status, String thumb, String format, String title, String catno, int year, String resourceUrl, String artist, int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TABLE_RELEASE_ID, id);
        contentValues.put(DatabaseHelper.TABLE_RELEASE_STATUS, status);
        contentValues.put(DatabaseHelper.TABLE_RELEASE_THUMB, thumb);
        contentValues.put(DatabaseHelper.TABLE_RELEASE_FORMAT, format);
        contentValues.put(DatabaseHelper.TABLE_RELEASE_TITLE, title);
        contentValues.put(DatabaseHelper.TABLE_RELEASE_CATNO, catno);
        contentValues.put(DatabaseHelper.TABLE_RELEASE_YEAR, year);
        contentValues.put(DatabaseHelper.TABLE_RELEASE_RESOURCEURL, resourceUrl);
        contentValues.put(DatabaseHelper.TABLE_RELEASE_ARTIST, artist);
        return contentValues;
    }

    public Release() {}

    public Release(String title) {
        this.title = title;
    }

    public Release(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Release(String status, String thumb, String format, String title, String catno, int year, String resourceUrl, String artist, int id) {
        this.status = status;
        this.thumb = thumb;
        this.format = format;
        this.title = title;
        this.catno = catno;
        this.year = year;
        this.resourceUrl = resourceUrl;
        this.artist = artist;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatno() {
        return catno;
    }

    public void setCatno(String catno) {
        this.catno = catno;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

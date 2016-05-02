package com.androidfung.newsreader.ui;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.Time;

import com.androidfung.newsreader.provider.FeedContract;

/**
 * Created by fung on 4/30/2016.
 */
public class NewsRecord implements Parcelable {

    /**
     * Projection for querying the content provider.
     */
    private static final String[] PROJECTION = new String[]{
            FeedContract.Entry._ID,
            FeedContract.Entry.COLUMN_NAME_TITLE,
            FeedContract.Entry.COLUMN_NAME_LINK,
            FeedContract.Entry.COLUMN_NAME_PUBLISHED,
            FeedContract.Entry.COLUMN_NAME_SUMMARY,
            FeedContract.Entry.COLUMN_NAME_CONTENT
    };

    // Column indexes. The index of a column in the Cursor is the same as its relative position in
    // the projection.
    /**
     * Column index for _ID
     */
    private static final int COLUMN_ID = 0;
    /**
     * Column index for title
     */
    private static final int COLUMN_TITLE = 1;
    /**
     * Column index for link
     */
    private static final int COLUMN_URL_STRING = 2;
    /**
     * Column index for published
     */
    private static final int COLUMN_PUBLISHED = 3;
    /**
     * Column index for summary
     */
    private static final int COLUMN_SUMMARY = 4;
    /**
     * Column index for content
     */
    private static final int COLUMN_CONTENT = 5;


//    /**
//     * List of Cursor columns to read from when preparing an adapter to populate the ListView.
//     */
//    private static final String[] FROM_COLUMNS = new String[]{
//            FeedContract.Entry.COLUMN_NAME_TITLE,
//            FeedContract.Entry.COLUMN_NAME_PUBLISHED
//    };

    private String mTtile;
    private String mDatetime;
    private String mLink;
    private String mContent;
    private String mSummary;

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        this.mSummary = summary;
    }

    public String getTtile() {
        return mTtile;
    }

    public void setTtile(String mTtile) {
        this.mTtile = mTtile;
    }

    public String getDatetime() {
        return mDatetime;
    }

    public void setDatetime(String mDatetime) {
        this.mDatetime = mDatetime;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public static NewsRecord fromCursor(Cursor cursor) {
        //TODO return your MyListItem from cursor.
        NewsRecord r = new NewsRecord();
        r.setTtile(cursor.getString(COLUMN_TITLE));
        Time t = new Time();
        t.set(cursor.getLong(COLUMN_PUBLISHED));
        r.setDatetime(t.format("%Y-%m-%d %H:%M"));
        r.setLink(cursor.getString(COLUMN_URL_STRING));
        r.setSummary(cursor.getString(COLUMN_SUMMARY));
        r.setContent(cursor.getString(COLUMN_CONTENT));
        return r;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTtile);
        dest.writeString(mSummary);
        dest.writeString(mContent);
        dest.writeString(mDatetime);
        dest.writeString(mLink);
    }

    public static final Parcelable.Creator<NewsRecord> CREATOR
            = new Parcelable.Creator<NewsRecord>() {
        public NewsRecord createFromParcel(Parcel in) {
            return new NewsRecord(in);
        }

        public NewsRecord[] newArray(int size) {
            return new NewsRecord[size];
        }
    };

    private NewsRecord(Parcel in) {
        mTtile = in.readString();
        mSummary = in.readString();
        mContent = in.readString();
        mDatetime = in.readString();
        mLink = in.readString();
    }

    public NewsRecord() {
    }
}

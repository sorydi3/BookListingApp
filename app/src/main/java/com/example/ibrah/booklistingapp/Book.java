package com.example.ibrah.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ibrah on 16/07/2017.
 */

public class Book implements Parcelable {
    /**
     * A {@link Book} object that contains details related to a single
     * book item to be displayed in BookListActivity
     */

    /**
     * Book Title
     */
    private String mTitle;

    /**
     * Book Author
     */
    private String mAuthor;

    /**
     * Rating
     */
    private String mRating;

    /**
     * Description
     */
    private String mDescription;

    /**
     * Thumbnail Link
     */
    private String mThumbnailLink;


    /**
     * Default Constructor - Constructs a new {@link Book} object
     *
     * @param title         - Title of the book
     * @param author        - Author of the book
     * @param rating        - Rating of the book (e.g. 3.5)
     * @param description   - Description of the book
     * @param thumbnailLink - Link for the book image
     */
    public Book(String title, String author,
                String rating, String description, String thumbnailLink) {

        mTitle = title;
        mAuthor = author;
        mRating = rating;
        mDescription = description;
        mThumbnailLink = thumbnailLink;
    }

    public Book(Parcel input) {

        mTitle = input.readString();
        mAuthor = input.readString();
        mRating = input.readString();
        mDescription = input.readString();
        mThumbnailLink = input.readString();
    }


    /**
     * Getter method - Title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Setter method - Title
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * Getter method - Author
     */
    public String getAuthor() {
        return mAuthor;
    }

    /**
     * Setter method - Author
     */
    public void setAuthor(String author) {
        mAuthor = author;
    }


    /**
     * Getter method - Rating
     */
    public String getRating() {
        return mRating;
    }

    /**
     * Setter method - Rating
     */
    public void setRating(String rating) {
        mRating = rating;
    }

    /**
     * Getter method - Description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Setter method - Description
     */
    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     * Getter method - Thumbnail Link
     */
    public String getThumbnailLink() {
        return mThumbnailLink;
    }

    /**
     * Setter method - Thumbnail Link
     */
    public void setThumbnailLink(String thumbnailLink) {
        mThumbnailLink = thumbnailLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mAuthor);
        dest.writeString(mRating);
        dest.writeString(mDescription);
        dest.writeString(mThumbnailLink);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

}

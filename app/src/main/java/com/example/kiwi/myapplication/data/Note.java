package com.example.kiwi.myapplication.data;

import android.support.annotation.Nullable;

import com.google.common.base.Objects;

import java.util.UUID;

/**
 * Created by kiwi on 6/2/16.
 */
public class Note {

    private String mId;
    @Nullable
    private String mTitle;
    @Nullable
    private String mDescription;
    @Nullable
    private String mImageUrl;


    public Note(String title) {
        mTitle = title;
    }

    public Note(@Nullable String title, @Nullable String description) {
        this(title, description, null);
    }

    public Note(@Nullable String title, @Nullable String description, @Nullable String imageUrl) {
        mId = UUID.randomUUID().toString();
        mTitle = title;
        mDescription = description;
        mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isEmpty() {
        return (mTitle == null || "".equals(mTitle)) &&
                (mDescription == null || "".equals(mDescription));
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Note note = (Note)o;
        return Objects.equal(mId, note.mId) &&
                Objects.equal(mTitle, note.mTitle) &&
                Objects.equal(mDescription, note.mDescription) &&
                Objects.equal(mImageUrl, note.mImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mTitle, mDescription, mImageUrl);
    }

    public String getId() {
        return mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}

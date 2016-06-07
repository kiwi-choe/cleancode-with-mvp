package com.example.kiwi.myapplication.notedetail;

/**
 * Created by kiwi on 6/7/16.
 */
public interface NoteDetailContract {

    interface View {

        void showMissingNote();

        void setProgressIndicator(boolean active);

        void hideTitle();

        void showTitle(String title);

        void hideDescription();

        void showDescription(String description);

        void showImage(String imageUrl);

        void hideImage();
    }

    interface UserActionsListener {

        void openNote(String noteId);
    }
}

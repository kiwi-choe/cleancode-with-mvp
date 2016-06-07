package com.example.kiwi.myapplication.addNote;

/**
 * Created by kiwi on 6/7/16.
 */
public interface AddNoteContract {

    interface View {

        void showNotesList();

        void showEmptyNoteError();
    }

    interface UserActionsListener {

        void saveNote(String title, String description);

    }
}

package com.example.kiwi.myapplication.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by kiwi on 6/2/16.
 */
public interface NotesRepository {

    interface LoadNotesCallback {

        void onNotesLoaded(List<Note> notes);
    }

    interface GetNoteCallback {

        void onNoteLoaded(Note note);
    }

    void getNotes(@NonNull LoadNotesCallback callback);

    void getNote(@NonNull String noteId, @NonNull GetNoteCallback callback);

    void saveNote(Note newNote);

    void refreshData();
}


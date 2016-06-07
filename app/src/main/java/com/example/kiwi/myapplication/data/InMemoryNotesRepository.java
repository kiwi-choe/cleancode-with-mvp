package com.example.kiwi.myapplication.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kiwi on 6/6/16.
 */
public class InMemoryNotesRepository implements NotesRepository {

    private final NotesServiceApi mNotesServiceApi;

    // This method has reduced visibility for testing and is only visible to tests in the same package.
    @VisibleForTesting
    List<Note> mCachedNotes;

    public InMemoryNotesRepository(NotesServiceApi notesServiceApi) {
        mNotesServiceApi = checkNotNull(notesServiceApi);
    }

    @Override
    public void refreshData() {
        mCachedNotes = null;
    }

    @Override
    public void saveNote(Note newNote) {
        checkNotNull(newNote);
        mNotesServiceApi.saveNote(newNote);
        refreshData();
    }

    @Override
    public void getNotes(@NonNull final LoadNotesCallback callback) {

        checkNotNull(callback);
        // Load from API only if needed.
        if(mCachedNotes == null) {
            mNotesServiceApi.getAllNotes(new NotesServiceApi.NotesServiceCallback<List<Note>>() {
                @Override
                public void onLoaded(List<Note> notes) {

                    mCachedNotes = ImmutableList.copyOf(notes);

                    callback.onNotesLoaded(mCachedNotes);
                }
            });
        } else {
            callback.onNotesLoaded(mCachedNotes);
        }
    }

    @Override
    public void getNote(@NonNull String noteId, @NonNull final GetNoteCallback callback) {
        checkNotNull(noteId);
        checkNotNull(callback);
        // Load notes matching the id always directly from the API
        mNotesServiceApi.getNote(noteId, new NotesServiceApi.NotesServiceCallback<Note>() {
            @Override
            public void onLoaded(Note note) {
                callback.onNoteLoaded(note);
            }
        });
    }
}
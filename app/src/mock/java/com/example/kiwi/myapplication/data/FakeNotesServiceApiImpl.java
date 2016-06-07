package com.example.kiwi.myapplication.data;

import android.support.v4.util.ArrayMap;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by kiwi on 6/6/16.
 *
 * Fake implementation of {@link NotesServiceApi} to inject a fake service in a hermetic test.
 */
public class FakeNotesServiceApiImpl implements NotesServiceApi {


    // TODO replace this with a new test specific data set.
    private static final ArrayMap<String, Note> NOTES_SERVICE_DATA = new ArrayMap<>();

    @Override
    public void getAllNotes(NotesServiceCallback<List<Note>> callback) {
        callback.onLoaded(Lists.newArrayList(NOTES_SERVICE_DATA.values()));
    }

    @Override
    public void getNote(String noteId, NotesServiceCallback<Note> callback) {
        Note note = NOTES_SERVICE_DATA.get(noteId);
        callback.onLoaded(note);

    }

    @Override
    public void saveNote(Note newNote) {
        NOTES_SERVICE_DATA.put(newNote.getId(), newNote);
    }
}

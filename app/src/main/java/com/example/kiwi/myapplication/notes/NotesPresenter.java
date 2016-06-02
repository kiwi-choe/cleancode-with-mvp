package com.example.kiwi.myapplication.notes;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.kiwi.myapplication.data.Note;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kiwi on 6/2/16.
 */
public class NotesPresenter implements NotesContract.UserActionsListener {

    private List<Note> mNoteList;

    private NotesContract.View mNotesView;

    public NotesPresenter(NotesContract.View notesView) {

        mNoteList = new ArrayList<>(0);
        mNotesView = notesView;//checkNotNull(notesView, "notesView cannot be null!");
        // get notes from Model(data)
        getNotes();

    }

    // temp
    private void getNotes() {
        Note note = new Note("test note 1");
        mNoteList.add(note);
        note = new Note("test note 2");
        mNoteList.add(note);

        // show notes
        if(mNotesView == null)
            Log.d("NotesPresenter", "mNotesView is null!!");
        else {
            mNotesView.showNotes(mNoteList);
        }

    }

    @Override
    public void loadNotes() {

        //mNotesView.showNotes(mNoteList);
    }
}

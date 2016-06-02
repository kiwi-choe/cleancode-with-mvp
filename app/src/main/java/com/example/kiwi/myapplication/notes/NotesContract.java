package com.example.kiwi.myapplication.notes;

import com.example.kiwi.myapplication.data.Note;

import java.util.List;

/**
 * Created by kiwi on 6/2/16.
 */
public interface NotesContract {

    interface View {

        void showNotes(List<Note> notes);

    }

    interface UserActionsListener {

        void loadNotes();
    }
}

package com.example.kiwi.myapplication.notes;

import android.support.annotation.NonNull;

import com.example.kiwi.myapplication.data.Note;

import java.util.List;

/**
 * Created by kiwi on 6/2/16.
 */
public interface NotesContract {

    interface View {

        void showNotes(List<Note> notes);

        void showAddNote();

        void setProgressIndicator(boolean forceUpdate);

        void showNoteDetailUi(String noteId);
    }

    interface UserActionsListener {

        void loadNotes(boolean forceUpdate);

        void addNewNote();

        void openNoteDetails(@NonNull Note note);
    }
}

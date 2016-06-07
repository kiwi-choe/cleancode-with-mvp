package com.example.kiwi.myapplication.notes;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.kiwi.myapplication.data.Note;
import com.example.kiwi.myapplication.data.NotesRepository;
import com.example.kiwi.myapplication.util.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kiwi on 6/2/16.
 */
public class NotesPresenter implements NotesContract.UserActionsListener {

    private List<Note> mNoteList;

    private final NotesRepository mNotesRepository;
    private NotesContract.View mNotesView;

    public NotesPresenter(
            @NonNull NotesRepository notesRepository, @NonNull NotesContract.View notesView) {

        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null");
        mNotesView = checkNotNull(notesView, "notesView cannot be null");
    }

    @Override
    public void loadNotes(boolean forceUpdate) {

        // Display a progress indicator
        mNotesView.setProgressIndicator(true);
        // Refresh the data from the repository if an update is forced (parameter is true)
        if(forceUpdate) {
            mNotesRepository.refreshData();
        }

        // The network request might be handled in a different thread so make sure
        // Espresso knows that the app is busy until the response is handled.
//        EspressoIdlingResource.increment(); // App is busy until further notice

        // Load the notes from the repository
        // When the notes have been loaded, hide the progress indicator and display them
        mNotesRepository.getNotes(new NotesRepository.LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
//                EspressoIdlingResource.decrement(); // Set app as idle.
                mNotesView.setProgressIndicator(false);
                mNotesView.showNotes(notes);
            }
        });
    }

    @Override
    public void addNewNote() {
        mNotesView.showAddNote();
    }

    @Override
    public void openNoteDetails(@NonNull Note requesteNote) {
        checkNotNull(requesteNote, "requestedNote cannot be null");
        mNotesView.showNoteDetailUi(requesteNote.getId());
    }
}

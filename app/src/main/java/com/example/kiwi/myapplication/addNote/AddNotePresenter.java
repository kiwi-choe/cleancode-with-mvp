package com.example.kiwi.myapplication.addNote;

import android.support.annotation.NonNull;

import com.example.kiwi.myapplication.data.Note;
import com.example.kiwi.myapplication.data.NotesRepository;
import com.example.kiwi.myapplication.util.ImageFile;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kiwi on 6/7/16.
 */
public class AddNotePresenter implements AddNoteContract.UserActionsListener {

    @NonNull
    private final NotesRepository mNotesRepository;
    @NonNull
    private final AddNoteContract.View mAddNoteView;
    @NonNull
    private final ImageFile mImageFile;


    public AddNotePresenter(
            @NonNull NotesRepository notesRepository,
            @NonNull AddNoteContract.View addNoteView,
            @NonNull ImageFile imageFile) {

        mNotesRepository = checkNotNull(notesRepository);
        mAddNoteView = checkNotNull(addNoteView);
        mImageFile = imageFile;
    }

    @Override
    public void saveNote(String title, String description) {

        String imageUrl = null;

        Note newNote = new Note(title, description, imageUrl);
        if(newNote.isEmpty())
            mAddNoteView.showEmptyNoteError();
        else {
            mNotesRepository.saveNote(newNote);
            mAddNoteView.showNotesList();
        }

    }
}

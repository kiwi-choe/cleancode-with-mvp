package com.example.kiwi.myapplication.notedetail;

import android.support.annotation.NonNull;

import com.example.kiwi.myapplication.data.Note;
import com.example.kiwi.myapplication.data.NotesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by kiwi on 6/7/16.
 */
public class NoteDetailPresenter implements NoteDetailContract.UserActionsListener {

    private final NotesRepository mNotesRepository;
    private final NoteDetailContract.View mNotesDetailView;

    public NoteDetailPresenter(@NonNull NotesRepository notesRepository,
                               @NonNull NoteDetailContract.View noteDetailView) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null");
        mNotesDetailView = checkNotNull(noteDetailView, "noteDetailView cannot be null");
    }

    @Override
    public void openNote(String noteId) {
        if(null == noteId || noteId.isEmpty()) {
            mNotesDetailView.showMissingNote();
            return;
        }

        mNotesDetailView.setProgressIndicator(true);
        mNotesRepository.getNote(noteId, new NotesRepository.GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {
                mNotesDetailView.setProgressIndicator(false);
                if(null == note)
                    mNotesDetailView.showMissingNote();
                else
                    showNote(note);
            }
        });
    }

    private void showNote(Note note) {
        String title = note.getTitle();
        String description = note.getDescription();
        String imageUrl = note.getImageUrl();

        if(title != null && title.isEmpty())
            mNotesDetailView.hideTitle();
        else
            mNotesDetailView.showTitle(title);

        if (description != null && description.isEmpty()) {
            mNotesDetailView.hideDescription();
        } else {
            mNotesDetailView.showDescription(description);
        }

        if (imageUrl != null) {
            mNotesDetailView.showImage(imageUrl);
        } else {
            mNotesDetailView.hideImage();
        }

    }
}

package com.example.kiwi.myapplication.notes;

import com.example.kiwi.myapplication.data.Note;
import com.example.kiwi.myapplication.data.NotesRepository;
import com.example.kiwi.myapplication.data.NotesRepository.LoadNotesCallback;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by kiwi on 6/6/16.
 */
public class NotesPresenterTest {

    private static List<Note> NOTES = Lists.newArrayList(new Note("Title1", "Description1"),
            new Note("Title2", "Description2"));
    private NotesPresenter mNotesPresenter;

    @Mock
    private NotesRepository mNotesRepository;

    @Mock
    private NotesContract.View mNotesView;

    @Captor
    private ArgumentCaptor<LoadNotesCallback> mLoadNotesCallbackCaptor;

    @Before
    public void setupNotesPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation.
        // To inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mNotesPresenter = new NotesPresenter(mNotesRepository, mNotesView);
    }

    @Test
    public void clickOnFabShowsAddNoteUi() {
        // When adding a new note
        mNotesPresenter.addNewNote();

        // Then add note UI is shown
        verify(mNotesView).showAddNote();
    }

    @Test
    public void loadNotesFromRepositoryAndLoadIntoView() {

        // Give an initialized NotesPresenter with empty notes
        // When loading of Notes is requested
        mNotesPresenter.loadNotes(true);

        // Callback is captured and invoked with stubbed notes
        verify(mNotesRepository).getNotes(mLoadNotesCallbackCaptor.capture());
        mLoadNotesCallbackCaptor.getValue().onNotesLoaded(NOTES);

        // Then progress indicator is hidden and notes are shown in UI
        verify(mNotesView).setProgressIndicator(false);
        verify(mNotesView).showNotes(NOTES);


    }
}

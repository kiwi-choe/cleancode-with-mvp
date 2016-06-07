package com.example.kiwi.myapplication.addNote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiwi.myapplication.Injection;
import com.example.kiwi.myapplication.R;

/**
 * Created by kiwi on 6/7/16.
 *
 * Main UI for the add note screen. Users can enter a note title and description.
 * Images can be added to notes by clicking on the options menu.
 */
public class AddNoteFragment extends Fragment implements AddNoteContract.View {

    private TextView mTitle;
    private TextView mDescription;
    private ImageView mImageThumbnail;

    private AddNoteContract.UserActionsListener mActionListener;

    public static AddNoteFragment newInstance() { return new AddNoteFragment(); }

    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_addnote, container, false);
        mTitle = (TextView)root.findViewById(R.id.add_note_title);
        mDescription = (TextView)root.findViewById(R.id.add_note_description);
        mImageThumbnail = (ImageView)root.findViewById(R.id.add_note_image_thumbnail);


//        setRetainInstance(true);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        mActionListener = new AddNotePresenter(Injection.provideNotesRepository(), this,
                Injection.provideImageFile());

        FloatingActionButton fab =
                (FloatingActionButton)getActivity().findViewById(R.id.fab_add_notes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionListener.saveNote(mTitle.getText().toString(),
                        mDescription.getText().toString());
            }
        });
    }

    @Override
    public void showNotesList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showEmptyNoteError() {
        Snackbar.make(mTitle, getString(R.string.empty_note_msg), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If an image is received, display it on the ImageView.

    }
}

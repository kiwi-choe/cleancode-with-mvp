package com.example.kiwi.myapplication.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kiwi.myapplication.Injection;
import com.example.kiwi.myapplication.R;
import com.example.kiwi.myapplication.addNote.AddNoteActivity;
import com.example.kiwi.myapplication.data.Note;
import com.example.kiwi.myapplication.notedetail.NoteDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class NotesFragment extends Fragment implements NotesContract.View {

    private static final int REQUEST_ADD_NOTE = 111;

    private NotesContract.UserActionsListener mActionsListener;

    private NotesAdapter mListAdapter;

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance() {return new NotesFragment();}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListAdapter = new NotesAdapter(new ArrayList<Note>(0), mItemListener);
        mActionsListener = new NotesPresenter(Injection.provideNotesRepository(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadNotes(false);
    }

    /*
        * Listener for clicks on notes in the RecyclerView
        * */
    NoteItemListener mItemListener = new NoteItemListener() {
        @Override
        public void onNoteClick(Note clickedNote) {
            mActionsListener.openNoteDetails(clickedNote);
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If a note was successfully added, show snackbar.
        if(REQUEST_ADD_NOTE == requestCode && Activity.RESULT_OK == resultCode) {
            Snackbar.make(getView(), R.string.notesaved, Snackbar.LENGTH_SHORT).show();
            // load notes
            //mActionsListener.loadNotes(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_notes, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.notes_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = getContext().getResources().getInteger(R.integer.num_notes_columns);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_notes);
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionsListener.addNewNote();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        setRetainInstance(true);
    }

    @Override
    public void showNotes(List<Note> notes) {
        mListAdapter.replaceData(notes);
    }

    @Override
    public void showAddNote() {
        Intent intent = new Intent(getContext(), AddNoteActivity.class);
        startActivityForResult(intent, REQUEST_ADD_NOTE);
    }

    @Override
    public void setProgressIndicator(boolean forceUpdate) {

    }

    @Override
    public void showNoteDetailUi(String noteId) {

        // in it's own Activity, since it makes more sence that way and it gives us the flexibility
        // to show some Intent stubbing.
        Intent intent = new Intent(getContext(), NoteDetailActivity.class);
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, noteId);
        startActivity(intent);
    }

    private static class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

        private List<Note> mNotes;
        private NoteItemListener mItemListener;

        public NotesAdapter(ArrayList<Note> notes, NoteItemListener itemListener) {
            setList(notes);
            mItemListener = itemListener;
        }

        private void setList(List<Note> notes) {
            mNotes = checkNotNull(notes);   // if null, invoke NullPointerException
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.item_note, parent, false);

            return new ViewHolder(noteView, mItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Note note = mNotes.get(position);

            holder.title.setText(note.getTitle());
            holder.description.setText(note.getDescription());
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        public Note getItem(int position) {return mNotes.get(position);}

        public void replaceData(List<Note> notes) {
            setList(notes);
            notifyDataSetChanged();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView title;
            public TextView description;

            public ViewHolder(View itemView, NoteItemListener listener) {
                super(itemView);

                mItemListener = listener;
                title = (TextView) itemView.findViewById(R.id.note_detail_title);
                description = (TextView) itemView.findViewById(R.id.note_detail_description);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Note note = getItem(position);
                mItemListener.onNoteClick(note);
            }
        }
    }



    public interface NoteItemListener {

        void onNoteClick(Note clickedNote);
    }

}

package com.example.kiwi.myapplication.notes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kiwi.myapplication.R;
import com.example.kiwi.myapplication.data.Note;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class NotesFragment extends Fragment implements NotesContract.View {

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
        mActionsListener = new NotesPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // load notes
    }

    /*
        * Listener for clicks on notes in the RecyclerView
        * */
    NoteItemListener mItemListener = new NoteItemListener() {
        @Override
        public void onNoteClick(Note clickedNote) {
            // do action when click an item(note)
        }
    };

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

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showNotes(List<Note> notes) {
        mListAdapter.replaceData(notes);
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

        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        public void replaceData(List<Note> notes) {
            setList(notes);
            notifyDataSetChanged();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView title;

            public ViewHolder(View itemView, NoteItemListener listener) {
                super(itemView);

                mItemListener = listener;
                title = (TextView) itemView.findViewById(R.id.note_detail_title);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                //
            }
        }
    }



    public interface NoteItemListener {

        void onNoteClick(Note clickedNote);
    }

}

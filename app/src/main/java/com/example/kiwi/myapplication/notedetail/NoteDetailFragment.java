package com.example.kiwi.myapplication.notedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.kiwi.myapplication.Injection;
import com.example.kiwi.myapplication.R;

public class NoteDetailFragment extends Fragment implements NoteDetailContract.View{

    private static final String ARG_NOTE_ID = "NOTE_ID";

    private TextView mDetailTitle;
    private TextView mDetailDescription;
    private ImageView mDetailImage;
    private NoteDetailContract.UserActionsListener mActionsListener;

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    public static NoteDetailFragment newInstance(String noteId) {
        Bundle args = new Bundle();
        args.putString(ARG_NOTE_ID, noteId);
        NoteDetailFragment fragment = new NoteDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note_detail, container, false);

        mDetailTitle = (TextView) root.findViewById(R.id.note_detail_title);
        mDetailDescription = (TextView) root.findViewById(R.id.note_detail_description);
        mDetailImage = (ImageView) root.findViewById(R.id.note_detail_image);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionsListener = new NoteDetailPresenter(Injection.provideNotesRepository(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String noteId = getArguments().getString(ARG_NOTE_ID);
        mActionsListener.openNote(noteId);
    }

    @Override
    public void showMissingNote() {
        mDetailTitle.setText("");
        mDetailDescription.setText("No data");
    }

    @Override
    public void setProgressIndicator(boolean active) {
        if(active) {
            mDetailTitle.setText("");
            mDetailDescription.setText(getString(R.string.loading));
        }
    }

    @Override
    public void hideTitle() {
        mDetailTitle.setVisibility(View.GONE);
    }

    @Override
    public void showTitle(String title) {
        mDetailTitle.setVisibility(View.VISIBLE);
        mDetailTitle.setText(title);
    }

    @Override
    public void hideDescription() {
        mDetailDescription.setVisibility(View.GONE);
    }

    @Override
    public void showDescription(String description) {
        mDetailDescription.setVisibility(View.VISIBLE);
        mDetailDescription.setText(description);
    }

    @Override
    public void hideImage() {
        mDetailImage.setVisibility(View.GONE);
    }

    @Override
    public void showImage(String imageUrl) {
        mDetailImage.setVisibility(View.VISIBLE);

        // This app uees Glide for image loading
        Glide.with(this)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(mDetailImage) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);

                    }
                });
    }

}

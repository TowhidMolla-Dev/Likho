package com.towhid.myjournal;

import androidx.cardview.widget.CardView;

import com.towhid.myjournal.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}

package com.towhid.myjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.towhid.myjournal.Models.Notes;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    EditText edt_title, edt_notes;
    ImageView imgview_save;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        edt_title = findViewById(R.id.edt_title);
        edt_notes = findViewById(R.id.edt_notes);
        imgview_save = findViewById(R.id.imgview_save);

        notes = new Notes();
        try {
            notes =(Notes) getIntent().getSerializableExtra("old_note");
            edt_title.setText(notes.getTitle());
            edt_notes.setText(notes.getNotes());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        imgview_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_title.getText().toString();
                String description = edt_notes.getText().toString();

                if (description.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please add some notes", Toast.LENGTH_SHORT).show();
                    return;
                }

                LocalDateTime date = LocalDateTime.now();

                if (!isOldNote){
                    notes = new Notes();
                }

                notes.setTitle(title);
                notes.setDate(date.toString());
                notes.setNotes(description);

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
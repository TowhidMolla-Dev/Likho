package com.towhid.myjournal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.towhid.myjournal.Models.Notes;
import com.towhid.myjournal.NotesClickListener;
import com.towhid.myjournal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.txt_title.setText(list.get(position).getTitle());
        holder.txt_title.setSelected(true);

        holder.txtview_notes.setText(list.get(position).getNotes());

        holder.txtview_date.setText(list.get(position).getDate());
        holder.txtview_date.setSelected(true);

        if (list.get(position).isPinned()) {
            holder.imgview_pin.setImageResource(R.drawable.pin);
        } else {
            holder.imgview_pin.setImageResource(0);
        }

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));

            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);

                return true;
            }
        });
    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(List<Notes> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView txt_title, txtview_notes, txtview_date;
    ImageView imgview_pin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        notes_container = itemView.findViewById(R.id.notes_container);
        notes_container = itemView.findViewById(R.id.txt_title);
        notes_container = itemView.findViewById(R.id.txtview_notes);
        notes_container = itemView.findViewById(R.id.txtview_date);
        notes_container = itemView.findViewById(R.id.imgview_pin);
    }
}

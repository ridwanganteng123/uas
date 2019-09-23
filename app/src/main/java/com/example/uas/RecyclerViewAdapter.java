package com.example.uas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {
    Context context;
    OnUserClickListener listener;

    List<PersonBean> listPersonInfo;

    public RecyclerViewAdapter (Context context, List<PersonBean>
            listPersonInfo, OnUserClickListener listener) {
        this.context = context;
        this.listPersonInfo = listPersonInfo;
        this.listener = listener;
    }

    public interface OnUserClickListener {

        void onUserClick(PersonBean currentPerson, String action);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.notes, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {

        final PersonBean currentPerson = listPersonInfo.get(position);
        holder.ctxtJudul.setText(currentPerson.getJudul());
        holder.ctxtDeskripsi.setText(currentPerson.getDeskripsi());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson, "Edit");
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(currentPerson, "Delete");
                DatabaseHelper db = new DatabaseHelper(context);

                db.delete(currentPerson.getJudul());

            }
        });

    }

    @Override
    public int getItemCount() {

        return listPersonInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView ctxtDeskripsi, ctxtJudul;
        ImageView imgDelete, imgEdit;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ctxtDeskripsi = itemView.findViewById(R.id.ctxtDeskripsi);
            ctxtJudul = itemView.findViewById(R.id.ctxtJudul);
            imgDelete = itemView.findViewById(R.id.imgdelete);
            imgEdit = itemView.findViewById(R.id.imgedit);

        }
    }
}



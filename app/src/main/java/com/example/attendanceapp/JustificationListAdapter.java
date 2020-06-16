package com.example.attendanceapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class JustificationListAdapter extends RecyclerView.Adapter {

    private List<Justification> justifications;

    public JustificationListAdapter(List<Justification> justifications) {
        this.justifications = justifications;
    }

    @NonNull
    @Override
    public JustificationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.justification_item,
                parent,
                false
        );
        JustificationListViewHolder viewHolder = new JustificationListViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JustificationListViewHolder viewHolder =
                (JustificationListViewHolder) holder;
        Justification justification = justifications.get(position);

        viewHolder.filename.setText(justification.getFilename());
        viewHolder.createdAt.setText(justification.getCreated().toString());
    }

    @Override
    public int getItemCount() {
        return justifications.size();
    }

    private static class JustificationListViewHolder extends RecyclerView.ViewHolder {

        private TextView filename;
        private TextView createdAt;

        public JustificationListViewHolder(@NonNull View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.justification_filename);
            createdAt = itemView.findViewById(R.id.justification_date);
        }
    }
}

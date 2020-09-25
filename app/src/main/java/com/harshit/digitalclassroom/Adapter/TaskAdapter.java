package com.harshit.digitalclassroom.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.harshit.digitalclassroom.R;
import com.harshit.digitalclassroom.utils.TaskList;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    ArrayList<TaskList> taskLists;
    Context context;
    Activity activity;




    public TaskAdapter(Context context, Activity activity, ArrayList<TaskList> itemlist) {
        taskLists = itemlist;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_item,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.title.setText(taskLists.get(position).getTitle());
        holder.status.setText(taskLists.get(position).getStatus());
        holder.creatinDate.setText(taskLists.get(position).getCreationDate());


    }

    @Override
    public int getItemCount() {
        return taskLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView creatinDate;
        TextView status;
        public ViewHolder(@NonNull View itemView) {
           super(itemView);
           title = itemView.findViewById(R.id.task_title);
           status = itemView.findViewById(R.id.status);
           creatinDate = itemView.findViewById(R.id.creation_date);

        }
    }

}


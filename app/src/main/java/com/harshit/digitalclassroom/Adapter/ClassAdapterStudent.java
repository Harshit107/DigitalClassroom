package com.harshit.digitalclassroom.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harshit.digitalclassroom.R;
import com.harshit.digitalclassroom.model.ClassList;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class ClassAdapterStudent extends RecyclerView.Adapter<ClassAdapterStudent.ViewHolder> {

    Activity activity;
    Context context;
    ArrayList<ClassList> classLists;


    public ClassAdapterStudent(Activity activity, Context context, ArrayList<ClassList> classLists) {
        this.activity = activity;
        this.context = context;
        this.classLists = classLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.class_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        final int[] images = {R.drawable.classview1,
                R.drawable.classview2,R.drawable.classview3,
                R.drawable.classview4,R.drawable.classview5,R.drawable.classview6,
                R.drawable.classview7,R.drawable.classview8,R.drawable.classview9,R.drawable.classview10};
        final Random rand = new Random();
        holder.backGround.setBackgroundResource(images[rand.nextInt(images.length)]);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context,holder.item);
                popupMenu.inflate(R.menu.activity_main_drawer);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {


                        return false;
                    }
                });
                popupMenu.show();


            }
        });



    }

    @Override
    public int getItemCount() {
        return classLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView className ;
        TextView item ;
        TextView classDesc ;
        TextView teacherName ;
        CircleImageView teacherPic;
        RelativeLayout backGround;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        classDesc = itemView.findViewById(R.id.class_description);
        className = itemView.findViewById(R.id.class_name);
        teacherPic = itemView.findViewById(R.id.profile_pic);
        item = itemView.findViewById(R.id.textViewOptions);
        teacherName = itemView.findViewById(R.id.teacher_name);
        backGround = itemView.findViewById(R.id.class_main_relative_layout);

        }
    }
}

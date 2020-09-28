package com.harshit.digitalclassroom.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.harshit.digitalclassroom.R;

import de.hdodenhof.circleimageview.CircleImageView;

class ProfileFragment extends Fragment {

    Activity activity;
    Context context;


    public ProfileFragment(Activity activity, Context context){
        this.activity = activity;
        this.context = context;
    }

    TextView  creation_date;
    LinearLayout profile_main,password_main;
    EditText name, email, phone,newPassword,confPassword;
    CircleImageView profile_pic;
    Button update,change;
    View progress;
    TextView progressText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(activity == null){
            return;
        }
        initilize();



    }

    private void initilize() {
        progress = activity.findViewById(R.id.progress_bar);
        progressText = progress.findViewById(R.id.progressText);
        progress.setVisibility(View.GONE);

        name = activity.findViewById(R.id.name);
        update=activity.findViewById(R.id.update);
        change=activity.findViewById(R.id.change_password);
        email =activity. findViewById(R.id.email);
        phone = activity.findViewById(R.id.phone);
        profile_pic = activity.findViewById(R.id.profile_pic);

        newPassword = activity.findViewById(R.id.newPass);
        confPassword = activity.findViewById(R.id.confPass);

        profile_main = activity.findViewById(R.id.profile_layout);
        password_main = activity.findViewById(R.id.password_layout);

        creation_date = activity.findViewById(R.id.account);
    }
}

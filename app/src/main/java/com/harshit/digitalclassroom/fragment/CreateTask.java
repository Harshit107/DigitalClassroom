package com.harshit.digitalclassroom.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.harshit.digitalclassroom.Config.Config;
import com.harshit.digitalclassroom.R;
import com.harshit.digitalclassroom.utils.SharedPreferenceValue;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class CreateTask extends Fragment {

    Context context;
    Activity activity;
    EditText title;
    EditText description;
    public CreateTask(Context applicationContext, Activity mainActivity) {
        this.context = applicationContext;
        this.activity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title = activity.findViewById(R.id.title);
        description = activity.findViewById(R.id.description);
        Button btnCreate = activity.findViewById(R.id.btnCreateTask);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String strTitle = title.getText().toString();
                String strDesc = description.getText().toString();

                if(vaidateInput(strTitle,strDesc)) {
                    createTask(strTitle,strDesc);
                }
            }
        });


    }

    public void createTask(String title, String description){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("description", description);
        JsonObjectRequest request_json = new JsonObjectRequest(Config.CREATE_TASK, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Process os success response

                        Toasty.success(context,"Task Created successfully",Toasty.LENGTH_LONG,true).show();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, new CreateTask(context,activity))
                                .addToBackStack(null)
                                .commit();;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.toString());
                        Log.d("loginErrorVolly",error.toString());
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String>  header = new HashMap<String, String>();
                header.put("Content-Type", "application/json");
                header.put("Authorization", "Bearer "+ SharedPreferenceValue.getToken(context));

                return header;
            }
        };
        requestQueue.add(request_json);

    }


    public boolean vaidateInput(String inpTitle, String inpDescription) {

        if(inpTitle.trim().isEmpty()){
            title.setError("Enter Valid input");
            title.requestFocus();
            return false;
        }
        if(inpDescription.trim().isEmpty()){
            description.setError("Enter Valid input");
            description.requestFocus();
            return false;
        }

        return true;


    }

}
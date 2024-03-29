package com.harshit.digitalclassroom.fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.harshit.digitalclassroom.Adapter.ClassAdapterStudent;
import com.harshit.digitalclassroom.R;
import com.harshit.digitalclassroom.model.ClassList;
import java.util.ArrayList;
public class Homepage extends Fragment {

    Activity activity;
    Context context;
    ClassAdapterStudent classAdapterStudent;
    ArrayList<ClassList> classLists;
    RecyclerView homeRecyclear;
    private ShimmerFrameLayout mShimmerViewContainer;
    private SwipeRefreshLayout mSwipeRefreshLayout;



    public Homepage(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }
    public Homepage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(activity == null){
           return;
        }
        initilize();
        mShimmerViewContainer.startShimmerAnimation();
        homeRecyclear.setLayoutManager(new LinearLayoutManager(context));
        homeRecyclear.setHasFixedSize(true);
        classLists =  new ArrayList<>();
        loadData();
        stopRefreshing();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                stopRefreshing();
            }
        });



    }

    private void initilize() {
        homeRecyclear = activity.findViewById(R.id.home_task_recycle);
        mSwipeRefreshLayout = activity.findViewById(R.id.swipeRefresh);
        mShimmerViewContainer = activity.findViewById(R.id.shimmer_view_container);
    }

    public void loadData() {
        classLists.clear();

        for(int i =0; i<20; i++) {
            classLists.add(new ClassList(String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i)));
            classAdapterStudent = new ClassAdapterStudent(activity,context,classLists);
            setAdapter();
        }




        /*RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Config.READ_ALL_TASK, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", String.valueOf(response.length()));
                for (int i =0; i<response.length();i++) {

                    try {
                        JSONObject task = response.getJSONObject(i);
                        Log.d("response",task.toString());
                        String status = task.getString("completed");
                        String title = task.getString("title");
                        String description = task.getString("description");
                        String created = task.getString("createdAt");
                        String updateed = task.getString("updatedAt");
                        String id = task.getString("_id");


                        taskLists.add(new TaskList(title,created,status));
                        taskAdapter = new TaskAdapter(context,activity,taskLists);

                    } catch (JSONException e) {
                        Log.d("Task",e.toString());
                    }
                    setAdapter();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Homepage",error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer "+ SharedPreferenceValue.getToken(context));

                return params;
            }
        };

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonArrayRequest);
        */



    }
    public void loadMoreData() {

    }
    public void setAdapter(){
        homeRecyclear.setAdapter(classAdapterStudent);
        classAdapterStudent.notifyDataSetChanged();
    }

    public void stopRefreshing(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mShimmerViewContainer.setVisibility(View.GONE);
                mShimmerViewContainer.stopShimmerAnimation();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        },2000);
    }


}

package com.harshit.digitalclassroom;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.harshit.digitalclassroom.Config.Config;
import com.harshit.digitalclassroom.utils.IsNetwork;
import com.harshit.digitalclassroom.utils.SharedPreferenceValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class RegistrationActivity extends AppCompatActivity {

    private ImageView logo, joinus;
    private AutoCompleteTextView username, email, password,cnf_password;
    private Button signup;
    private TextView signin;
    RelativeLayout progressbar;
    TextView progressText;
    TextView progressShortDesc;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initializeGUI();
        if(!IsNetwork.isOnline(getApplicationContext()))
            snakeBar("Connection not available",findViewById(android.R.id.content).getRootView());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String inputName = username.getText().toString().trim();
                final String inputPw = password.getText().toString().trim();
                final String inputEmail = email.getText().toString().trim();
                final String cnfPassword = cnf_password.getText().toString().trim();
                if(!IsNetwork.isOnline(getApplicationContext())) {
                    snakeBar("Connection not available", view);
                    return;
                }
                if(validateInput(inputName, inputPw, inputEmail,cnfPassword))
                         registerUser(inputName, inputPw, inputEmail);

            }
        });

//        used for testing
//        registerUser("harshit keshari","12345678","harshitkeshari765@gmail.com");


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!IsNetwork.isOnline(getApplicationContext())) {
                    snakeBar("Connection not available", view);
                    return;
                }
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });
    }

    private void initializeGUI(){

        logo = findViewById(R.id.ivRegLogo);
        joinus = findViewById(R.id.ivJoinUs);
        username = findViewById(R.id.atvUsernameReg);
        email =  findViewById(R.id.atvEmailReg);
        password = findViewById(R.id.atvPasswordReg);
        cnf_password = findViewById(R.id.cnfPassword);
        signin = findViewById(R.id.tvSignIn);
        signup = findViewById(R.id.btnSignUp);
        progressbar = findViewById(R.id.progress);
        progressText = progressbar.findViewById(R.id.progressText);
        progressShortDesc = progressbar.findViewById(R.id.shortdesc);


    }

    private void registerUser(final String name, final String password, String email) {

        requestVerification(email,password,name);

    }
    private void requestVerification(final String email, final String password, final String name) {

        progressbar.setVisibility(View.VISIBLE);
        progressText.setText("Please Wait");
        progressShortDesc.setVisibility(View.VISIBLE);
        progressShortDesc.setText("Verifying User Detail");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        JsonObjectRequest request_json = new JsonObjectRequest(Config.CREATE_USER, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Process os success response
                        try {
                            String token = response.getString("token").toString();
                            SharedPreferenceValue.updatToken(getApplicationContext(),token);
                            progressbar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressbar.setVisibility(View.GONE);
                NetworkResponse networkResponse = error.networkResponse;

            if (networkResponse != null) {
                Log.d("Error Status",String.valueOf(networkResponse.statusCode));
                parseVolleyError(error);
            }
            else
                Toasty.error(getApplicationContext()," Internal server error ",Toasty.LENGTH_LONG,true).show();

            }
        });


        requestQueue.add(request_json);

    }

//    validate input
    private boolean validateInput(String inName, String inPw, String inEmail,String cnfPassword){

        if(inName.isEmpty()){
            username.setError("Name is Required.");
            return false;
        }
        if(inPw.isEmpty()){
            password.setError("Password is empty.");
            return false;
        }
        if(inEmail.isEmpty()){
            email.setError("Email is empty.");
            return false;
        }
        if(!cnfPassword.equals(inPw)){
            password.setError("Password must be match");
            cnf_password.setError("Password must be match");
            return false;

        }
        return true;
    }

//    snakebar
    public void snakeBar(String msg, View view) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

//    vollyerror
    public void parseVolleyError(VolleyError error) {
        try {
            Log.d("Errorresponsecode",String.valueOf(error.networkResponse.statusCode));
            progressbar.setVisibility(View.GONE);
            String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
            JSONObject data = new JSONObject(responseBody);
            Log.d("response",data.toString());
            snakeBar("User is already Registered with this email",getCurrentFocus());
//            JSONArray errors = data.getJSONArray("errors");
//            JSONObject jsonMessage = errors.getJSONObject(0);
//            String message = jsonMessage.getString("message");
//            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } catch (JSONException ignored) {
            Log.d("response",ignored.toString());
            snakeBar("Something went wrong",getCurrentFocus());
        }
    }

}


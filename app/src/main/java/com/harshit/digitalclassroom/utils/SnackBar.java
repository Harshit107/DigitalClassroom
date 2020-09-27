package com.harshit.digitalclassroom.utils;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.harshit.digitalclassroom.R;

public class SnackBar {

       public static void show(String msg, View view,Context context) {
           Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
           View snackBarView = snackbar.getView();
           snackBarView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
           snackbar.show();

   }

}

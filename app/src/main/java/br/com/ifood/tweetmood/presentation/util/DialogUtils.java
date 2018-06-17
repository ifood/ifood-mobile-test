package br.com.ifood.tweetmood.presentation.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogUtils {

    public void showDialog(Activity activity, int msg, int positiveButton, DialogInterface.OnClickListener positiveClick){
        showDialog(activity, activity.getString(msg), positiveButton, positiveClick);
    }

    private void showDialog(Activity activity, String msg, int positiveButton, DialogInterface.OnClickListener positiveClick) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(msg);

        builder.setPositiveButton(positiveButton, positiveClick);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);


        if (!activity.isFinishing()) {
            alertDialog.show();
        }
    }

    public ProgressDialog showProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.show();

        return progressDialog;
    }
}
package com.example.shikimoriclient.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class CustomDialogFragment extends DialogFragment {

    View view;

    public CustomDialogFragment() {
    }

    @SuppressLint("ValidFragment")
    public CustomDialogFragment(View view) {
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setView(view).create();
    }
}

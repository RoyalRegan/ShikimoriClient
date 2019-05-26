package com.example.shikimoriclient.FrontEnd.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.shikimoriclient.R;

public class Authorization extends Fragment
{
    public Authorization()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v =inflater.inflate(R.layout.dialog_authorization, container, false);
        WebView webView = v.findViewById(R.id.webv);
        return v;
    }
}

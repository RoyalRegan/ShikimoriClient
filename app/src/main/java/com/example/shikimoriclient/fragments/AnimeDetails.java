package com.example.shikimoriclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shikimoriclient.R;

public class AnimeDetails extends Fragment
{
    TextView textView0;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    TextView textView10;
    TextView textView11;
    TextView textView12;
    TextView textView13;
    TextView textView14;
    TextView textView15;
    TextView textView16;
    ImageView imageView1;
    ImageView imageView2;
    View view1;
    View view2;

    public AnimeDetails()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_anime_details, null);

        textView0 = (TextView)view.findViewById(R.id.textView0);
        textView1 = (TextView)view.findViewById(R.id.textView1);
        textView2 = (TextView)view.findViewById(R.id.textView2);
        textView3 = (TextView)view.findViewById(R.id.textView3);
        textView4 = (TextView)view.findViewById(R.id.textView4);
        textView5 = (TextView)view.findViewById(R.id.textView5);
        textView6 = (TextView)view.findViewById(R.id.textView6);
        textView7 = (TextView)view.findViewById(R.id.textView7);
        textView8 = (TextView)view.findViewById(R.id.textView8);
        textView9 = (TextView)view.findViewById(R.id.textView9);
        textView10 = (TextView)view.findViewById(R.id.textView10);
        textView11 = (TextView)view.findViewById(R.id.textView11);
        textView12 = (TextView)view.findViewById(R.id.textView12);
        textView13 = (TextView)view.findViewById(R.id.textView13);
        textView14 = (TextView)view.findViewById(R.id.textView14);
        textView15 = (TextView)view.findViewById(R.id.textView15);
        textView16 = (TextView)view.findViewById(R.id.textView16);
        imageView1 = (ImageView)view.findViewById(R.id.imageView1);
        imageView2 = (ImageView)view.findViewById(R.id.imageView2);
        view1 = (View)view.findViewById(R.id.view1);
        view2 = (View)view.findViewById(R.id.view2);

        return view;
    }

}

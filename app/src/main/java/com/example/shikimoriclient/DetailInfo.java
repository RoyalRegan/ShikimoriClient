package com.example.shikimoriclient;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v4.app.FragmentManager;

import com.example.shikimoriclient.fragments.AnimeDetails;
import com.example.shikimoriclient.fragments.RanobeOrMangaDetails;

public class DetailInfo extends AppCompatActivity
{
    FrameLayout container;
    FragmentManager myFragmentManager;
    AnimeDetails animeDetails;
    RanobeOrMangaDetails ranobeOrMangaDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        animeDetails = new AnimeDetails();
        ranobeOrMangaDetails = new RanobeOrMangaDetails();

        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = myFragmentManager
                    .beginTransaction();
            fragmentTransaction.add(R.id.container, animeDetails);
            fragmentTransaction.commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //////////////условие
        ////или
        getMenuInflater().inflate(R.menu.menu_anime_details, menu);
        ////или
        getMenuInflater().inflate(R.menu.menu_ranobe_and_manga_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //////условие
        ////или
        switch(item.getItemId())
        {
            case R.id.watch_list:
                //smth
                return true;
            case R.id.throw_out:
                //smth
                return true;
            case R.id.go_in_stack:
                //smth
                return true;
            case R.id.plans:
                //smth
                return true;
            case R.id.rewatch:
                //smth
                return true;
            case R.id.watch:
                //smth
                return true;
        }
        ///////или
        switch(item.getItemId())
        {
            case R.id.read_list:
                //smth
                return true;
            case R.id.throw_out:
                //smth
                return true;
            case R.id.go_in_stack:
                //smth
                return true;
            case R.id.plans:
                //smth
                return true;
            case R.id.reread:
                //smth
                return true;
            case R.id.read:
                //smth
                return true;
        }
        return  super.onOptionsItemSelected(item);
    }
}

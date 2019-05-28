package com.example.shikimoriclient;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.PopupMenu;

import com.example.shikimoriclient.BackEnd.dao.ItemSimple;
import com.example.shikimoriclient.BackEnd.dao.anime.Anime;
import com.example.shikimoriclient.BackEnd.dao.manga.Manga;
import com.example.shikimoriclient.FrontEnd.fragments.AnimeDetails;
import com.example.shikimoriclient.FrontEnd.fragments.RanobeOrMangaDetails;

//TODO: Add similar(as context dialog like RecyclerViewFragment) and related(?)
public class DetailInfo extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton floatingActionButton;
    private FragmentManager myFragmentManager;
    private FragmentTransaction fragmentTransaction;
    private boolean animeType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        initializeComp();
    }

    private void initializeComp() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton = findViewById(R.id.menuButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        myFragmentManager = getSupportFragmentManager();
        fragmentTransaction = myFragmentManager
                .beginTransaction();
        setCompConfiguration();
        fillComp();
        setListeners();
        fragmentTransaction.commit();
    }

    private void setLayout() {

    }

    private void fillComp() {
        ItemSimple item = (ItemSimple) getIntent().getSerializableExtra("Item");
        if (item instanceof Anime) {
            animeType = true;
            AnimeDetails animeDetails = AnimeDetails.newInstance((Anime) item);
            fragmentTransaction.add(R.id.container, animeDetails);
        }
        if (item instanceof Manga) {
            animeType = false;
            RanobeOrMangaDetails ranobeOrMangaDetails = RanobeOrMangaDetails.newInstance((Manga) item);
            fragmentTransaction.add(R.id.container, ranobeOrMangaDetails);
        }
    }

    private void setCompConfiguration() {

    }

    private void setListeners() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(DetailInfo.this, v);
                popup.setOnMenuItemClickListener(DetailInfo.this);
                popup.inflate(R.menu.menu_anime_details);
                popup.show();
            }
        });
    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        if (animeType)
        {
            inflater.inflate(R.menu.menu_anime_details, popup.getMenu());
        }
        else
        {
            inflater.inflate(R.menu.menu_ranobe_and_manga_details, popup.getMenu());
        }
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (animeType) {
            switch (item.getItemId()) {
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
        } else {
            switch (item.getItemId()) {
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
        }
        return super.onOptionsItemSelected(item);
    }
}

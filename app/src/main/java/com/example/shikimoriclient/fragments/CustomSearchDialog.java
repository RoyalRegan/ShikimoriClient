package com.example.shikimoriclient.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ajithvgiri.searchdialog.OnSearchItemSelected;
import com.ajithvgiri.searchdialog.SearchListAdapter;
import com.ajithvgiri.searchdialog.SearchListItem;
import com.example.shikimoriclient.R;

import java.util.ArrayList;
import java.util.List;


//TODO: Change this 'amazing' class
//TODO: store only 20 last request and save after close app
public class CustomSearchDialog {

    private static final String TAG = "SearchableDialog";
    private List<SearchListItem> searchListItems;
    private Activity activity;
    private String dTitle;
    private OnSearchItemSelected onSearchItemSelected;
    private AlertDialog alertDialog;
    private int position;
    private int style;
    private SearchListItem searchListItem = null;

    private SearchListAdapter searchListAdapter;
    private ListView listView;

    public CustomSearchDialog(Activity activity, String dialogTitle) {
        this.activity = activity;
        this.dTitle = dialogTitle;

    }

    public CustomSearchDialog(Activity activity, List<SearchListItem> searchListItems, String dialogTitle) {
        this.searchListItems = searchListItems;
        this.activity = activity;
        this.dTitle = dialogTitle;
    }

    public CustomSearchDialog(Activity activity, List<SearchListItem> searchListItems, String dialogTitle, int style) {
        this.searchListItems = searchListItems;
        this.activity = activity;
        this.dTitle = dialogTitle;
        this.style = style;
    }

    public void setOnItemSelected(OnSearchItemSelected searchItemSelected) {
        this.onSearchItemSelected = searchItemSelected;
    }

    //TODO: Show keyboard on create
    public void show() {
        final AlertDialog.Builder adb = new AlertDialog.Builder(activity);
        @SuppressLint("InflateParams") View view = activity.getLayoutInflater().inflate(R.layout.search_dialog_layout, null);
        TextView rippleViewClose = view.findViewById(R.id.close);
        TextView title = view.findViewById(R.id.spinerTitle);
        title.setText(dTitle);
        listView = view.findViewById(R.id.list);

        final EditText searchBox = view.findViewById(R.id.searchBox);
        searchListAdapter = new SearchListAdapter(activity, R.layout.items_view_layout, R.id.text1, searchListItems);
        listView.setAdapter(searchListAdapter);
        adb.setView(view);
        alertDialog = adb.create();
        alertDialog.getWindow().getAttributes().windowAnimations = style;
        alertDialog.setCancelable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t = view.findViewById(R.id.text1);
                for (int j = 0; j < searchListItems.size(); j++) {
                    if (t.getText().toString().equalsIgnoreCase(searchListItems.get(j).toString())) {
                        position = j;
                        searchListItem = searchListItems.get(position);
                    }
                }
                try {
                    onSearchItemSelected.onClick(position, searchListItem);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                alertDialog.dismiss();
            }
        });

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<SearchListItem> filteredValues = new ArrayList<>();
                for (int i = 0; i < searchListItems.size(); i++) {
                    if (searchListItems.get(i) != null) {
                        SearchListItem item = searchListItems.get(i);
                        if (item.getTitle().toLowerCase().trim().contains(searchBox.getText().toString().toLowerCase().trim())) {
                            filteredValues.add(item);
                        }
                    }
                }
                searchListAdapter = new SearchListAdapter(activity, R.layout.items_view_layout, R.id.text1, filteredValues);
                listView.setAdapter(searchListAdapter);
            }
        });

        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchListItems.add(0, new SearchListItem(searchListItems.size(), searchBox.getText().toString()));
                    //find by typed name
                    alertDialog.dismiss();
                    handled = true;
                }
                return handled;
            }
        });

        rippleViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void clear() {
        this.searchListItems.clear();
    }

    public void refresh() {
        searchListAdapter.notifyDataSetChanged();
    }

    public SearchListAdapter getAdapter() {
        return searchListAdapter;
    }

    public SearchListItem getSearchListItem() {
        return searchListItem;
    }

    public void setSearchListItems(List<SearchListItem> searchListItems) {
        this.searchListItems = searchListItems;
    }
}
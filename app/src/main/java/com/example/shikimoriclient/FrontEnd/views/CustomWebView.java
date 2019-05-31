package com.example.shikimoriclient.FrontEnd.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.WebView;

import java.io.File;
import java.util.Date;

import static android.view.inputmethod.EditorInfo.IME_ACTION_DONE;

public class CustomWebView extends WebView {
    public CustomWebView(Context context) {
        super(context);

        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    protected void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        BaseInputConnection baseInputConnection = new BaseInputConnection(this, true);
        outAttrs.imeOptions = IME_ACTION_DONE;
        return baseInputConnection;
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }
}
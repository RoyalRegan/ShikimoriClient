package com.example.shikimoriclient.FrontEnd.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.shikimoriclient.BackEnd.api.Api;
import com.example.shikimoriclient.BackEnd.api.user.Users;
import com.example.shikimoriclient.BackEnd.dao.user.User;
import com.example.shikimoriclient.BackEnd.dao.user.UserCredentials;
import com.example.shikimoriclient.FrontEnd.adapters.CustomWebView;
import com.example.shikimoriclient.MainActivity;
import com.example.shikimoriclient.R;

import java.io.FileOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.shikimoriclient.BackEnd.api.user.Users.AUTHENTICATION_URL;
import static com.example.shikimoriclient.BackEnd.api.user.Users.CLIENT_ID;
import static com.example.shikimoriclient.BackEnd.api.user.Users.CLIENT_SECRET;
import static com.example.shikimoriclient.BackEnd.api.user.Users.GRANT_TYPE;
import static com.example.shikimoriclient.BackEnd.api.user.Users.REDIRECT_URL;

public class SigInDialog {
    private View view;
    private AlertDialog alertDialog;
    private CustomWebView web;
    private MainActivity mainActivity;

    public SigInDialog(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void show() {
        final AlertDialog.Builder adb = new AlertDialog.Builder(mainActivity);
        initializeComp();
        adb.setView(view);
        alertDialog = adb.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initializeComp() {
        view = mainActivity.getLayoutInflater().inflate(R.layout.dialog_authorization, null);
        web = view.findViewById(R.id.webv);
        web.getSettings().setAppCacheEnabled(false);
        web.requestFocus(View.FOCUS_DOWN);
        web.loadUrl(AUTHENTICATION_URL);
        setListeners();
    }

    private void setListeners() {
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("authorize/")) {
                    Users api = Api.getUser();
                    Call<UserCredentials> call = api.getUserCredentials(
                            GRANT_TYPE,
                            CLIENT_ID,
                            CLIENT_SECRET,
                            url.substring(url.lastIndexOf("/") + 1),
                            REDIRECT_URL);
                    call.enqueue(new Callback<UserCredentials>() {
                        @Override
                        public void onResponse(Call<UserCredentials> call, Response<UserCredentials> response) {
                            UserCredentials userCredentials = response.body();
                            Call<User> innerCall = api.getUser(userCredentials.getTokenType() + " " + userCredentials.getAccessToken());
                            innerCall.enqueue(new Callback<User>() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    User user = response.body();
                                    String filename = "userInfo";
                                    FileOutputStream outputStream;
                                    try {
                                        outputStream = view.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                                        outputStream.write(("user_id:" + user.getId()).getBytes());
                                        outputStream.write("\n".getBytes());
                                        outputStream.write(("user_nickname:" + user.getNickname()).getBytes());
                                        outputStream.write("\n".getBytes());
                                        outputStream.write(("user_icon_url:" + user.getAvatarUrl()).getBytes());
                                        outputStream.write("\n".getBytes());
                                        outputStream.write(("access_token:" + userCredentials.getAccessToken()).getBytes());
                                        outputStream.write("\n".getBytes());
                                        outputStream.write(("expires_in:" + 86400).getBytes());
                                        outputStream.write("\n".getBytes());
                                        outputStream.write(("created_at:" + 1558890356).getBytes());
                                        outputStream.write("\n".getBytes());
                                        outputStream.write(("refresh_token:" + userCredentials.getRefreshToken()).getBytes());
                                        outputStream.close();
                                        mainActivity.checkUserData();
                                        CookieManager.getInstance().removeAllCookies(value -> {
                                        });
                                        CookieManager.getInstance().removeSessionCookies(value -> {
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<UserCredentials> call, Throwable t) {

                        }
                    });
                    alertDialog.dismiss();
                    return true;
                } else {
                    view.loadUrl(url);
                    return false;
                }
            }
        });
    }
}
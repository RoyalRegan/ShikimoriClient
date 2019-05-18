package com.example.shikimoriclient.BackEnd.api;

import com.example.shikimoriclient.BackEnd.dao.anime.CalendarItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Calendar {
    @GET("calendar")
    Call<List<CalendarItem>> getCalendar();
}

package com.example.shikimoriclient.BackEnd.api.anime.player;

import android.content.Context;

import com.example.shikimoriclient.BackEnd.dao.anime.AnimeVideo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@Deprecated
public class PlayerHandler {

    public static List<AnimeVideo> getVideos(String animeId, int seriaNumber, Context context) {
        Videos vid = Player.initalize().create(Videos.class);
        Call<String> call = vid.getVideosByEpisodeNumber("https://play.shikimori.org/" + animeId + "/video_online/" + seriaNumber);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Pattern pattern = Pattern.compile("<div class=\"b-video_variant\" data-video_id=\"[0-9]*\">.*?</div>");
                Matcher matcher = pattern.matcher(response.body());
                ArrayList<AnimeVideo> animeVideos = new ArrayList<>();
                while (matcher.find()) {
                    String info = matcher.group();
                    Pattern id = Pattern.compile("<div class=\"b-video_variant\" data-video_id=\"[0-9]*\">");
                    Matcher idMatcher = id.matcher(info);
                    Pattern kind = Pattern.compile("<span class=\"video-kind [a-z]+\">.+?</span>");
                    Matcher kindMatcher = kind.matcher(info);
                    Pattern hosting = Pattern.compile("<span class=\"video-hosting\">.+?</span>");
                    Matcher hostingMatcher = hosting.matcher(info);
                    Pattern author = Pattern.compile("<span class=\"video-author\">.+?</span>");
                    Matcher authorMatcher = author.matcher(info);
                    Matcher valueMathcer;
                    AnimeVideo video = new AnimeVideo();
                    if (idMatcher.find()) {
                        Pattern value = Pattern.compile("[0-9]+");
                        valueMathcer = value.matcher(idMatcher.group());
                        if (valueMathcer.find()) {
                            video.setId(valueMathcer.group());
                        }
                    }
                    if (kindMatcher.find()) {
                        Pattern value = Pattern.compile(">.+<");
                        valueMathcer = value.matcher(kindMatcher.group());
                        if (valueMathcer.find()) {
                            video.setType(valueMathcer.group().substring(1, valueMathcer.group().length() - 1));
                        }
                    }
                    if (hostingMatcher.find()) {
                        Pattern value = Pattern.compile(">.+<");
                        valueMathcer = value.matcher(hostingMatcher.group().substring(1, hostingMatcher.group().length() - 1));
                        if (valueMathcer.find()) {
                            video.setHost(valueMathcer.group());
                        }
                    }
                    if (authorMatcher.find()) {
                        Pattern value = Pattern.compile(">.+<");
                        valueMathcer = value.matcher(authorMatcher.group().substring(1, authorMatcher.group().length() - 1));
                        if (valueMathcer.find()) {
                            video.setAuthor(valueMathcer.group());
                        }
                    }
                    animeVideos.add(video);
                }
                //here start intent
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return null;
    }

    public static String getPlayerLink(String animeId, int seriaNumber, String id, Context context) {
        Videos vid = Player.initalize().create(Videos.class);
        Call<String> calll = vid.getPlayerUrlByVideoId("https://play.shikimori.org/" + animeId + "/video_online/" + seriaNumber + "/" + id);
        calll.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Pattern pattern = Pattern.compile("<iframe src=\".+?\"");
                Matcher matcher = pattern.matcher(response.body());
                if (matcher.find()) {
                    String playerUrl = "https:" + matcher.group().substring(matcher.group().indexOf('"') + 1, matcher.group().lastIndexOf('"'));
                    //here start intent
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return null;
    }
}

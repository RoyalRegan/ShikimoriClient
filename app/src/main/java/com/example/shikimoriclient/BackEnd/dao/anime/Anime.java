package com.example.shikimoriclient.BackEnd.dao.anime;

import com.example.shikimoriclient.BackEnd.dao.ScoreRate;
import com.example.shikimoriclient.BackEnd.dao.StatusRate;
import com.google.gson.annotations.SerializedName;

public class Anime extends AnimeSimple {
    private AnimeRating rating;
    private String[] english;
    private String[] japanese;
    private String[] synonyms;
    @SerializedName("license_name_ru")
    private String licenseNameRu;
    private int duration;
    private String score;
    private String description;
    @SerializedName("description_html")
    private String descriptionHtml;
    @SerializedName("description_source")
    private String descriptionSource;
    private String franchise;
    private boolean favoured;
    private boolean anons;
    private boolean ongoing;
    @SerializedName("thread_id")
    private boolean thread_id;
    @SerializedName("topic_id")
    private boolean topic_id;
    @SerializedName("myanimelist_id")
    private boolean myanimelist_id;
    @SerializedName("rates_scores_stats")
    private ScoreRate[] ratesScoresStats;
    @SerializedName("rates_statuses_stats")
    private StatusRate[] ratesStatusesStats;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("next_episode_at")
    private String nextEpisodeAt;
    private Genre[] genres;
    private Studio[] studios;
    private Video[] videos;
    private Screenshot[] screenshots;


    //@SerializedName("user_rate")
    //private String userRate;
}

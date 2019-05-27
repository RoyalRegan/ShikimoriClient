package com.example.shikimoriclient.BackEnd.dao.manga;

import com.example.shikimoriclient.BackEnd.dao.Genre;
import com.example.shikimoriclient.BackEnd.dao.ScoreRate;
import com.example.shikimoriclient.BackEnd.dao.StatusRate;
import com.example.shikimoriclient.BackEnd.dao.UserRate;
import com.example.shikimoriclient.BackEnd.dao.anime.AnimeRating;
import com.example.shikimoriclient.BackEnd.dao.anime.Screenshot;
import com.example.shikimoriclient.BackEnd.dao.anime.Studio;
import com.example.shikimoriclient.BackEnd.dao.anime.Video;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Manga extends MangaSimple implements Serializable {
    private String[] english;
    private String[] japanese;
    private String[] synonyms;
    @SerializedName("license_name_ru")
    private String licenseNameRu;
    private String score;
    private Genre[] genres;
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
    private int thread_id;
    @SerializedName("topic_id")
    private int topic_id;
    @SerializedName("myanimelist_id")
    private int myanimelist_id;
    @SerializedName("rates_scores_stats")
    private ScoreRate[] ratesScoresStats;
    @SerializedName("rates_statuses_stats")
    private StatusRate[] ratesStatusesStats;
    @SerializedName("user_rate")
    private UserRate userRate;
    private Publisher[] publishers;

    public String[] getEnglish() {
        return english;
    }

    public String[] getJapanese() {
        return japanese;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public String getLicenseNameRu() {
        return licenseNameRu;
    }

    public String getScore() {
        return score;
    }

    public String getDescription() {
        if (description == null) {
            return "Нет описания";
        }
        return description;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public String getDescriptionSource() {
        return descriptionSource;
    }

    public String getFranchise() {
        return franchise;
    }

    public boolean isFavoured() {
        return favoured;
    }

    public boolean isAnons() {
        return anons;
    }

    public boolean isOngoing() {
        return ongoing;
    }

    public int getThread_id() {
        return thread_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public int getMyanimelist_id() {
        return myanimelist_id;
    }

    public ScoreRate[] getRatesScoresStats() {
        return ratesScoresStats;
    }

    public StatusRate[] getRatesStatusesStats() {
        return ratesStatusesStats;
    }

    public UserRate getUserRate() {
        return userRate;
    }

    public Genre[] getGenres() {
        return genres;
    }
}

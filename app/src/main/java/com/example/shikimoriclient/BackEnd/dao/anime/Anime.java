package com.example.shikimoriclient.BackEnd.dao.anime;

import com.example.shikimoriclient.BackEnd.dao.Genre;
import com.example.shikimoriclient.BackEnd.dao.ScoreRate;
import com.example.shikimoriclient.BackEnd.dao.StatusRate;
import com.example.shikimoriclient.BackEnd.dao.UserRate;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Anime extends AnimeSimple implements Serializable {
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
    private int thread_id;
    @SerializedName("topic_id")
    private int topic_id;
    @SerializedName("myanimelist_id")
    private int myanimelist_id;
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
    @SerializedName("user_rate")
    private UserRate userRate;

    public AnimeRating getRating() {
        return rating;
    }

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

    public int getDuration() {
        return duration;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getNextEpisodeAt() {
        return nextEpisodeAt;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public Studio[] getStudios() {
        return studios;
    }

    public Video[] getVideos() {
        return videos;
    }

    public Screenshot[] getScreenshots() {
        return screenshots;
    }

    public UserRate getUserRate() {
        return userRate;
    }
}

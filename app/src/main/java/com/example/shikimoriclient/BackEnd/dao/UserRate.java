package com.example.shikimoriclient.BackEnd.dao;

import com.google.gson.annotations.SerializedName;

public class UserRate {
    private int id;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("target_id")
    private int targetId;
    @SerializedName("target_type")
    private String targetType;
    private int score;
    private String status;
    private String text;
    private int episodes;
    private int chapters;
    private int volumes;
    @SerializedName("text_html")
    private String textHtml;
    private int rewatches;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTargetId() {
        return targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public int getScore() {
        return score;
    }

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public int getEpisodes() {
        return episodes;
    }

    public int getChapters() {
        return chapters;
    }

    public int getVolumes() {
        return volumes;
    }

    public String getTextHtml() {
        return textHtml;
    }

    public int getRewatches() {
        return rewatches;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}

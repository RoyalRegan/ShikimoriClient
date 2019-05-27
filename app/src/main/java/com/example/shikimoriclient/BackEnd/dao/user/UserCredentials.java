package com.example.shikimoriclient.BackEnd.dao.user;

import com.google.gson.annotations.SerializedName;

public class UserCredentials {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("created_at")
    private int createdAt;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

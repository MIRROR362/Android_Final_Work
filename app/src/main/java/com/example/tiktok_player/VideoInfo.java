package com.example.tiktok_player;
import com.google.gson.annotations.SerializedName;

class VideoInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("feedurl")
    private String feedurl;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("description")
    private String description;
    @SerializedName("likecount")
    private String likecount;
    @SerializedName("avatar")
    private String avatar;

    public String getId(){
        return this.id;
    }

    public String getUrl(){
        return feedurl;
    }

    public String getNickname() { return nickname; }

    public String getDescription(){
        return description;
    }

    public String getLikecount(){
        return likecount;
    }

    public String getAvatar(){
        return avatar;
    }



    @Override
    public String toString() {
        return "VideoInfo{" +
                "id=" + id +
                ", feedUrl='" + feedurl +
                ", nickname=" + nickname +
                ", description=" + description +
                ", likeCount=" + likecount +
                ", avatar=" + avatar +
                '}';
    }
}

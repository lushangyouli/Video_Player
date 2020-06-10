package com.example.video_plyaer;

public class VideoResult {
    private String _id;
    private String feedurl;
    private String nickname;
    private String description;
    private int likecount;
    private String avatar;

    public void set_id(String _id){
        this._id = _id;
    }

    public void setFeedurl(String feedurl){
        this.feedurl = feedurl;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setLikecount(int likecount){
        this.likecount = likecount;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String get_id() {
        return _id;
    }

    public String getFeedurl(){
        return feedurl;
    }

    public String getNickname(){
        return nickname;
    }

    public String getDescription(){
        return description;
    }

    public int getLikecount(){
        return likecount;
    }

    public String getAvatar(){
        return avatar;
    }
}

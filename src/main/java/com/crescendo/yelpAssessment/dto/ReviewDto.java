package com.crescendo.yelpAssessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewDto {

    private String id;
    private String url;
    private String text;
    private String timeCreated;
    private int rating;

    private UserDto user;

    public ReviewDto(){

    }

    public ReviewDto(@JsonProperty("id") String id,
                     @JsonProperty("url") String url,
                     @JsonProperty("text") String text,
                     @JsonProperty("time_created") String timeCreated,
                     @JsonProperty("rating") int rating,
                     @JsonProperty("user") UserDto user){

        this.id = id;
        this.url = url;
        this.text = text;
        this.timeCreated = timeCreated;
        this.rating = rating;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}

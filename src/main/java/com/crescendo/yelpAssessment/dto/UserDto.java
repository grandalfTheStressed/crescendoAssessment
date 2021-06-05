package com.crescendo.yelpAssessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    private String id;
    private String profileUrl;
    private String imageUrl;
    private String name;
    private String joyLikelihood;
    private String sorrowLikelihood;
    private String angerLikelihood;
    private String surpriseLikelihood;

    public UserDto(){

    }

    public UserDto(@JsonProperty("id") String id,
                   @JsonProperty("profile_url") String profileUrl,
                   @JsonProperty("image_url") String imageUrl,
                   @JsonProperty("name") String name){

        this.id = id;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoyLikelihood() {
        return joyLikelihood;
    }

    public void setJoyLikelihood(String joyLikelihood) {
        this.joyLikelihood = joyLikelihood;
    }

    public String getSorrowLikelihood() {
        return sorrowLikelihood;
    }

    public void setSorrowLikelihood(String sorrowLikelihood) {
        this.sorrowLikelihood = sorrowLikelihood;
    }

    public String getAngerLikelihood() {
        return angerLikelihood;
    }

    public void setAngerLikelihood(String angerLikelihood) {
        this.angerLikelihood = angerLikelihood;
    }

    public String getSurpriseLikelihood() {
        return surpriseLikelihood;
    }

    public void setSurpriseLikelihood(String surpriseLikelihood) {
        this.surpriseLikelihood = surpriseLikelihood;
    }
}

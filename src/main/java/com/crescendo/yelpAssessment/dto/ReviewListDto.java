package com.crescendo.yelpAssessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ReviewListDto {

    private ArrayList<ReviewDto> list;
    private int total;
    private ArrayList<String> possibleLang;

    public ReviewListDto(){


    }

    public ReviewListDto(@JsonProperty("reviews") ArrayList<ReviewDto> list,
                         @JsonProperty("total") int total,
                         @JsonProperty("possible_languages") ArrayList<String> possibleLang){

        this.list = list;
        this.total = total;
        this.possibleLang = possibleLang;
    }

    public ArrayList<ReviewDto> getList() {
        return list;
    }

    public void setList(ArrayList<ReviewDto> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<String> getPossibleLang() {
        return possibleLang;
    }

    public void setPossibleLang(ArrayList<String> possibleLang) {
        this.possibleLang = possibleLang;
    }
}

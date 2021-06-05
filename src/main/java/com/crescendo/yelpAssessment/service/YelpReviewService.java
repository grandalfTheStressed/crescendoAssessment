package com.crescendo.yelpAssessment.service;

import com.crescendo.yelpAssessment.dto.ReviewListDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class YelpReviewService {

    String yelpApiKey = "Bearer " + "";

    public YelpReviewService() throws MalformedURLException {
    }


    public ReviewListDto getReviews(String yelpId) throws IOException {

        URL url = new URL("https://api.yelp.com/v3/businesses/" + yelpId + "/reviews");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", yelpApiKey);
        con.setDoInput(true);
        con.setDoOutput(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String receive = "";
        String line;
        while ((line = in.readLine()) != null){
            receive += line;
        }

        in.close();

        ObjectMapper objectMapper = new ObjectMapper();

        ReviewListDto list = objectMapper.readValue(receive, ReviewListDto.class);

        return list;
    }

}

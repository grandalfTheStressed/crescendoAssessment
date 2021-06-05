package com.crescendo.yelpAssessment.controller;

import com.crescendo.yelpAssessment.dto.ReviewListDto;
import com.crescendo.yelpAssessment.service.GoogleVisionService;
import com.crescendo.yelpAssessment.service.YelpReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/")
public class yelpEndpoint {

    @Autowired
    YelpReviewService yelpReviewService;

    @Autowired
    GoogleVisionService googleVisionService;

    @GetMapping("review/{yelpId}")
    public Object getReviewData(@PathVariable("yelpId") String yelpId) throws IOException {

        ReviewListDto rv = yelpReviewService.getReviews(yelpId);
        googleVisionService.useGoogleVision(rv);
        return rv.getList();
    }
}

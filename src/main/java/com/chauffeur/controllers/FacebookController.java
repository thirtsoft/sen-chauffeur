package com.chauffeur.controllers;

import com.chauffeur.controllers.api.FacebookApi;
import com.chauffeur.services.FacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FacebookController implements FacebookApi {

    private final FacebookService facebookService;

    @Autowired
    public FacebookController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }


    @Override
    public int getNumberOfPagesLikes() {
        return facebookService.countNumbersOfLikes();
    }

    @Override
    public PagedList<Post> getNumberOfPagesSubscribesUsers() {
        return facebookService.countNumbersOfSubscribesUsers();
    }

    @Override
    public String countNumbersOfMentionLikes() {
        return null;
    }

    @Override
    public int countNumbersOfAbonnees() {
        return facebookService.countNumbersOfAbonnees();
    }
}

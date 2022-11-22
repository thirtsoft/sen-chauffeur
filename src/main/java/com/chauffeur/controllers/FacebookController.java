package com.chauffeur.controllers;

import com.chauffeur.controllers.api.FacebookApi;
import com.chauffeur.services.FacebookService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://sunuchauffeur.com")
@RestController
public class FacebookController implements FacebookApi {

    private final FacebookService facebookService;

    @Autowired
    public FacebookController(FacebookService facebookService) {
        this.facebookService = facebookService;
    }


    @Override
    public int getNumberOfPagesLikes() throws IOException, JSONException {
        return facebookService.countNumbersOfUsersWhoLikesPages();
    }

    @Override
    public int countNumberOfPagesFollowers() throws IOException, JSONException {
        return facebookService.countNumberOfPagesFollowers();
    }
}

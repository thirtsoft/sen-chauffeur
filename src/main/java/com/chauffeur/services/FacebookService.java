package com.chauffeur.services;

import org.json.JSONException;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;

import java.io.IOException;

public interface FacebookService {

    int countNumbersOfLikes();

    PagedList<Post> countNumbersOfSubscribesUsers();

    int countNumbersOfAbonnees();

    int countNumberOfPagesFollowers() throws IOException, JSONException;

    int countNumbersOfUsersWhoLikesPages() throws IOException, JSONException;

}

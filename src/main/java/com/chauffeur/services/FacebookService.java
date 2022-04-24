package com.chauffeur.services;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;

public interface FacebookService {

    int countNumbersOfLikes();

    PagedList<Post> countNumbersOfSubscribesUsers();

    int countNumbersOfAbonnees();

    int countNumbersOfMentionLikes();

}

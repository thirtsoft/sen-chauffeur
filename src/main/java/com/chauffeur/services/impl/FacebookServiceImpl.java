package com.chauffeur.services.impl;

import com.chauffeur.services.FacebookService;
import com.chauffeur.utils.FacebookConstants;
import com.restfb.*;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;
import com.restfb.types.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class FacebookServiceImpl implements FacebookService {

    @Value("${spring.social.facebook.app-id}")
    private String facebookAppId;

    @Value("${spring.social.facebook.app-secret}")
    private String facebookAppSecret;

    private final String facebookAcessToken = "EAAEWdPvZA0YEBAObOqyjsHnnXKOn0CQL9oE8vCSGNujhYdmwA85cCN9XEbFmWHnxteiZCmw0OejUqF9VpiYKgvs5pyiIA0MRAJIciHlgtb3dvOSyZA3fcUxzLJigSa91K8x2eq8E6h5LZCVrFuO19kEGSdOVcwayC99JzVdEaMXdwyJwJ3H3B3c9OcKZC9TtZAuDWHNetLLzFpJcCGcEmCtNRGX3HZAx4ZCQJpUrrjAZBC2WHQCkcp8jP";


    private FacebookConnectionFactory createConnection() {
        return new FacebookConnectionFactory(facebookAppId, facebookAppSecret);
    }


    @Override
    public int countNumbersOfLikes() {
        Facebook facebook = new FacebookTemplate(this.getFacebookAcessToken());
        return facebook.pageOperations().getPage(FacebookConstants.FACBOOK_PAGEID).getNewLikeCount();
    }

    @Override
    public PagedList<Post> countNumbersOfSubscribesUsers() {
        return new FacebookTemplate(this.getFacebookAcessToken()).feedOperations().getFeed();
    }

    @Override
    public int countNumbersOfAbonnees() {
        FacebookClient fbClient = new DefaultFacebookClient(this.getFacebookAcessToken(), Version.VERSION_3_1);

    //    Connection<Page> postConnection = fbClient.fetchConnection(FacebookConstants.FACBOOK_PAGEID+"?fields=followers_count", Page.class);

    //    Connection<Page> postConnection =fbClient.fetchConnection("112263434790734/posts", Page.class);

        JsonObject jObj=fbClient.fetchObject(FacebookConstants.FACBOOK_PAGEID + "/posts", JsonObject.class,Parameter.with("data", "story, created_time"));
        //System.out.println(jObj);
        JsonValue data=jObj.get("data");
//      System.out.println(data);
        JsonValue post_clicks=((JsonObject) ( (JsonArray) data).get(0)).get("story");
        System.out.println(post_clicks.asString());
        JsonValue valueClick= ((JsonObject) ((JsonArray) ((JsonObject) ((JsonArray) data).get(0)).get("values")).get(0)).get("value");
        System.out.println(valueClick.asInt());

        int counter = 0;
        /*
        for (List<Page> myFriend : postConnection) {
            for (Page friend : myFriend) {
                String friendId = friend.getId();
                int nbre = friend.getNewLikeCount();
                int follow = friend.getEngagement().getCount();

                System.out.println(nbre);
                System.out.println(follow);

                counter++;

            }
        }
        */

/*
        Page page = fbClient.fetchObject(FacebookConstants.FACBOOK_PAGEID,
                Page.class,Parameter.with("fields", postConnection.getTotalCount()));*/





/*
        System.out.println("Comments count: " + fbClient.getJsonMapper(page.getEngagement().getCount()));
        System.out.println("Comments count: " + page.getNewLikeCount());

        int counter = 0;
        for (int i =0; i<page.getEngagement().getCount(); i++) {

                System.out.println(page.getName());
                System.out.println(page.getNewLikeCount());

                counter++;

        } */
/*
        for (int i =0; i<page.) {
            for (Page page1 : feedPage) {
                System.out.println(page1.getName());
                System.out.println(page1.getNewLikeCount());

                counter++;
            }
        } */
        return counter;
    }

    @Override
    public int countNumbersOfMentionLikes() {
        if (this.getFacebookAcessToken() == null) {
            log.error("Error connection");
        }
        PagedList<Post> postPagedList = new FacebookTemplate(this.getFacebookAcessToken()).feedOperations().getFeed();

        return 1;
    }

    public String getFacebookAcessToken() {
        if (this.createConnection() == null) {
            log.error("Connection to facebook failed");
            return null;
        }
        return facebookAcessToken;
    }
}

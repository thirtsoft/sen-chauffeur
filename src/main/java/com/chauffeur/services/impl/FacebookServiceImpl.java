package com.chauffeur.services.impl;

import com.chauffeur.services.FacebookService;
import com.chauffeur.utils.FacebookConstants;
import com.restfb.*;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;
import com.restfb.types.Notification;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Service
@Transactional
@Slf4j
public class FacebookServiceImpl implements FacebookService {

    @Value("${spring.social.facebook.app-id}")
    private String facebookAppId;

    @Value("${spring.social.facebook.app-secret}")
    private String facebookAppSecret;

    private final String facebookAcessToken = "EAAEWdPvZA0YEBAAIKMA5JN7ZBqYeLivVb2UWhJZBZCZBWZA4stIXcYNaC9Y6v4cq2PJ9sNB72U6iSx3PtxKKpRzhXeo5uPNGZCS3cnGEYY0UgxbZAlGD3iqZCXlD0vhAI9FiQtY01Mlz4sgouwqRswfaSdyHDCghJJZB0F5IxF2YKSSFGIlIH4ebX4AfaOdfN4x0hI66vypEErQtsciib31lLGVf1ag4KypyMR07ZBOTfHGAsjNEnmtpOpM";


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


        return counter;
    }

    @Override
    public int countNumberOfPagesFollowers() throws IOException, JSONException {
        int nombrePageFollowers = 0;
        JSONObject json;
        try {
            json = new JSONObject(readUrl("https://graph.facebook.com/sunuchauffeurSn/?fields=followers_count&access_token=EAAEWdPvZA0YEBAAIKMA5JN7ZBqYeLivVb2UWhJZBZCZBWZA4stIXcYNaC9Y6v4cq2PJ9sNB72U6iSx3PtxKKpRzhXeo5uPNGZCS3cnGEYY0UgxbZAlGD3iqZCXlD0vhAI9FiQtY01Mlz4sgouwqRswfaSdyHDCghJJZB0F5IxF2YKSSFGIlIH4ebX4AfaOdfN4x0hI66vypEErQtsciib31lLGVf1ag4KypyMR07ZBOTfHGAsjNEnmtpOpM"));
            System.out.println(json.toString());
            System.out.println(json.get("followers_count"));

            nombrePageFollowers = json.getInt("followers_count");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return nombrePageFollowers;
    }

    @Override
    public int countNumbersOfUsersWhoLikesPages() throws IOException, JSONException {
        int nombrePageLikes = 0;
        JSONObject json;
        try {
            json = new JSONObject(readUrl("https://graph.facebook.com/sunuchauffeurSn/?fields=fan_count&access_token=EAAEWdPvZA0YEBAAIKMA5JN7ZBqYeLivVb2UWhJZBZCZBWZA4stIXcYNaC9Y6v4cq2PJ9sNB72U6iSx3PtxKKpRzhXeo5uPNGZCS3cnGEYY0UgxbZAlGD3iqZCXlD0vhAI9FiQtY01Mlz4sgouwqRswfaSdyHDCghJJZB0F5IxF2YKSSFGIlIH4ebX4AfaOdfN4x0hI66vypEErQtsciib31lLGVf1ag4KypyMR07ZBOTfHGAsjNEnmtpOpM"));
            System.out.println(json.toString());
            System.out.println(json.get("fan_count"));
            System.out.println(json.get("id"));

            nombrePageLikes = json.getInt("fan_count");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return nombrePageLikes;
    }


    public String getFacebookAcessToken() {
        if (this.createConnection() == null) {
            log.error("Connection to facebook failed");
            return null;
        }
        return facebookAcessToken;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];

            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}

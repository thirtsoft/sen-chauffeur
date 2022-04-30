package com.chauffeur;


import com.chauffeur.enumeration.RoleName;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.RoleRepository;
import com.chauffeur.repository.UtilisateurRepository;

import com.chauffeur.services.UtilisateurService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@SpringBootApplication
public class SenChauffeurApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SenChauffeurApplication.class);

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.social.facebook.app-id}")
    private static String facebookAppId;

    @Value("${spring.social.facebook.app-secret}")
    private static String facebookAppSecret;

    String accessTokenString = "EAAEWdPvZA0YEBACbL3g0gzYUKflutC" +
            "b7H7Ftq1fzIeJqGcRZAUyikprt40rV6fT2ZC8ZAUp6Iy6HQ5cw6eiQESa3zEJc7m0o0WUdTom" +
            "WrSFbgQLtgFHz8ffKfGEQQ4A5VaxGUxLQVYFhJLI51obZBQB7FReOmKD73ZAEes0yqz2QWBgROhD7Ks3GwI76axpDCUt3lBliwjRQZDZD";


    public static void main(String[] args) throws IOException, JSONException {
        SpringApplication.run(SenChauffeurApplication.class, args);

        createChauffeursDirectoryPhotoIfItDoesntExist();

        createChauffeursDirectoryCvIfItDoesntExist();

        createAnnonceDirectoryIfItDoesntExist();


        JSONObject json;
        try {
            json = new JSONObject(readUrl("https://graph.facebook.com/sunuchauffeurSn/?fields=fan_count&access_token=EAAEWdPvZA0YEBACbL3g0gzYUKflutCb7H7Ftq1fzIeJqGcRZAUyikprt40rV6fT2ZC8ZAUp6Iy6HQ5cw6eiQESa3zEJc7m0o0WUdTomWrSFbgQLtgFHz8ffKfGEQQ4A5VaxGUxLQVYFhJLI51obZBQB7FReOmKD73ZAEes0yqz2QWBgROhD7Ks3GwI76axpDCUt3lBliwjRQZDZD"));
            System.out.println(json.toString());
            System.out.println(json.get("fan_count"));
            System.out.println(json.get("id"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




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


    private static FacebookConnectionFactory createConnection() {
        return new FacebookConnectionFactory(facebookAppId, facebookAppSecret);
    }


    private String getFacebookAcessToken() {
        if (this.createConnection() == null) {
            return null;
        }
        return accessTokenString;
    }




    private static void createChauffeursDirectoryPhotoIfItDoesntExist() {
        Path path = Paths.get(System.getProperty("user.home") + "/senchauffeur/chauffeur/photos/");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                LOG.error(String.format("Problem creating directory %s", path));
            }
        }
    }

    private static void createChauffeursDirectoryCvIfItDoesntExist() {
        Path path = Paths.get(System.getProperty("user.home") + "/senchauffeur/chauffeur/cvs/");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                LOG.error(String.format("Problem creating directory %s", path));
            }
        }
    }

    private static void createAnnonceDirectoryIfItDoesntExist() {
        Path path = Paths.get(System.getProperty("user.home") + "/senchauffeur/annoncephotos/");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                LOG.error(String.format("Problem creating directory %s", path));
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {


        Utilisateur admin = new Utilisateur();
        admin.setId(5L);
        admin.setUsername("Admin");
        admin.setName("Admin diallo");
        admin.setActive(true);
        admin.setPassword(bCryptPasswordEncoder.encode("admin1234"));
        utilisateurRepository.save(admin);
        utilisateurService.addRoleToUser("Admin", RoleName.ROLE_ADMIN);



    }

}

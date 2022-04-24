package com.chauffeur;


import com.chauffeur.repository.RoleRepository;
import com.chauffeur.repository.UtilisateurRepository;
import com.chauffeur.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
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

    public static void main(String[] args) {
        SpringApplication.run(SenChauffeurApplication.class, args);

        createChauffeursDirectoryPhotoIfItDoesntExist();

        createChauffeursDirectoryCvIfItDoesntExist();

        createAnnonceDirectoryIfItDoesntExist();

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

        /*
        Utilisateur admin = new Utilisateur();
        admin.setId(5L);
        admin.setUsername("thir");
        admin.setName("thir diallo");
        admin.setActive(true);
        admin.setPassword(bCryptPasswordEncoder.encode("admin1234"));
        utilisateurRepository.save(admin);
        utilisateurService.addRoleToUser("thir", RoleName.ROLE_ADMIN);
        */


    }

}

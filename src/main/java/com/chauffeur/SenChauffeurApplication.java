package com.chauffeur;


import com.chauffeur.enumeration.RoleName;
import com.chauffeur.models.*;
import com.chauffeur.repository.*;
import com.chauffeur.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletContext;


@SpringBootApplication
public class SenChauffeurApplication implements CommandLineRunner {

    /*

    private static final Logger LOG = LoggerFactory.getLogger(SenChauffeurApplication.class);
    @Autowired
    ServletContext context;

    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private AddresseRepository addresseRepository;

    @Autowired
    private TarifRepository tarifRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        private EmailService emailService;

        @Autowired
        private JavaMailSender javaMailSender;

        @Autowired
        private EmailRepository emailRepository;

        */


    public static void main(String[] args) {
        SpringApplication.run(SenChauffeurApplication.class, args);

        //    createChauffeursDirectoryPhotoIfItDoesntExist();

        //  createChauffeursDirectoryCvIfItDoesntExist();

        //    createAnnonceDirectoryIfItDoesntExist();

    }

    /*
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

    */

    @Override
    public void run(String... args) throws Exception {

        /*

        Permis p1 = permisRepository.save(new Permis(1L, "P1", "Permis Poids Legere", 10));
        Permis p2 = permisRepository.save(new Permis(2L, "P2", "Permis Professionnel", 5));
        Permis p3 = permisRepository.save(new Permis(3L, "P3", "Permis Tracteur", 15));
        Permis p4 = permisRepository.save(new Permis(4L, "P4", "Permis Transport", 20));
        Permis p5 = permisRepository.save(new Permis(5L, "P5", "Permis Conducteur d’Engin", 20));

        Addresse ad1 = addresseRepository.save(new Addresse(1L, "DK", "54DK", "Dakar", "SENEGAL"));
        Addresse ad2 = addresseRepository.save(new Addresse(2L, "DL", "4DB", "'Diourbel'", "SENEGAL"));
        Addresse ad3 = addresseRepository.save(new Addresse(3L, "FT", "54FT", "Fatick", "SENEGAL"));
        Addresse ad4 = addresseRepository.save(new Addresse(4L, "KF", "54KF", "Kaffrine", "SENEGAL"));
        Addresse ad5 = addresseRepository.save(new Addresse(5L, "KL", "54KL", "Kaolack", "SENEGAL"));
        Addresse ad6 = addresseRepository.save(new Addresse(6L, "KED", "54KED", "Kedougou", "SENEGAL"));
        Addresse ad7 = addresseRepository.save(new Addresse(7L, "KD", "54KD", "Kolda", "SENEGAL"));
        Addresse ad8 = addresseRepository.save(new Addresse(8L, "LG", "54LG", "Louga", "SENEGAL"));
        Addresse ad9 = addresseRepository.save(new Addresse(9L, "MT", "54MT", "Matam", "SENEGAL"));
        Addresse ad10 = addresseRepository.save(new Addresse(10L, "ST", "54ST", "Saint-Louis", "SENEGAL"));
        Addresse ad11 = addresseRepository.save(new Addresse(11L, "SD", "54SD", "Sedhiou", "SENEGAL"));
        Addresse ad12 = addresseRepository.save(new Addresse(12L, "TB", "54TB", "Tambacounda", "SENEGAL"));
        Addresse ad13 = addresseRepository.save(new Addresse(12L, "TH", "54TH", "Thies", "SENEGAL"));
        Addresse ad14 = addresseRepository.save(new Addresse(14L, "ZG", "54ZG", "Ziguinchor", "SENEGAL"));


        Chauffeur ch1 = new Chauffeur(1L, "Chauffeur particulier", "Thierno", "Diallo", "M", "thierno@gmail.com", "779221401", "3", 120000,
                "Dk-Thies-Bignona-Zig", "Full-Time", true, "cv_thierno.pdf", "photo1.jpg", p1, ad14);
        Chauffeur ch2 = new Chauffeur(2L, "Chauffeur particulier", "Ibrahima", "Sene", "M", "ibrahim@gmail.com", "778064307", "15", 250000,
                "Senegal et SousRegion", "Full-Time", true, "cv_seneIbrahima.pdf", "photo2.jpg", p1, ad1);
        Chauffeur ch3 = new Chauffeur(3L, "Chauffeur Livraison", "Abdou", "Thiaw", "M", "abdou@gmail.com", "779402602", "6", 200000,
                "Partout au Senegal", "Full-Time", true, "cv_abduThiaw.pdf", "photo3.jpg", p1, ad1);
        Chauffeur ch4 = new Chauffeur(4L, "Chauffeur transport", "Ngor", "Ndour", "M", "ngor@gmail.com", "773152045", "8", 220000,
                "Partout au Senegal", "Partial-Time", true, "cv_ngor.pdf", "photo5.jpg", p1, ad1);
        Chauffeur ch5 = new Chauffeur(5L, "Chauffeur professionel", "Khadim", "Ndiaye", "M", "khadim@gmail.com", "776440310", "7", 600000,
                "Partout au Senegal", "Full-Time", true, "cv_khadimNdiaye.pdf", "photo6.jpg", p1, ad1);
        Chauffeur ch6 = new Chauffeur(6L, "Chauffeur transport", "Modou", "Fall", "M", "modu@gmail.com", "776440310", "11", 600000,
                "Partout au Senegal", "Full-Time", true, "cv_modu.pdf", "photo4.jpg", p2, ad1);

        Chauffeur ch7 = new Chauffeur(7L, "Chauffeur Camion", "Abdou", "Diouf", "M", "abdue@gmail.com", "776440310", "10ans", 600000,
                "Dk-Zig", "Full-Time", true, "cv1.pdf", "photo7.jpg", p5, ad4);
        Chauffeur ch8 = new Chauffeur(8L, "Chauffeur engin", "Malick", "Sane", "F", "malick@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Partial-Time", false, "cv1.pdf", "photo8.jpg", p4, ad3);

        Chauffeur ch9 = new Chauffeur(9L, "Chauffeur Taxi", "Ousseynou", "Ly", "M", "ouzin@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Full-Time", true, "cv1.pdf", "photo9.jpg", p4, ad5);

        Chauffeur ch10 = new Chauffeur(10L, "Chauffeur Transport", "Alassane", "Badji", "M", "alassane@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Full-Time", false, "cv1.pdf", "photo10.jpg", p4, ad6);

        Chauffeur ch11 = new Chauffeur(11L, "Chauffeur prive", "Sire", "Diallo", "M", "saliou@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Full-Time", true, "cv1.pdf", "photo11.jpg", p1, ad11);

        Chauffeur ch12 = new Chauffeur(12L, "Chauffeur tracteur", "Mouhamed", "Diatta", "M", "mouhamed@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Full-Time", false, "cv1.pdf", "photo12.jpg", p2, ad12);

        Chauffeur ch13 = new Chauffeur(13L, "Chauffeur autobus", "Mamadi", "Sanogo", "M", "mamadi@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Full-Time", false, "cv1.pdf", "photo13.jpg", p3, ad8);

        Chauffeur ch14 = new Chauffeur(14L, "Chauffeur prive", "Alpha yaya", "Sene", "M", "alphayaya@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Full-Time", false, "cv1.pdf", "photo14.jpg", p4, ad13);

        Chauffeur ch15 = new Chauffeur(15L, "Chauffeur tata", "Mory", "Ba", "M", "morie@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Full-Time", false, "cv1.pdf", "photo15.jpg", p1, ad2);

        Chauffeur ch16 = new Chauffeur(16L, "Chauffeur bus", "Lamine", "Diao", "M", "lamie@gmail.com", "776440310", "10ans", 600000,
                "Dk-Thies", "Partail-Time", true, "cv6.pdf", "photo6.jpg", p2, ad6);


        chauffeurRepository.save(ch1);
        chauffeurRepository.save(ch2);
        chauffeurRepository.save(ch3);
        chauffeurRepository.save(ch4);
        chauffeurRepository.save(ch5);
        chauffeurRepository.save(ch6);
        chauffeurRepository.save(ch7);
        chauffeurRepository.save(ch8);
        chauffeurRepository.save(ch9);
        chauffeurRepository.save(ch10);
        chauffeurRepository.save(ch11);
        chauffeurRepository.save(ch12);
        chauffeurRepository.save(ch13);
        chauffeurRepository.save(ch14);
        chauffeurRepository.save(ch15);
        chauffeurRepository.save(ch16);

        Role useRole = new Role(RoleName.ROLE_USER);
        Role roleGestionnaire = new Role(RoleName.ROLE_GESTIONNAIRE);
        Role roleManager = new Role(RoleName.ROLE_MANAGER);
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(useRole);
        roleRepository.save(roleGestionnaire);
        roleRepository.save(roleManager);
        roleRepository.save(adminRole);


        Utilisateur kals = new Utilisateur();
        kals.setId(1L);
        kals.setUsername("Kalilou");
        kals.setName("Kalilou Badji");
        kals.setEmail("kaliloubadji@yahoo.fr");
        kals.setActive(true);
        kals.setPassword(bCryptPasswordEncoder.encode("sunuchauffeur@2022"));
        utilisateurRepository.save(kals);
        utilisateurService.addRoleToUser("Kalilou", RoleName.ROLE_MANAGER);

        Utilisateur laye = new Utilisateur();
        laye.setId(2L);
        laye.setUsername("Abdoulaye");
        laye.setName("Abdoulaye Kalel");
        laye.setEmail("kalel.abdoulaye@gmail.com");
        laye.setActive(true);
        laye.setPassword(bCryptPasswordEncoder.encode("sunuchauffeur@2022"));
        utilisateurRepository.save(laye);
        utilisateurService.addRoleToUser("Abdoulaye", RoleName.ROLE_MANAGER);

        Utilisateur admin = new Utilisateur();
        admin.setId(3L);
        admin.setUsername("Admin");
        admin.setName("Admin diallo");
        admin.setEmail("admin@gmail.com");
        admin.setActive(true);
        admin.setPassword(bCryptPasswordEncoder.encode("admin1234"));
        utilisateurRepository.save(admin);
        utilisateurService.addRoleToUser("Admin", RoleName.ROLE_ADMIN);


        */

    }

}

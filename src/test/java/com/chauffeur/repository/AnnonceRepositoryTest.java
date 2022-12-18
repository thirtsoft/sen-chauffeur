package com.chauffeur.repository;

import com.chauffeur.models.Annonce;
import com.chauffeur.models.Permis;
import com.chauffeur.models.Recruteur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnnonceRepositoryTest {

    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private RecruteurRepository recruteurRepository;

    @Test
    @Rollback(false)
    public void testCreateAnnonce() {
        Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);

        Long recId = (long) 1;
        Recruteur recruteur = recruteurRepository.findById(recId).orElse(null);

        long reference = 45612L;
        String lieuPoste = "Tairou";
        Annonce annonceDto = new Annonce();
        annonceDto.setReference(reference);
        annonceDto.setLieuPoste(lieuPoste);
        // 	annonceDto.setPermis(permis); annonceDto.setRecruteur(recruteur);

        Annonce annonceDtoResult = annonceRepository.save(annonceDto);

        assertNotNull(annonceDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateAnnonce() {
        Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);

        Long recId = (long) 1;
        Recruteur recruteur = recruteurRepository.findById(recId).orElse(null);

        long reference = 9456123L;
        String lieuPoste = "Tairou";
        String salaire = "120000fcfa";
        Annonce annonceDto = new Annonce();
        annonceDto.setReference(reference);
        annonceDto.setLieuPoste(lieuPoste);
        annonceDto.setSalaire(salaire);
        annonceDto.setStatus("ENCOURS");
        // 	annonceDto.setPermis(permis); annonceDto.setRecruteur(recruteur);

        annonceRepository.save(annonceDto);


        long referenceChauffeur = 9456123L;
        annonceDto.setReference(referenceChauffeur);
//        annonceDto.setId(2L);
        Annonce annonceUpdate = annonceRepository.save(annonceDto);

        assertThat(annonceUpdate.getReference()).isEqualTo(referenceChauffeur);

    }

    @Test
    public void testFindById() {
        Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);

        Long recId = (long) 1;
        Recruteur recruteur = recruteurRepository.findById(recId).orElse(null);

        long reference = 894561L;
        String lieuPoste = "Tairou";
        String salaire = "120000fcfa";
        Annonce annonceDto = new Annonce();
        annonceDto.setReference(reference);
        annonceDto.setLieuPoste(lieuPoste);
        annonceDto.setSalaire(salaire);
        annonceDto.setStatus("ENCOURS");
        //  	annonceDto.setPermis(permis); annonceDto.setRecruteur(recruteur);

        Annonce annonceDtoResult = annonceRepository.save(annonceDto);

        boolean isExistAnnonce = annonceRepository.findById(annonceDtoResult.getId()).isPresent();

        assertTrue(isExistAnnonce);

    }

    @Test
    public void testFindAll() {
        Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);

        Long recId = (long) 1;
        Recruteur recruteur = recruteurRepository.findById(recId).orElse(null);

        long reference = 789456L;
        String lieuPoste = "Tairou";
        String salaire = "120000fcfa";
        Annonce annonceDto = new Annonce();
        annonceDto.setReference(reference);
        annonceDto.setLieuPoste(lieuPoste);
        annonceDto.setSalaire(salaire);
        annonceDto.setStatus("ENCOURS");
        //  	annonceDto.setPermis(permis); annonceDto.setRecruteur(recruteur);

        annonceRepository.save(annonceDto);

        long reference2 = 7894562147L;
        Annonce annonceDto2 = new Annonce();
        annonceDto2.setReference(reference2);
        annonceDto.setStatus("VALIDEE");
        //   	annonceDto.setPermis(permis); annonceDto.setRecruteur(recruteur);
        annonceRepository.save(annonceDto);

        List<Annonce> notifications = annonceRepository.findAll();

        assertThat(notifications).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long catId = (long) 1;
        Permis permis = permisRepository.findById(catId).orElse(null);

        Long recId = (long) 1;
        Recruteur recruteur = recruteurRepository.findById(recId).orElse(null);

        long reference = 25634L;
        String lieuPoste = "Tairou";
        String salaire = "120000fcfa";
        Annonce annonceDto = new Annonce();
        annonceDto.setReference(reference);
        annonceDto.setLieuPoste(lieuPoste);
        annonceDto.setSalaire(salaire);
        annonceDto.setStatus("ENCOURS");
        //  	annonceDto.setPermis(permis); annonceDto.setRecruteur(recruteur);

        Annonce annonceDtoResult = annonceRepository.save(annonceDto);

        boolean isExistBeforeDelete = annonceRepository.findById(annonceDtoResult.getId()).isPresent();

        annonceRepository.deleteById(annonceDtoResult.getId());

        boolean notExistAfterDelete = annonceRepository.findById(annonceDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }


}

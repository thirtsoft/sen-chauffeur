package com.chauffeur.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.chauffeur.models.Permis;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PermisRepositoryTest {
	
	@Autowired
    private PermisRepository permisRepository;

    @Test
    @Rollback(false)
    public void testCreatePermis() {
  
    	Permis permis = new Permis(1L, "Poids Lours", 10);
    	
    	Permis permisResult = permisRepository.save(permis);
    	
        assertNotNull(permisResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdatePermis() {
    	Permis permis = new Permis(1L, "Poids Lours", 10);
    	permisRepository.save(permis);
    	
    	String designation = "A";
    	permis.setId(2L);
    	permis.setDesignation(designation);
    	
    	Permis permisUpdate = permisRepository.save(permis);
    	
    	 assertThat(permisUpdate.getDesignation()).isEqualTo(designation);
    	 assertThat(permisUpdate.getDesignation()).isEqualTo(permis.getDesignation());

    }

    @Test
    public void testFindById() {
    	Permis permis = new Permis(1L, "Poids Lours", 10);
    	
    	Permis permisResult = permisRepository.save(permis);

        boolean isExistPermis = permisRepository.findById(permisResult.getId()).isPresent();
         
        assertTrue(isExistPermis);
    	
    }

   
    @Test
    public void testFindAll() {
    	
    	Permis permis = new Permis(1L, "Poids Lours", 10);
    	permisRepository.save(permis);
    	
    	Permis permis2 = new Permis(2L, "Poids Lours", 10);
    	permisRepository.save(permis2);
    	
        List<Permis> permisList = permisRepository.findAll();

        assertThat(permisList).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
    	Permis permis = new Permis(1L, "Poids Lours", 10);

    	Permis categoryDtoResult2 = permisRepository.save(permis);

        boolean isExistBeforeDelete = permisRepository.findById(categoryDtoResult2.getId()).isPresent();

        permisRepository.deleteById(categoryDtoResult2.getId());

        boolean notExistAfterDelete = permisRepository.findById(categoryDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);
    }

}

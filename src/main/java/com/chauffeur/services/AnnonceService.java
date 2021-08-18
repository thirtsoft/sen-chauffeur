package com.chauffeur.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.dto.ChauffeurDto;

public interface AnnonceService {
	
	AnnonceDto save(AnnonceDto annonceDto);
	
	AnnonceDto update(Long idAnnonce, AnnonceDto annonceDto);

	AnnonceDto findById(Long id);
	
	AnnonceDto findByReference(String reference);

    List<AnnonceDto> findAll();
    
    List<AnnonceDto> findListAnnonceByKeyword(String keyword);
    
    List<AnnonceDto> findListAnnonceByLibelle(String libelle);
    
    List<AnnonceDto> findListAnnonceByPermis(Long pId);
    
    BigDecimal countNumbersOfAnnonces();

    Page<AnnonceDto> findAnnonceByPageable(Pageable pageable);
    
    Page<AnnonceDto> findAnnonceByPermisByPageable(Long permisId, Pageable pageable);
    
  

    void delete(Long id);

}

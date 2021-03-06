package com.chauffeur.services;

import java.util.List;

import com.chauffeur.dto.PermisDto;

public interface PermisService {
	
	PermisDto save(PermisDto permisDto);
	
	PermisDto update(Long idPermis, PermisDto permisDto);

	PermisDto findById(Long id);

    List<PermisDto> findAll();
    
    List<PermisDto> findByPermisByIdDesc();

    void delete(Long id);

}

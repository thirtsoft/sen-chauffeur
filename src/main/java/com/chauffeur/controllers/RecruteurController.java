package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.RecruteurApi;
import com.chauffeur.dto.RecruteurDto;
import com.chauffeur.services.RecruteurService;

@RestController
public class RecruteurController implements RecruteurApi {
	
	private RecruteurService recruteurService;

	@Autowired
	public RecruteurController(RecruteurService recruteurService) {
		this.recruteurService = recruteurService;
	}
	@Override
	public ResponseEntity<RecruteurDto> save(RecruteurDto recruteurDto) {
		return ResponseEntity.ok(recruteurService.save(recruteurDto));
	}

	@Override
	public ResponseEntity<RecruteurDto> findById(Long id) {
		return ResponseEntity.ok(recruteurService.findById(id));
	}

	@Override
	public List<RecruteurDto> findAll() {
		return recruteurService.findAll();
	}

	@Override
	public void delete(Long id) {
		recruteurService.delete(id);
		
	}

}

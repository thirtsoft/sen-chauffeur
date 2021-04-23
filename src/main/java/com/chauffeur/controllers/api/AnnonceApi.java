package com.chauffeur.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.AnnonceDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface AnnonceApi {
	
	@PostMapping(value = APP_ROOT + "/annonces/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AnnonceDto> save(@RequestBody AnnonceDto annonceDto);

	@GetMapping(value = APP_ROOT + "/annonces/{idAnnonce}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<AnnonceDto> findById(@PathVariable("idAnnonce") Long id);

	@GetMapping(value = APP_ROOT + "/annonces/all", produces = MediaType.APPLICATION_JSON_VALUE)
	List<AnnonceDto> findAll();

	@DeleteMapping(value = APP_ROOT + "/annonces/delete/{idAnnonce}")
	void delete(@PathVariable("idAnnonce") Long id);

}

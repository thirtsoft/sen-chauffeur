package com.chauffeur.controllers.api;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.chauffeur.dto.PermisDto;
import com.chauffeur.dto.TarifDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface TarifApi {
	
	@PostMapping(value = APP_ROOT + "/tarifs/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un Tarif",
			notes = "Cette méthode permet d'ajouter un nouveau Tarif", response = TarifDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "La Tarif a été crée"),
			@ApiResponse(code = 400, message = "Aucun Tarif  ajoutée")

	})
	ResponseEntity<TarifDto> save(@RequestBody TarifDto tarifDto);
	
	@PutMapping(value = APP_ROOT + "/tarifs/update/{idTarif}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modofier un Tarif  par ID",
			notes = "Cette méthode permet de modifier un Tarif par ID", response = TarifDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le Tarif a été modifié"),
			@ApiResponse(code = 400, message = "Aucun Tarif  modifié")

	})
	ResponseEntity<TarifDto> update(@PathVariable("idTarif") Long idTarif, @RequestBody TarifDto tarifDto);

	@GetMapping(value = APP_ROOT + "/tarifs/findById/{idTarif}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher un Tarif par son ID",
			notes = "Cette méthode permet d'afficher un Tarif par son ID",  response = TarifDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La Tarif a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun Tarif")

	})
	ResponseEntity<TarifDto> findById(@PathVariable("idTarif") Long idTarif);

	@GetMapping(value = APP_ROOT + "/tarifs/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Tarif",
			notes = "Cette méthode permet d'afficher la liste des Tarif",
			responseContainer = "List<TarifDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Tarif a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Tarif")

	})
	ResponseEntity<List<TarifDto>> findAll();
	
	@GetMapping(value = APP_ROOT + "/tarifs/searchTarifsOrderByIdDesc",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Tarifs par ordre Déccroissante",
			notes = "Cette méthode permet d'afficher la liste des Tarifs par ordre Déccroissante",
			responseContainer = "List<TarifDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Tarif a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Tarif")

	})
	ResponseEntity<List<TarifDto>> getdAllTarifsOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/tarifs/searchTarifsByAnnonce/{pId}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Tarifs par ID de l'Annonce",
			notes = "Cette méthode permet d'afficher la liste des Tarifs par ID de l'Annonce",
			responseContainer = "List<TarifDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Tarif a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Tarif")

	})
	ResponseEntity<List<TarifDto>> getListTarifByAnnonce(@PathVariable("pId") Long pId);
	 
	@GetMapping(value = APP_ROOT + "/tarifs/searchTarifByPageables", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Tarifs par pages",
			notes = "Cette méthode permet d'afficher la liste des Tarifs par pages",
			responseContainer = "List<TarifDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Tarif a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Tarif")

	})
	Page<TarifDto> getListTarifByPageable(@RequestParam(name = "page") int page, 
			@RequestParam(name = "size") int size);
	
	
	@GetMapping(value = APP_ROOT + "/tarifs/searchTarifByAnnoncePageables", 
	    		produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Tarifs par ID de l'Annonce par pages",
			notes = "Cette méthode permet d'afficher la liste des Tarifs par ID de l'Annonce par pages",
			responseContainer = "List<TarifDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Tarif a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Tarif")

	})
	Page<TarifDto> getTarifByAnnoncePageables(
	    		@RequestParam("id") Long annonceId, 
	    		@RequestParam(name = "page") int page,
	    		@RequestParam(name = "size") int size);
	
	@DeleteMapping(value = APP_ROOT + "/tarifs/delete/{idTarif}")
	@ApiOperation(value = "Supprimer un Tarif par son ID",
			notes = "Cette méthode permet de supprimer un Tarif par son ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le Tarif a été supprimé")

	})
	void delete(@PathVariable("idTarif") Long idTarif);

}

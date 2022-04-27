package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.math.BigDecimal;
import java.util.List;

import com.chauffeur.dto.JetonDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.NewsleterDto;

public interface NewsleterApi {
	
	@PostMapping(value = APP_ROOT + "/newsleters/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un Newsleter",
			notes = "Cette méthode permet d'ajouter un nouveau Newsleter", response = NewsleterDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Le Newsleter a été crée"),
			@ApiResponse(code = 400, message = "Aucun Newsleter  ajoutée")

	})
	ResponseEntity<NewsleterDto> createNewsleter(@RequestBody NewsleterDto newsleterDto);
	
	@PutMapping(value = APP_ROOT + "/newsleters/update/{idNewsleter}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modofier un Newsleter  par ID",
			notes = "Cette méthode permet de modifier un Newsleter par ID", response = NewsleterDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le Newsleter a été modifié"),
			@ApiResponse(code = 400, message = "Aucun Newsleter  modifié")

	})
	ResponseEntity<NewsleterDto> updateNewsleter(@PathVariable("idNewsleter") Long idNewsleter, 
			@RequestBody NewsleterDto newsleterDto);
	
	@GetMapping(value = APP_ROOT + "/newsleters/findById/{idNewsleter}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher un Newsleter par son ID",
			notes = "Cette méthode permet d'afficher un Newsleter par son ID",  response = NewsleterDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La Newsleters a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun Newsleters")

	})
	ResponseEntity<NewsleterDto> findById(@PathVariable("idNewsleter") Long idNewsleter);
	
	@GetMapping(value = APP_ROOT + "/newsleters/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Newsleters",
			notes = "Cette méthode permet d'afficher la liste des Newsleters", responseContainer = "List<NewsleterDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Newsleters a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Newsleters")

	})
	ResponseEntity<List<NewsleterDto>> getAllNewsleters();
		    
	@GetMapping(value = APP_ROOT + "/newsleters/searchNewsleterOrderByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Newsleter par ordre Décroissante",
			notes = "Cette méthode permet d'afficher la liste des Newsleter par ordre Décroissante", responseContainer = "List<NewsleterDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Newsleters a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Newsleters")

	})
	ResponseEntity<List<NewsleterDto>> getListOfNewsletersOrderByIdDesc();
		
	@GetMapping(value = APP_ROOT + "/newsleters/NumbersOfNewsleters")
	@ApiOperation(value = "Décompter le nombre de Newsleter",
			notes = "Cette méthode permet de compter et d'afficher le nombre total de Newsleters",
			response = JetonDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le nombre de Newsleter"),
			@ApiResponse(code = 400, message = "nombre null")

	})
    BigDecimal getNumbersOfNewsleters();

	@DeleteMapping(value = APP_ROOT + "/newsleters/delete/{idNewsleter}")
	@ApiOperation(value = "Supprimer un Newsleter par son ID",
			notes = "Cette méthode permet de supprimer un Newsleter par son ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le Newsleter a été supprimé")

	})
	void delete(@PathVariable("idNewsleter") Long idNewsleter);
	

}

package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.math.BigDecimal;
import java.util.List;

import com.chauffeur.dto.HistoriqueAnnonceDto;
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

import com.chauffeur.dto.HistoriqueLoginDto;

public interface HistoriqueLoginApi {
	
	@PostMapping(value = APP_ROOT + "/historiqueLogins/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer une HistoriqueLogin",
			notes = "Cette méthode permet d'enregistrer une HistoriqueLogin", response = HistoriqueLoginDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "L'HistoriqueLogin a été crée"),
			@ApiResponse(code = 400, message = "Aucun HistoriqueLogin  crée / modifié")

	})
	ResponseEntity<HistoriqueLoginDto> save(@RequestBody HistoriqueLoginDto historiqueLoginDto);
	
	@PutMapping(value = APP_ROOT + "/historiqueLogins/update/{idHistoriqueLogin}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modofier une HistoriqueLogin  par ID",
			notes = "Cette méthode permet de modifier une HistoriqueLogin par ID", response = HistoriqueLoginDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'HistoriqueLogin a été modifié"),
			@ApiResponse(code = 400, message = "Aucun HistoriqueLogin  modifié")

	})
	ResponseEntity<HistoriqueLoginDto> update(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin, 
			@RequestBody HistoriqueLoginDto historiqueLoginDto);
	
	@GetMapping(value = APP_ROOT + "/historiqueLogins/findById/{idHistoriqueLogin}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher une HistoriqueLogin par ID",
			notes = "Cette méthode permet de rechercher une HistoriqueLogin par son ID", response = HistoriqueLoginDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'HistoriqueLogin a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun HistoriqueLogin  avec cet ID")

	})
	ResponseEntity<HistoriqueLoginDto> findById(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin);
	
	@GetMapping(value = APP_ROOT + "/historiqueLogins/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des HistoriqueLogins",
			notes = "Cette méthode permet d'afficher la liste des HistoriqueLogins",
			responseContainer = "List<HistoriqueLoginDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des HistoriqueLogins a été trouvé"),
			@ApiResponse(code = 400, message = "Liste vide")

	})
	ResponseEntity<List<HistoriqueLoginDto>> findAll();
		    
	@GetMapping(value = APP_ROOT + "/historiqueLogins/searchHistoriqueLoginByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des HistoriqueLogins par order Décroissante",
			notes = "Cette méthode permet d'afficher la liste des HistoriqueLogins par order Décroissante",
			responseContainer = "List<HistoriqueLoginDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des HistoriqueLogins a été trouvé"),
			@ApiResponse(code = 400, message = "Liste vide")

	})
	ResponseEntity<List<HistoriqueLoginDto>> getAllHistoriqueLoginOrderByIdDesc();
		
	@GetMapping(value = APP_ROOT + "/historiqueLogins/NumbersOfHistoriqueLogins")
	@ApiOperation(value = "Décompter le nombre d'HistoriqueLogin",
			notes = "Cette méthode permet de compter et d'afficher le nombre total d'HistoriqueLogins",
			response = HistoriqueLoginDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le nombre d'HistoriqueLogin"),
			@ApiResponse(code = 400, message = "nombre null")

	})
    BigDecimal getNumbersOfHistoriqueLogins();

	@DeleteMapping(value = APP_ROOT + "/historiqueLogins/delete/{idHistoriqueLogin}")
	@ApiOperation(value = "Supprimer une HistoriqueLogin par son ID",
			notes = "Cette méthode permet de supprimer une HistoriqueLogin par son ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'HistoriqueLogin a été supprimé")

	})
	void delete(@PathVariable("idHistoriqueLogin") Long idHistoriqueLogin);
	
}

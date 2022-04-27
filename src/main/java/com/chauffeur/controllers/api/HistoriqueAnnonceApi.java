package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.math.BigDecimal;
import java.util.List;

import com.chauffeur.dto.AnnonceDto;
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

import com.chauffeur.dto.HistoriqueAnnonceDto;

public interface HistoriqueAnnonceApi {
	
	@PostMapping(value = APP_ROOT + "/historiqueAnnonces/create", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer une HistoriqueAnnonce",
			notes = "Cette méthode permet d'enregistrer une HistoriqueAnnonce", response = HistoriqueAnnonceDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "L'HistoriqueAnnonce a été crée"),
			@ApiResponse(code = 400, message = "Aucun HistoriqueAnnonce  crée / modifié")

	})
	ResponseEntity<HistoriqueAnnonceDto> save(@RequestBody HistoriqueAnnonceDto historiqueAnnonceDto);
	
	@PutMapping(value = APP_ROOT + "/historiqueAnnonces/update/{idHistoriqueAnnonce}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modofier une HistoriqueAnnonce  par ID",
			notes = "Cette méthode permet de modifier une HistoriqueAnnonce par ID", response = HistoriqueAnnonceDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'HistoriqueAnnonce a été modifié"),
			@ApiResponse(code = 400, message = "Aucun HistoriqueAnnonce  modifié")

	})
	ResponseEntity<HistoriqueAnnonceDto> update(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce, 
			@RequestBody HistoriqueAnnonceDto historiqueAnnonceDto);
	
	@GetMapping(value = APP_ROOT + "/historiqueAnnonces/findById/{idHistoriqueAnnonce}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher une HistoriqueAnnonce par ID",
			notes = "Cette méthode permet de rechercher une HistoriqueAnnonce par son ID", response = HistoriqueAnnonceDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'HistoriqueAnnonce a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun HistoriqueAnnonce  avec cet ID")

	})
	ResponseEntity<HistoriqueAnnonceDto> findById(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce);
	
	@GetMapping(value = APP_ROOT + "/historiqueAnnonces/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des HistoriqueAnnonces",
			notes = "Cette méthode permet d'afficher la liste des HistoriqueAnnonces", responseContainer = "List<HistoriqueAnnonceDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des HistoriqueAnnonce a été trouvé"),
			@ApiResponse(code = 400, message = "Liste vide")

	})
	ResponseEntity<List<HistoriqueAnnonceDto>> findAll();
		    
	@GetMapping(value = APP_ROOT + "/historiqueAnnonces/searchHistoriqueAnnonceByIdDesc", 
			 produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des HistoriqueAnnonces par order Décroissante",
			notes = "Cette méthode permet d'afficher la liste des HistoriqueAnnonces par order Décroissante",
			responseContainer = "List<HistoriqueAnnonceDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des HistoriqueAnnonce a été trouvé"),
			@ApiResponse(code = 400, message = "Liste vide")

	})
	ResponseEntity<List<HistoriqueAnnonceDto>> getListHistoriqueAnnoncesOrderByIdDesc();
		
	@GetMapping(value = APP_ROOT + "/historiqueAnnonces/NumbersOfhistoriqueAnnonces")
	@ApiOperation(value = "Décompter le nombre d'HistoriqueAnnonces",
			notes = "Cette méthode permet de compter et d'afficher le nombre total d'HistoriqueAnnonces", response = HistoriqueAnnonceDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le nombre d'HistoriqueAnnonces"),
			@ApiResponse(code = 400, message = "Aucun liste HistoriqueAnnonces")

	})
    BigDecimal getNumbersOfhistoriqueAnnonces();

	@DeleteMapping(value = APP_ROOT + "/historiqueAnnonces/delete/{idHistoriqueAnnonce}")
	@ApiOperation(value = "Supprimer une HistoriqueAnnonces par son ID",
			notes = "Cette méthode permet de supprimer une HistoriqueAnnonces par son ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'HistoriqueAnnonces a été supprimé")

	})
	void delete(@PathVariable("idHistoriqueAnnonce") Long idHistoriqueAnnonce);

}

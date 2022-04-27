package com.chauffeur.controllers.api;

import java.util.List;

import com.chauffeur.dto.NotificationDto;
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

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.PermisDto;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface PermisApi {
	
	@PostMapping(value = APP_ROOT + "/permis/create",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un Permis",
			notes = "Cette méthode permet d'ajouter un nouveau Permis", response = PermisDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "La Permis a été crée"),
			@ApiResponse(code = 400, message = "Aucun Permis  ajoutée")

	})
	ResponseEntity<PermisDto> save(@RequestBody PermisDto permisDto);
	
	@PutMapping(value = APP_ROOT + "/permis/update/{idPermis}",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modofier un Permis  par ID",
			notes = "Cette méthode permet de modifier un Permis par ID", response = PermisDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le Permis a été modifié"),
			@ApiResponse(code = 400, message = "Aucun Permis  modifié")

	})
	ResponseEntity<PermisDto> update(@PathVariable("idPermis") Long id, @RequestBody PermisDto permisDto);

	@GetMapping(value = APP_ROOT + "/permis/findById/{idPermis}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher un Permis par son ID",
			notes = "Cette méthode permet d'afficher un Permis par son ID",  response = PermisDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La Permis a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun Permis")

	})
	ResponseEntity<PermisDto> findById(@PathVariable("idPermis") Long id);

	@GetMapping(value = APP_ROOT + "/permis/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Permis",
			notes = "Cette méthode permet d'afficher la liste des Permis",
			responseContainer = "List<PermisDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Permis a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Permis")

	})
	ResponseEntity<List<PermisDto>> findAll();
	
	@GetMapping(value = APP_ROOT + "/permis/searchPermisOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Permis par ordre Décroissante",
			notes = "Cette méthode permet d'afficher la liste des Permis par ordre Décroissante",
			responseContainer = "List<PermisDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Permis a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Permis")

	})
	ResponseEntity<List<PermisDto>> getdAllPermisOrderByIdDesc();

	@DeleteMapping(value = APP_ROOT + "/permis/delete/{idPermis}")
	@ApiOperation(value = "Supprimer un Permis par son ID",
			notes = "Cette méthode permet de supprimer un Permis par son ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le Permis a été supprimé")

	})
	void delete(@PathVariable("idPermis") Long id);

}

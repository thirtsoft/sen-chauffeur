package com.chauffeur.controllers.api;

import static com.chauffeur.utils.Constants.APP_ROOT;

import java.math.BigDecimal;
import java.util.List;

import com.chauffeur.dto.JetonDto;
import com.chauffeur.dto.NewsleterDto;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.chauffeur.dto.NotificationDto;

public interface NotificationApi {
	
	@PostMapping(value = APP_ROOT + "/notifications/create",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un Notification",
			notes = "Cette méthode permet d'ajouter un nouveau Notification", response = NotificationDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "La Notification a été crée"),
			@ApiResponse(code = 400, message = "Aucun Notification  ajoutée")

	})
	ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto);
	
	@PostMapping(value = APP_ROOT + "/notifications/createWithChauffeur/{idNotification}", 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un Notification à un chauffeur",
			notes = "Cette méthode permet d'ajouter un nouveau Notification à un chauffeur", response = NotificationDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "La Notification a été crée"),
			@ApiResponse(code = 400, message = "Aucun Notification  ajoutée")

	})
	ResponseEntity<NotificationDto> saveNoteToChauffeur(@PathVariable("idNotification") Long id, 
			@RequestBody NotificationDto notificationDto);
	
	@PutMapping(value = APP_ROOT + "/notifications/update/{idNotification}",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Modofier un Notification  par ID",
			notes = "Cette méthode permet de modifier un NotificationD par ID", response = NotificationDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le Notification a été modifié"),
			@ApiResponse(code = 400, message = "Aucun Notification  modifié")

	})
	ResponseEntity<NotificationDto> update(@PathVariable("idNotification") Long id, @RequestBody NotificationDto notificationDto);

	@PostMapping(value = APP_ROOT + "/notifications/createRatingToChauffeur",
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un Notification à un chauffeur par un employeur",
			notes = "Cette méthode permet d'ajouter un nouveau Notification à un chauffeur par un employeur",
			response = NotificationDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "La Notification a été crée"),
			@ApiResponse(code = 400, message = "Aucun Notification  ajoutée")

	})
	ResponseEntity<NotificationDto> saveRating(@RequestBody NotificationDto notificationDto, 
			@RequestParam Long idChauff, @RequestParam Long id);


	@GetMapping(value = APP_ROOT + "/notifications/findById/{idNotification}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher un Notification par son ID",
			notes = "Cette méthode permet d'afficher un Notification par son ID",  response = NotificationDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La Notification a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun Notification")

	})
	ResponseEntity<NotificationDto> findById(@PathVariable("idNotification") Long id);

	@GetMapping(value = APP_ROOT + "/notifications/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Notifications",
			notes = "Cette méthode permet d'afficher la liste des Notifications", responseContainer = "List<NotificationDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Notifications a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Notifications")

	})
	ResponseEntity<List<NotificationDto>> findAll();
	
	@GetMapping(value = APP_ROOT + "/notifications/searchAllNotificationsOrderByIdDesc",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des Notifications par ordre Décroissante",
			notes = "Cette méthode permet d'afficher la liste des Notifications par ordre Décroissante",
			responseContainer = "List<NotificationDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Notifications a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Notifications")

	})
    ResponseEntity<List<NotificationDto>> getAllNotificationsOrderByIdDesc();
	
	@GetMapping(value = APP_ROOT + "/notifications/searchTop3RatingOrderByCreatedDateDesc",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des trois dernieres Notifications",
			notes = "Cette méthode permet d'afficher la liste des trois dernieres Notifications",
			responseContainer = "List<NotificationDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Notifications a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Notifications")

	})
	ResponseEntity<List<NotificationDto>> getTop3ByOrderByCreatedDateDesc();
	
	@GetMapping(value = APP_ROOT + "/notifications/searchTop4RatingOrderByCreatedDateDescByChauffeurId/{idChauff}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Afficher la liste des quatre dernieres Notifications d'un chauffeur",
			notes = "Cette méthode permet d'afficher la liste des quatre dernieres Notifications d'un chauffeur",
			responseContainer = "List<NotificationDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des Notifications a été trouvé"),
			@ApiResponse(code = 400, message = "Aucun liste Notifications")

	})
	ResponseEntity<List<NotificationDto>> getTop4ByOrderByCreatedDateDescByProductId(@PathVariable("idChauff") Long id);
	
	@GetMapping(value = APP_ROOT + "/notifications/countNumberOfNotificationByProductId/{idChauff}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Décompter le nombre de Notifications d'un chauffeur",
			notes = "Cette méthode permet de compter et d'afficher le nombre total de Notifications d'un chauffeur",
			response = NotificationDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le nombre de Notifications"),
			@ApiResponse(code = 400, message = "nombre null")

	})
	BigDecimal countNumberOfNotificationByChauffeurId(@PathVariable("idChauff") String chauffRef);

	@GetMapping(value = APP_ROOT + "/notifications/countNumberOfNotification",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Décompter le nombre de Notifications",
			notes = "Cette méthode permet de compter et d'afficher le nombre total de Notifications",
			response = NotificationDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le nombre de Notifications"),
			@ApiResponse(code = 400, message = "nombre null")

	})
    BigDecimal countNumberOfNotification();
	
	@DeleteMapping(value = APP_ROOT + "/notifications/delete/{idNotification}")
	@ApiOperation(value = "Supprimer un Notification par son ID",
			notes = "Cette méthode permet de supprimer un Notification par son ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Le Notification a été supprimé")

	})
	void delete(@PathVariable("idNotification") Long id);

}

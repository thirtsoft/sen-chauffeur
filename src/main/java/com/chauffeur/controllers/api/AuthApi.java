package com.chauffeur.controllers.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chauffeur.dto.EmailDto;
import com.chauffeur.message.request.LoginForm;
import com.chauffeur.message.request.SignUpForm;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.chauffeur.utils.Constants.APP_ROOT;

public interface AuthApi {


	@PostMapping(value = APP_ROOT + "/auth/authenticated")
	@ApiOperation(value = "Se connecter",
			notes = "Cette méthode permet à un utilisateur de se connecter", response = LoginForm.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "utilisateur a été envoyé / modifié"),
			@ApiResponse(code = 400, message = "Aucun connection utilisateur  établie")
	})
	ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm);


	@PostMapping(value = APP_ROOT + "/auth/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create Account",
			notes = "Cette méthode permet à un utilisateur de créer un compte", response = SignUpForm.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Le compte a été crée"),
			@ApiResponse(code = 400, message = "Aucun Compte  crée / modifié")
	})
	ResponseEntity<?> signUp(@Valid @RequestBody SignUpForm signUpForm);

}

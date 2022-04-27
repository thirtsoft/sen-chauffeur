package com.chauffeur.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.HistoriqueLoginApi;
import com.chauffeur.dto.HistoriqueLoginDto;
import com.chauffeur.services.HistoriqueLoginService;

@RestController
@CrossOrigin
public class HistoriqueLoginController implements HistoriqueLoginApi {
	
	private HistoriqueLoginService  historiqueLoginService;
	
	@Autowired
	public HistoriqueLoginController(HistoriqueLoginService historiqueLoginService) {
		this.historiqueLoginService = historiqueLoginService;
	}

	@Override
	public ResponseEntity<HistoriqueLoginDto> save(HistoriqueLoginDto historiqueLoginDto) {
		HistoriqueLoginDto historiqueLoginResultDto = historiqueLoginService.save(historiqueLoginDto);
		return new ResponseEntity<>(historiqueLoginResultDto, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<HistoriqueLoginDto> update(Long idHistoriqueLogin, HistoriqueLoginDto historiqueLoginDto) {
		historiqueLoginDto.setId(idHistoriqueLogin);
		HistoriqueLoginDto historiqueLoginResultDto = historiqueLoginService.save(historiqueLoginDto);
		return new ResponseEntity<>(historiqueLoginResultDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<HistoriqueLoginDto> findById(Long idHistoriqueLogin) {
		HistoriqueLoginDto historiqueLoginResultDto = historiqueLoginService.findById(idHistoriqueLogin);
		return new ResponseEntity<>(historiqueLoginResultDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<HistoriqueLoginDto>> findAll() {
		List<HistoriqueLoginDto> historiqueLoginDtos = historiqueLoginService.findAll();
		return new ResponseEntity<>(historiqueLoginDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<HistoriqueLoginDto>> getAllHistoriqueLoginOrderByIdDesc() {
		List<HistoriqueLoginDto> historiqueLoginDtos = historiqueLoginService.findHistoriqueLoginByOrderByIdDesc();
		return new ResponseEntity<>(historiqueLoginDtos, HttpStatus.OK);
	}

	@Override
	public BigDecimal getNumbersOfHistoriqueLogins() {
		return historiqueLoginService.countNumbersOfHistoriqueLogins();
	}

	@Override
	public void delete(Long idHistoriqueLogin) {
		historiqueLoginService.delete(idHistoriqueLogin);
		
	}

}

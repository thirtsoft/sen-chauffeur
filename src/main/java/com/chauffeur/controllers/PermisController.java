package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.PermisApi;
import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.dto.PermisDto;
import com.chauffeur.services.PermisService;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://sunuchauffeur.com")
@RestController
public class PermisController implements PermisApi{
	
	private PermisService permisService;

	@Autowired
	public PermisController(PermisService permisService) {
		this.permisService = permisService;
	}
	
	@Override
	public ResponseEntity<PermisDto> save(PermisDto permisDto) {
		PermisDto permisDtoResultat = permisService.save(permisDto);
		return new ResponseEntity<>(permisDtoResultat, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<PermisDto> update(Long id, PermisDto permisDto) {
		permisDto.setId(id);
		PermisDto permisDtoResultat = permisService.save(permisDto);
		return new ResponseEntity<>(permisDtoResultat, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PermisDto> findById(Long id) {
		PermisDto permisDtoResultat = permisService.findById(id);
		return new ResponseEntity<>(permisDtoResultat, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PermisDto>> findAll() {
		List<PermisDto> permisDtoList = permisService.findAll();
		return new ResponseEntity<>(permisDtoList, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<List<PermisDto>> getdAllPermisOrderByIdDesc() {
		List<PermisDto> permisDtoList = permisService.findByPermisByIdDesc();
	    return new ResponseEntity<>(permisDtoList, HttpStatus.OK);

	}
	
	@Override
	public void delete(Long id) {
		permisService.delete(id);
	}

}

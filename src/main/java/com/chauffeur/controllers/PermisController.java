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

@RestController
@CrossOrigin
public class PermisController implements PermisApi{
	
	private PermisService permisService;

	@Autowired
	public PermisController(PermisService permisService) {
		this.permisService = permisService;
	}
	
	@Override
	public ResponseEntity<PermisDto> save(PermisDto permisDto) {
		return ResponseEntity.ok(permisService.save(permisDto));
	}
	
	@Override
	public ResponseEntity<PermisDto> update(Long id, PermisDto permisDto) {
		permisDto.setId(id);
		return ResponseEntity.ok(permisService.save(permisDto));
	}

	@Override
	public ResponseEntity<PermisDto> findById(Long id) {
		return ResponseEntity.ok(permisService.findById(id));
	}

	@Override
	public List<PermisDto> findAll() {
		return permisService.findAll();
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

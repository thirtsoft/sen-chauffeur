package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.TarifApi;
import com.chauffeur.dto.PermisDto;
import com.chauffeur.dto.TarifDto;
import com.chauffeur.services.TarifService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TarifController implements TarifApi {
	
	private TarifService tarifService;
	
	@Autowired
	public TarifController(TarifService tarifService) {
		this.tarifService = tarifService;
	}

	@Override
	public ResponseEntity<TarifDto> save(TarifDto tarifDto) {
		TarifDto tarifDtoResult = tarifService.save(tarifDto);
		return new ResponseEntity<>(tarifDtoResult, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<TarifDto> update(Long idTarif, TarifDto tarifDto) {
		tarifDto.setId(idTarif);
		TarifDto tarifDtoResult = tarifService.save(tarifDto);
		return new ResponseEntity<>(tarifDtoResult, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TarifDto> findById(Long idTarif) {
		TarifDto tarifDtoResult = tarifService.findById(idTarif);
		return new ResponseEntity<>(tarifDtoResult, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TarifDto>> findAll() {
		List<TarifDto> tarifDtoList = tarifService.findAll();
		return new ResponseEntity<>(tarifDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TarifDto>> getdAllTarifsOrderByIdDesc() {
		List<TarifDto> tarifDtoList = tarifService.findByTarifByIdDesc();
		return new ResponseEntity<>(tarifDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TarifDto>> getListTarifByAnnonce(Long pId) {
		List<TarifDto> tarifDtoList = tarifService.findListTarifDtoByAnnonce(pId);
		return new ResponseEntity<>(tarifDtoList, HttpStatus.OK);
	}

	@Override
	public Page<TarifDto> getListTarifByPageable(int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return tarifService.findTarifByPageable(pageable);
	}

	@Override
	public Page<TarifDto> getTarifByAnnoncePageables(Long tarifId, int page, int size) {
		final Pageable pageable = PageRequest.of(page, size);
        return tarifService.findTarifByAnnonceByPageable(tarifId, pageable);
	}


	@Override
	public void delete(Long idTarif) {
		tarifService.delete(idTarif);

	}

	
	
}

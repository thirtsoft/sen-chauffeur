package com.chauffeur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.chauffeur.controllers.api.AddresseApi;
import com.chauffeur.dto.AddresseDto;
import com.chauffeur.dto.AnnonceDto;
import com.chauffeur.services.AddressService;

@RestController
@CrossOrigin
public class AddresseController implements AddresseApi {
	
	private AddressService addressService;
	
	@Autowired
	public AddresseController(AddressService addressService) {
		this.addressService = addressService;
	}

	@Override
	public ResponseEntity<AddresseDto> save(AddresseDto addresseDto) {
		AddresseDto addresseDtoResultat = addressService.save(addresseDto);
		return new ResponseEntity<>(addresseDtoResultat, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<AddresseDto> update(Long id, AddresseDto addresseDto) {
		addresseDto.setId(id);
		AddresseDto addresseDtoResultat = addressService.save(addresseDto);
		return new ResponseEntity<>(addresseDtoResultat, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<AddresseDto> findById(Long id) {
		AddresseDto addresseDtoResultat = addressService.findById(id);
		return new ResponseEntity<>(addresseDtoResultat, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<AddresseDto>> getAllAddresses() {
		List<AddresseDto> addresseDtoList = addressService.findAll();
        return new ResponseEntity<>(addresseDtoList, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<AddresseDto>> getdAllAddressOrderByIdDesc() {
		List<AddresseDto> addresseDtoList = addressService.findByAddresseByIdDesc();
        return new ResponseEntity<>(addresseDtoList, HttpStatus.OK);
	}
	
	@Override
	public void delete(Long id) {
		addressService.delete(id);
		
	}

	
}

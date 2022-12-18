package com.chauffeur.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.AddresseDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Addresse;
import com.chauffeur.repository.AddresseRepository;
import com.chauffeur.services.AddressService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class AddressServiceImpl implements AddressService {
	
	@Autowired
    private final AddresseRepository addressRepository;

    public AddressServiceImpl(AddresseRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddresseDto save(AddresseDto addresseDto) {

        return AddresseDto.fromEntityToDto(
        		addressRepository.save(
        				AddresseDto.fromDtoToEntity(addresseDto)
                )
        );
    }
    
    @Override
	public AddresseDto update(Long idAddress, AddresseDto addresseDto) {
    	if (!addressRepository.existsById(idAddress)) {
            throw new ResourceNotFoundException("Address not found");
        }

        Optional<Addresse> addresse = addressRepository.findById(idAddress);

        if (!addresse.isPresent()) {
            throw new ResourceNotFoundException("Address not found");
        }

        AddresseDto addressDtoResult = AddresseDto.fromEntityToDto(addresse.get());
        addressDtoResult.setReference(addresseDto.getReference());
        addressDtoResult.setCodePostal(addresseDto.getCodePostal());
        addressDtoResult.setPays(addresseDto.getPays());
        addressDtoResult.setVille(addresseDto.getVille());

        return AddresseDto.fromEntityToDto(
        		addressRepository.save(
        				AddresseDto.fromDtoToEntity(addressDtoResult)
                )
        );
	}

    @Override
    public AddresseDto findById(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return null;
        }

        Optional<Addresse> addresse = addressRepository.findById(id);

        return Optional.of(AddresseDto.fromEntityToDto(addresse.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Addresse avec l'Id = " + id + "n'a été trouvé")
        );
    }

    
    @Override
    public List<AddresseDto> findAll() {
        return addressRepository.findAll().stream()
                .map(AddresseDto::fromEntityToDto)
                .collect(Collectors.toList());
    }
    
    @Override
	public List<AddresseDto> findByAddresseByIdDesc() {
    	 return addressRepository.findAddresseByOrderByIdDesc().stream()
                 .map(AddresseDto::fromEntityToDto)
                 .collect(Collectors.toList());
	}

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Addresse Id is null");
            return;
        }
        addressRepository.deleteById(id);

    }

	

	

}

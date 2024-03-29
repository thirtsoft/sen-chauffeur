package com.chauffeur.services.impl;

import com.chauffeur.dto.ChauffeurDto;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Chauffeur;
import com.chauffeur.repository.ChauffeurRepository;
import com.chauffeur.services.ChauffeurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ChauffeurServiceImpl implements ChauffeurService {

    @Autowired
    private final ChauffeurRepository chauffeurRepository;

    public ChauffeurServiceImpl(ChauffeurRepository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }

    @Override
    public ChauffeurDto save(ChauffeurDto chauffeurDto) {

        return ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                        ChauffeurDto.fromDtoToEntity(chauffeurDto)
                )
        );
    }

    @Override
    public ChauffeurDto update(Long idChauffeur, ChauffeurDto chauffeurDto) {
        if (!chauffeurRepository.existsById(idChauffeur)) {
            throw new ResourceNotFoundException("Chauffeur not found");
        }

        Optional<Chauffeur> chauffeur = chauffeurRepository.findById(idChauffeur);

        if (!chauffeur.isPresent()) {
            throw new ResourceNotFoundException("Chauffeur not found");
        }

        ChauffeurDto chauffeurDtoResult = ChauffeurDto.fromEntityToDto(chauffeur.get());
        chauffeurDtoResult.setReference(chauffeurDto.getReference());
        chauffeurDtoResult.setFirstName(chauffeurDto.getFirstName());
        chauffeurDtoResult.setLastName(chauffeurDto.getLastName());
        chauffeurDtoResult.setSexe(chauffeurDto.getSexe());
        chauffeurDtoResult.setEmail(chauffeurDto.getEmail());
        chauffeurDtoResult.setPhoneChauffeur(chauffeurDto.getPhoneChauffeur());
        chauffeurDtoResult.setPhotoChauffeur(chauffeurDto.getPhotoChauffeur());
        chauffeurDtoResult.setPretentionSalaire(chauffeurDto.getPretentionSalaire());
        chauffeurDtoResult.setSelected(chauffeurDto.isSelected());
        chauffeurDtoResult.setCvChauffeur(chauffeurDto.getCvChauffeur());
        chauffeurDtoResult.setNbreAnneeExperience(chauffeurDto.getNbreAnneeExperience());
        chauffeurDtoResult.setDisponibity(chauffeurDto.getDisponibity());
        chauffeurDtoResult.setDescription(chauffeurDto.getDescription());
        chauffeurDtoResult.setMobilite(chauffeurDto.getMobilite());
        chauffeurDtoResult.setDateInscription(chauffeurDto.getDateInscription());
        chauffeurDtoResult.setObtainedDate(chauffeurDto.getObtainedDate());
        chauffeurDtoResult.setPermisDto(chauffeurDto.getPermisDto());
        chauffeurDtoResult.setAddresseDto(chauffeurDto.getAddresseDto());

        return ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                        ChauffeurDto.fromDtoToEntity(chauffeurDtoResult)
                )
        );
    }

    @Override
    public ChauffeurDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Chauffeur> chauffeur = chauffeurRepository.findById(id);

        return Optional.of(ChauffeurDto.fromEntityToDto(chauffeur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun chauffeur avec l'Id = " + id + "n'a été trouvé")
        );
    }


    @Override
    public List<ChauffeurDto> findAll() {
        return chauffeurRepository.findAll().stream()
                .map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChauffeurDto> findByChauffeurByIdDesc() {
        return chauffeurRepository.findChauffeurByOrderByIdDesc().stream()
                .map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChauffeurDto saveChauffeurWithFiles(String chauffeurDto, MultipartFile photoChauffeur,
                                               MultipartFile cvChauffeur) throws IOException {
        ChauffeurDto chauffeurDtoMapper = new ObjectMapper().readValue(chauffeurDto, ChauffeurDto.class);
        System.out.println(chauffeurDtoMapper);

        chauffeurDtoMapper.setPhotoChauffeur(photoChauffeur.getOriginalFilename());

        chauffeurDtoMapper.setCvChauffeur(cvChauffeur.getOriginalFilename());

        return ChauffeurDto.fromEntityToDto(
                chauffeurRepository.save(
                        ChauffeurDto.fromDtoToEntity(chauffeurDtoMapper)
                )
        );
    }

    @Override
    public ChauffeurDto findByReference(String reference) {
        if (!StringUtils.hasLength(reference)) {
            log.error("Annonce REFERENCE is null");
        }

        Optional<Chauffeur> chauffeur = chauffeurRepository.findChauffeurByReference(reference);

        return Optional.of(ChauffeurDto.fromEntityToDto(chauffeur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Annonce avec l'Id = " + reference + "n'a été trouvé")
        );
    }

    @Override
    public List<ChauffeurDto> findChauffeurByDisponibility(String disponility) {
        return chauffeurRepository.findChauffeurByDisponibility('%' + disponility + '%').stream()
                .map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ChauffeurDto> findListChauffeurBySelected() {
        return chauffeurRepository.findChauffeurBySelected().stream()
                .map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ChauffeurDto> findListChauffeurByKeyword(String keyword) {
        if (keyword == null) {
            log.error("Article not found");
        }

        return chauffeurRepository.findChauffeurByKeyword(keyword).stream()
                .map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ChauffeurDto> findListChauffeurByPermis(Long pId) {
        return chauffeurRepository.findChauffeurByPermis(pId).stream()
                .map(ChauffeurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }


    @Override
    public BigDecimal countNumbersOfChauffeurs() {
        return chauffeurRepository.countNumberOfChauffeurs();
    }

    @Override
    public Page<ChauffeurDto> findChauffeurByPageable(Pageable pageable) {
        return chauffeurRepository.findAllChauffeurByPageable(pageable)
                .map(ChauffeurDto::fromEntityToDto);
    }

    @Override
    public Page<ChauffeurDto> findChauffeurByKeywordByPageable(String mc, Pageable pageable) {
        if (mc == null) {
            log.error("Chauffeur not found");
        }

        return chauffeurRepository.findChauffeurByKeywordByPageable(mc, pageable)
                .map(ChauffeurDto::fromEntityToDto);

    }

    @Override
    public Page<ChauffeurDto> findChauffeurByLocalityPageables(Long addId, Pageable pageable) {
        return chauffeurRepository.findChauffeurByLocalityPageables(addId, pageable)
                .map(ChauffeurDto::fromEntityToDto);
    }

    @Override
    public Page<ChauffeurDto> findChauffeurByPermisPageables(Long permisId, Pageable pageable) {
        return chauffeurRepository.findChauffeurByPermisPageables(permisId, pageable)
                .map(ChauffeurDto::fromEntityToDto);
    }


    @Override
    public List<?> countNumberOfChauffeurByMonth() {
        return chauffeurRepository.countNumberOfChauffeurByMonth()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> countNumberOfChauffeurByYear() {
        return chauffeurRepository.countNumberOfChauffeurByYear()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("chauffeur Id is null");
            return;
        }

        chauffeurRepository.deleteById(id);

    }

    @Override
    public List<ChauffeurDto> getAllChauffeurDtos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return chauffeurRepository.findAll(pageable)
                .map(ChauffeurDto::fromEntityToDto).getContent();
    }

    @Override
    public List<ChauffeurDto> getAllChauffeurDtosByIdAddress(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return chauffeurRepository.findByAddresseId(id, pageable)
                .map(ChauffeurDto::fromEntityToDto).getContent();
    }

    @Override
    public List<ChauffeurDto> getAllChauffeurDtosByKey(String disponibility, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return chauffeurRepository.findByDisponibityContaining(disponibility, pageable)
                .map(ChauffeurDto::fromEntityToDto).getContent();
    }

    @Override
    public long getAllChauffeurDtosSize() {
        return chauffeurRepository.count();
    }

    @Override
    public long getChauffeurDtosByAddressIdLength(Long id) {
        return chauffeurRepository.getChauffeurLengthByAddressId(id);
    }

    @Override
    public long getChauffeurDtosSizeByKey(String disponibility) {
        return chauffeurRepository.getChauffeurSizeByKey(disponibility);
    }


}

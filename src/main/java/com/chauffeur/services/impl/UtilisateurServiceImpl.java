package com.chauffeur.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.enumeration.RoleName;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.models.Role;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.RoleRepository;
import com.chauffeur.repository.UtilisateurRepository;
import com.chauffeur.services.UtilisateurService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
	
	private final RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
    		 						RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        return UtilisateurDto.fromEntityToDto(
        		utilisateurRepository.save(
                		UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );
    }
    
    @Override
    public UtilisateurDto update(Long id, UtilisateurDto utilisateurDto) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("State not found");
        }

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (!optionalUtilisateur.isPresent()) {
            throw new ResourceNotFoundException("Utilisateur not found");
        }

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(optionalUtilisateur.get());

        utilisateurDtoResult.setName(utilisateurDto.getName());
        utilisateurDtoResult.setUsername(utilisateurDto.getUsername());
        utilisateurDtoResult.setEmail(utilisateurDto.getEmail());
        utilisateurDtoResult.setMobile(utilisateurDto.getMobile());
        utilisateurDtoResult.setAddressRecruteur(utilisateurDto.getAddressRecruteur());
        utilisateurDtoResult.setVilleRecruteur(utilisateurDto.getVilleRecruteur());
        utilisateurDtoResult.setNomEntreprise(utilisateurDto.getNomEntreprise());
        utilisateurDtoResult.setSecteurActivite(utilisateurDto.getSecteurActivite());
        utilisateurDtoResult.setWebsite(utilisateurDto.getWebsite());
        utilisateurDtoResult.setInformation(utilisateurDto.getInformation());
        utilisateurDtoResult.setPassword(bCryptPasswordEncoder.encode(utilisateurDto.getPassword()));
        utilisateurDtoResult.setActive(utilisateurDto.isActive());

        return UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDtoResult)
                )
        );

    }
    
    @Override
    public boolean updateUsernameOfUtilisateurByUsername(String username, String newUsername) {
        Optional<Utilisateur> existsUser = utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
            user.setUsername(newUsername);
            this.utilisateurRepository.save(user);
            return true;
        }

        return false;
    }
    
    @Override
    public boolean updateUsernameOfUtilisateurByUserId(String id, String newUsername) {
        Optional<Utilisateur> existsUser = utilisateurRepository.findById(Long.valueOf(id));
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
            user.setUsername(newUsername);
            this.utilisateurRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateCustomerPasswordByUsername(String username, String oldPass, String newPass) {
        Optional<Utilisateur> existsUser = this.utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();

            if (passwordEncoder.matches(oldPass, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPass));
                this.utilisateurRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateCustomerPasswordByUserId(String id, String oldPass, String newPass) {
        Optional<Utilisateur> existsUser = utilisateurRepository.findById(Long.valueOf(id));
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();

            if (passwordEncoder.matches(oldPass, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPass));
                this.utilisateurRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateCustomerProfileByUsername(String username, String name, String newUsername, String email, String mobile) {
        Optional<Utilisateur> existsUser = this.utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
            user.setName(name);
            user.setUsername(newUsername);
            user.setEmail(email);
            user.setMobile(mobile);

            utilisateurRepository.save(user);

            return true;

        }
        return false;
    }

    @Override
    public UtilisateurDto activatedUser(String isActive, String id) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(Long.valueOf(id));
        Utilisateur utilisateur = optionalUtilisateur.get();
        utilisateur.setActive(Boolean.valueOf(isActive));

        return UtilisateurDto.fromEntityToDto(utilisateurRepository.save(utilisateur));

    }

    @Override
    public BigDecimal countNumberOfRecruteurs() {
        return utilisateurRepository.countNumberOfRecruteurs();
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Utilisateur> utiliOptional = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntityToDto(utiliOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun chauffeur avec l'Id = " + id + "n'a été trouvé")
        );
    }
    
    @Override
    public UtilisateurDto findByUsername(String username) {
        if (username == null) {
            log.error("Utilisateur with this username is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username);

        return Optional.of(UtilisateurDto.fromEntityToDto(utilisateur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Utilisateur avec l'Id = " + username + "n'a été trouvé")
        );
    }

    
    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<UtilisateurDto> findByOrderByIdDesc() {
        return utilisateurRepository.findByOrderByIdDesc().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDto> findNewsRecruteursByOrderByIdDesc() {
        return utilisateurRepository.findUtilisateursByOrderByIdDesc().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("chauffeur Id is null");
            return;
        }
        utilisateurRepository.deleteById(id);

    }

	@Override
	public void addRoleToUser(String username, RoleName roleName) {
		Role role = roleRepository.findByName(roleName).get();

        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).get();

        utilisateur.getRoles().add(role);
		
	}

}

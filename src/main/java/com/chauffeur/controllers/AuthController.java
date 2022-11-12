package com.chauffeur.controllers;

import com.chauffeur.controllers.api.AuthApi;
import com.chauffeur.dto.HistoriqueLoginDto;
import com.chauffeur.dto.UtilisateurDto;
import com.chauffeur.enumeration.RoleName;
import com.chauffeur.exceptions.ResourceNotFoundException;
import com.chauffeur.message.request.LoginForm;
import com.chauffeur.message.request.SignUpForm;
import com.chauffeur.message.response.JwtResponse;
import com.chauffeur.models.Role;
import com.chauffeur.models.Utilisateur;
import com.chauffeur.repository.RoleRepository;
import com.chauffeur.repository.UtilisateurRepository;
import com.chauffeur.security.jwt.JwtsProvider;
import com.chauffeur.security.service.UserPrinciple;
import com.chauffeur.services.HistoriqueLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController implements AuthApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtsProvider jwtsProvider;

    @Autowired
    private HistoriqueLoginService historiqueLoginService;

    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginForm) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtsProvider.generatedJwtToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userPrinciple.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(userPrinciple.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntityToDto(utilisateur);
        HistoriqueLoginDto historiqueLoginDto = new HistoriqueLoginDto();
        historiqueLoginDto.setUtilisateurDto(utilisateurDto);
        historiqueLoginDto.setAction("Connection");
        historiqueLoginDto.setCreatedDate(new Date());
        historiqueLoginService.save(historiqueLoginDto);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userPrinciple.getId(),
                userPrinciple.getUsername(),
                userPrinciple.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpForm) {
        if (utilisateurRepository.existsByUsername(signUpForm.getUsername())) {
            throw new ResourceNotFoundException("Fail -> Error: Username is already taken!");
        }
        if (utilisateurRepository.existsByEmail(signUpForm.getEmail())) {
            throw new ResourceNotFoundException("Error: Email is already in use!");
        }
        // Create new user's account
        Utilisateur utilisateur = new Utilisateur(
                signUpForm.getName(),
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()
                )
        );

        String[] strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = (roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
            roles.add(userRole);
        }

        for (String role : strRoles) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
                    break;

                case "gestionnaire":
                    roles.add(roleRepository.findByName(RoleName.ROLE_GESTIONNAIRE).get());
                    break;

                case "manager":
                    roles.add(roleRepository.findByName(RoleName.ROLE_MANAGER).get());
                    break;

                case "user":
                    roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
                    break;

                default:
                    return ResponseEntity.badRequest().body("Specified role not found");

            }
        }

        utilisateur.setRoles(roles);
        utilisateur.setActive(true);
        utilisateur.setDateInscription(new Date());
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));

    }


}

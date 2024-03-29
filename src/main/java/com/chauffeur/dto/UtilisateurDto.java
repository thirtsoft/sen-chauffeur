package com.chauffeur.dto;

import com.chauffeur.models.Role;
import com.chauffeur.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {

    private Long id;

    @NotBlank(message = "Nom et Prenom sont obligatory")
    private String name;

    private String username;

    private String mobile;

    @NotBlank(message = "Email est obligatory")
    private String email;

    private String nomEntreprise;

    private String addressRecruteur;

    private String website;

    private String secteurActivite;

    private String villeRecruteur;

    private String information;

    @NotBlank(message = "Password est obligatory")
    private String password;

    private String photo = "avatar.jpg";

    private String subject;

    private String message;

    private boolean isActive;

    private Date dateInscription;

    private Set<Role> roles = new HashSet<>();

    public UtilisateurDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public static UtilisateurDto fromEntityToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .name(utilisateur.getName())
                .username(utilisateur.getUsername())
                .mobile(utilisateur.getMobile())
                .email(utilisateur.getEmail())
                .addressRecruteur(utilisateur.getAddressRecruteur())
                .villeRecruteur(utilisateur.getVilleRecruteur())
                .nomEntreprise(utilisateur.getNomEntreprise())
                .secteurActivite(utilisateur.getSecteurActivite())
                .website(utilisateur.getWebsite())
                .photo(utilisateur.getPhoto())
                .information(utilisateur.getInformation())
                .isActive(utilisateur.isActive())
                .dateInscription(utilisateur.getDateInscription())
                .password(utilisateur.getPassword())
                .roles(utilisateur.getRoles())
                .build();

    }

    public static Utilisateur fromDtoToEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setName(utilisateurDto.getName());
        utilisateur.setUsername(utilisateurDto.getUsername());
        utilisateur.setMobile(utilisateurDto.getMobile());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setAddressRecruteur(utilisateurDto.getAddressRecruteur());
        utilisateur.setVilleRecruteur(utilisateurDto.getVilleRecruteur());
        utilisateur.setNomEntreprise(utilisateurDto.getNomEntreprise());
        utilisateur.setSecteurActivite(utilisateurDto.getSecteurActivite());
        utilisateur.setWebsite(utilisateurDto.getWebsite());
        utilisateur.setInformation(utilisateurDto.getInformation());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setActive(utilisateur.isActive());
        utilisateur.setDateInscription(utilisateurDto.getDateInscription());
        utilisateur.setPassword(utilisateurDto.getPassword());
        utilisateur.setRoles(utilisateur.getRoles());

        return utilisateur;
    }


}

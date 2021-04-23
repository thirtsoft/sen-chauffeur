package com.chauffeur.dto;

import com.chauffeur.models.Utilisateur;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurDto {
	
	private long id;

    private String username;

    private String mobile;

    private String email;

    private String password;
	
	public static UtilisateurDto fromEntityToDto(Utilisateur utilisateur) {
		if (utilisateur == null) {
			return null;
		}
		
		return UtilisateurDto.builder()
				.username(utilisateur.getUsername())
				.mobile(utilisateur.getMobile())
				.email(utilisateur.getEmail())
				.password(utilisateur.getPassword())
				.build();
		
	}
	
	public static Utilisateur fromDtoToEntity(UtilisateurDto utilisateurDto) {
		if (utilisateurDto == null) {
			return null;
		}
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setUsername(utilisateurDto.getUsername());
		utilisateur.setMobile(utilisateurDto.getMobile());
		utilisateur.setEmail(utilisateurDto.getEmail());
		utilisateur.setPassword(utilisateurDto.getPassword());
	
	
		return utilisateur;
	}


}

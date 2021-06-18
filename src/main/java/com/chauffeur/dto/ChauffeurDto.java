package com.chauffeur.dto;

import com.chauffeur.models.Chauffeur;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChauffeurDto {
	
	private Long id;
	
	private String reference;

	private String firstName;

	private String lastName;
	
	private String sexe;
	
	private double addressActuel;

	private String email;
	
	private String phoneChauffeur;
	
	private int nbreAnneeExperience;

	private double pretentionSalaire;
	
	private String cvChauffeur;
	
	private String mobilite;

	private String photoChauffeur;
	
	private PermisDto permisDto;
	
	public static ChauffeurDto fromEntityToDto(Chauffeur chauffeur) {
		if (chauffeur == null) {
			return null;
		}
		
		return ChauffeurDto.builder()
				.id(chauffeur.getId())
				.reference(chauffeur.getReference())
				.firstName(chauffeur.getFirstName())
				.lastName(chauffeur.getLastName())
				.sexe(chauffeur.getSexe())
				.email(chauffeur.getEmail())
				.phoneChauffeur(chauffeur.getPhoneChauffeur())
				.nbreAnneeExperience(chauffeur.getNbreAnneeExperience())
				.pretentionSalaire(chauffeur.getPretentionSalaire())
				.cvChauffeur(chauffeur.getCvChauffeur())
				.mobilite(chauffeur.getMobilite())
				.photoChauffeur(chauffeur.getPhotoChauffeur())
				.permisDto(PermisDto.fromEntityToDto(chauffeur.getPermis()))
				.build();
		
	}
	
	public static Chauffeur fromDtoToEntity(ChauffeurDto chauffeurDto) {
		if (chauffeurDto == null) {
			return null;
		}
		Chauffeur chauffeur = new Chauffeur();
		chauffeur.setReference(chauffeurDto.getReference());
		chauffeur.setFirstName(chauffeurDto.getFirstName());
		chauffeur.setLastName(chauffeurDto.getLastName());
		chauffeur.setSexe(chauffeurDto.getSexe());
		chauffeur.setEmail(chauffeurDto.getEmail());
		chauffeur.setPhoneChauffeur(chauffeurDto.getPhoneChauffeur());
		chauffeur.setNbreAnneeExperience(chauffeurDto.getNbreAnneeExperience());
		chauffeur.setPretentionSalaire(chauffeurDto.getPretentionSalaire());
		chauffeur.setCvChauffeur(chauffeurDto.getCvChauffeur());
		chauffeur.setMobilite(chauffeurDto.getMobilite());
		chauffeur.setPhotoChauffeur(chauffeurDto.getPhotoChauffeur());
		chauffeur.setPermis(PermisDto.fromDtoToEntity(chauffeurDto.getPermisDto()));
		
		return chauffeur;
	}


}


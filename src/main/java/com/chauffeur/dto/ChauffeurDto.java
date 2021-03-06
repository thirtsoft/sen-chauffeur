package com.chauffeur.dto;

import java.util.Date;

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
	
	private String disponibity;
	
	private boolean selected;

	private String email;
	
	private String phoneChauffeur;
	
	private String nbreAnneeExperience;

	private double pretentionSalaire;
	
	private String cvChauffeur;
	
	private String mobilite;

	private String photoChauffeur;
	
	private Date dateInscription;
	
	private String subject;
	
	private String message;
	
	private PermisDto permisDto;
	
	private AddresseDto addresseDto;
	
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
				.disponibity(chauffeur.getDisponibity())
				.mobilite(chauffeur.getMobilite())
				.selected(chauffeur.isSelected())
				.cvChauffeur(chauffeur.getCvChauffeur())
				.photoChauffeur(chauffeur.getPhotoChauffeur())
				.dateInscription(chauffeur.getDateInscription())
				.permisDto(PermisDto.fromEntityToDto(chauffeur.getPermis()))
				.addresseDto(AddresseDto.fromEntityToDto(chauffeur.getAddresse()))
				.build();
		
	}
	
	public static Chauffeur fromDtoToEntity(ChauffeurDto chauffeurDto) {
		if (chauffeurDto == null) {
			return null;
		}
		
		Chauffeur chauffeur = new Chauffeur();
		chauffeur.setId(chauffeurDto.getId());
		chauffeur.setReference(chauffeurDto.getReference());
		chauffeur.setFirstName(chauffeurDto.getFirstName());
		chauffeur.setLastName(chauffeurDto.getLastName());
		chauffeur.setSexe(chauffeurDto.getSexe());
		chauffeur.setEmail(chauffeurDto.getEmail());
		chauffeur.setPhoneChauffeur(chauffeurDto.getPhoneChauffeur());
		chauffeur.setNbreAnneeExperience(chauffeurDto.getNbreAnneeExperience());
		chauffeur.setPretentionSalaire(chauffeurDto.getPretentionSalaire());
		chauffeur.setDisponibity(chauffeurDto.getDisponibity());
		chauffeur.setMobilite(chauffeurDto.getMobilite());
		chauffeur.setSelected(chauffeurDto.isSelected());
		chauffeur.setCvChauffeur(chauffeurDto.getCvChauffeur());
		chauffeur.setPhotoChauffeur(chauffeurDto.getPhotoChauffeur());
		chauffeur.setDateInscription(chauffeurDto.getDateInscription());
		chauffeur.setPermis(PermisDto.fromDtoToEntity(chauffeurDto.getPermisDto()));
		chauffeur.setAddresse(AddresseDto.fromDtoToEntity(chauffeurDto.getAddresseDto()));
		
		return chauffeur;
	}


}


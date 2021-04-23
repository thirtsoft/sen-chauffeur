package com.chauffeur.dto;

import java.time.LocalDate;

import com.chauffeur.enumeration.StatusAnnonce;
import com.chauffeur.models.Annonce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnnonceDto {
	
	private long id;

	private String reference;
	
	private String lieuPoste;

	private String salaire;

	private LocalDate dateCandidature;
	
	private LocalDate dateCloture;

	private String modeCandidature;
	
    private StatusAnnonce statusAnnonce;

	private PermisDto permisDto;

	private RecruteurDto recruteurDto;
	
	public static AnnonceDto fromEntityToDto(Annonce annonce) {
		if (annonce == null) {
			return null;
		}
		
		return AnnonceDto.builder()
				.reference(annonce.getReference())
				.lieuPoste(annonce.getLieuPoste())
				.salaire(annonce.getSalaire())
				.modeCandidature(annonce.getModeCandidature())
				.dateCandidature(annonce.getDateCandidature())
				.dateCloture(annonce.getDateCloture())
				.statusAnnonce(annonce.getStatusAnnonce())
				.build();
		
	}
	
	public static Annonce fromDtoToEntity(AnnonceDto annonceDto) {
		if (annonceDto == null) {
			return null;
		}
		Annonce annonce = new Annonce();
		annonce.setReference(annonceDto.getReference());
		annonce.setLieuPoste(annonceDto.getLieuPoste());
		annonce.setSalaire(annonceDto.getSalaire());
		annonce.setModeCandidature(annonceDto.getModeCandidature());
		annonce.setDateCandidature(annonceDto.getDateCandidature());
		annonce.setDateCloture(annonceDto.getDateCloture());
		annonce.setStatusAnnonce(annonceDto.getStatusAnnonce());
		
		return annonce;
	}

}

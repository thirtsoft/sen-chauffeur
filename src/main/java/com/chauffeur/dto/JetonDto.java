package com.chauffeur.dto;

import java.util.Date;

import com.chauffeur.models.Jeton;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JetonDto {
	
	private Long id;

	@NotNull(message = "montant shouldn't be null")
	private float montant;

	private String etat;
	    
	private Date createdDate;

	private UtilisateurDto utilisateurDto;
	
	public static JetonDto fromEntityToDto(Jeton jeton) {
		if (jeton == null) {
			return null;
		}
		
		return JetonDto.builder()
				.id(jeton.getId())
				.montant(jeton.getMontant())
				.etat(jeton.getEtat())
				.createdDate(jeton.getCreatedDate())
				.utilisateurDto(UtilisateurDto.fromEntityToDto(jeton.getUtilisateur()))
				.build();
		
	}
	
	public static Jeton fromDtoToEntity(JetonDto jetonDto) {
		if (jetonDto == null) {
			return null;
		}
		
		Jeton jeton = new Jeton();
		jeton.setId(jetonDto.getId());
		jeton.setMontant(jetonDto.getMontant());
		jeton.setEtat(jetonDto.getEtat());
		jeton.setCreatedDate(jetonDto.getCreatedDate());
		jeton.setUtilisateur(UtilisateurDto.fromDtoToEntity(jetonDto.getUtilisateurDto()));
		
		return jeton;
	}



}

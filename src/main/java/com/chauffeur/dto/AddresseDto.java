package com.chauffeur.dto;


import com.chauffeur.models.Addresse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddresseDto {
	
	private Long id;

	@NotNull(message = "reference ne peut pas etre null")
	private String reference;

	private String codePostal;

	@NotNull(message = "ville ne peut pas etre null")
	private String ville;

	@NotNull(message = "ville ne peut pas etre null")
	private String pays;
	
	public static AddresseDto fromEntityToDto(Addresse addresse) {
		if (addresse == null) {
			return null;
		}
		
		return AddresseDto.builder()
				.id(addresse.getId())
				.reference(addresse.getReference())
				.codePostal(addresse.getCodePostal())
				.ville(addresse.getVille())
				.pays(addresse.getPays())
				.build();
		
	}
	
	public static Addresse fromDtoToEntity(AddresseDto addresseDto) {
		if (addresseDto == null) {
			return null;
		}
		Addresse addresse = new Addresse();
		addresse.setId(addresseDto.getId());
		addresse.setReference(addresseDto.getReference());
		addresse.setCodePostal(addresseDto.getCodePostal());
		addresse.setVille(addresseDto.getVille());
		addresse.setPays(addresseDto.getPays());
		
		return addresse;
	}
	

}

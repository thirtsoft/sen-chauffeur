
package com.chauffeur.dto;
import com.chauffeur.models.Tarif;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarifDto {
	
	private Long id;

	@NotNull(message = "reference should not null")
	private String reference;

	@NotNull(message = "montantTarif should not null")
	private String montantTarif;

	@NotNull(message = "description should not null")
	private String description;
	
	private TypeAnnonceDto typeAnnonceDto;

	
	public static TarifDto fromEntityToDto(Tarif tarif) {
		if (tarif == null) {
			return null;
		}
		
		return TarifDto.builder()
				.id(tarif.getId())
				.reference(tarif.getReference())
				.montantTarif(tarif.getMontantTarif())
				.description(tarif.getDescription())
				.typeAnnonceDto(TypeAnnonceDto.fromEntityToDto(tarif.getTypeAnnonce()))
				.build();
		
	}
	
	public static Tarif fromDtoToEntity(TarifDto tarifDto) {
		if (tarifDto == null) {
			return null;
		}
		Tarif tarif = new Tarif();
		tarif.setId(tarifDto.getId());
		tarif.setReference(tarifDto.getReference());
		tarif.setMontantTarif(tarifDto.getMontantTarif());
		tarif.setDescription(tarifDto.getDescription());
		tarif.setTypeAnnonce(TypeAnnonceDto.fromDtoToEntity(tarifDto.getTypeAnnonceDto()));
		
		return tarif;
	}

}

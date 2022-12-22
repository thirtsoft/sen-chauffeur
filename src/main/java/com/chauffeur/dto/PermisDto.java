package com.chauffeur.dto;

import com.chauffeur.models.Permis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermisDto {
	
	private Long id;

	@NotNull(message = "designation should not null")
	private String designation;

	@NotNull(message = "validite should not null")
	private int validite;

	public static PermisDto fromEntityToDto(Permis permis) {
		if (permis == null) {
			return null;
		}
		
		return PermisDto.builder()
				.id(permis.getId())
				.designation(permis.getDesignation())
				.validite(permis.getValidite())
				.build();
		
	}
	
	public static Permis fromDtoToEntity(PermisDto permisDto) {
		if (permisDto == null) {
			return null;
		}
		Permis permis = new Permis();
		permis.setId(permisDto.getId());
		permis.setDesignation(permisDto.getDesignation());
		permis.setValidite(permisDto.getValidite());

		return permis;
	}


}

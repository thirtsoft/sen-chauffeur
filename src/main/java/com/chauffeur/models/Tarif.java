package com.chauffeur.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarif")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarif implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "reference should not null")
	@Column(name = "reference", length = 50)
	private String reference;

	@NotNull(message = "montantTarif should not null")
	@Column(name = "montant", length = 100)
	private String montantTarif;
	
	@Column(name = "description")
	@Lob
	private String description;

	@ManyToOne
	@JoinColumn(name = "typeAnnonceId", nullable = false)
	private TypeAnnonce typeAnnonce;

}

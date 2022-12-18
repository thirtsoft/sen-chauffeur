package com.chauffeur.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Addresse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "reference ne peut pas etre null")
	@Column(name = "reference", length = 50)
	private String reference;

	@Column(name = "codePostal", unique = true, length = 70)
	private String codePostal;

	@NotNull(message = "ville shouldn't be null")
	@Column(name = "ville", unique = true, length = 100)
	private String ville;

	@NotNull(message = "pays shouldn't be null")
	@Column(name = "pays", length = 70)
	private String pays;



}

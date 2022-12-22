package com.chauffeur.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permis")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permis implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "designation should not null")
	@Column(name = "designation", unique = true,  length = 100)
	private String designation;

	@NotNull(message = "validite should not null")
	@Column(name = "validite", length = 30)
	private int validite;


}

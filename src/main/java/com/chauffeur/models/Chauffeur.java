package com.chauffeur.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chauffeur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chauffeur implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "reference", length = 50)
	private String reference;

	@Column(name = "firstName", length = 90)
	private String firstName;

	@Column(name = "lastName", length = 70)
	private String lastName;
	
	@Column(name = "sexe", length = 70)
	private String sexe;
	
	@Column(name = "addressActuel", length = 90)
	private String addressActuel;

	@Column(name = "email", length = 50)
	private String email;
	
	@Column(name = "phoneChauffeur", length = 30)
	private String phoneChauffeur;
	
	@Column(name = "experience", length = 50)
	private int nbreAnneeExperience;
	
	@Column(name = "pretentionSalaire", length = 90)
	private double pretentionSalaire;
	
	@Column(name = "mobilite", length = 100)
	private String mobilite;

	@Column(name = "cvChauffeur", length = 30)
	private String cvChauffeur;
	
	@Column(name = "photoChauffeur", length = 30)
	private String photoChauffeur;
	
	@ManyToOne
	@JoinColumn(name = "permId", nullable = false)
	private Permis permis;

}

package com.chauffeur.models;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "jeton")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jeton implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "montant")
    private float montant;

    @Column(name = "etat")
    private String etat;
    
    @Column(name = "createdDate")
    private Date createdDate;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;
    

}

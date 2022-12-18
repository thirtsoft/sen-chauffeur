package com.chauffeur.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "nbreEtoile post should not null")
    @Column(name = "nbreEtoile", length = 60)
    private float nbreEtoile;

    @NotNull(message = "observation post should not null")
    @Column(name = "observation")
    @Lob
    private String observation;
    
    @Column(name = "createdDate", length = 80)
    private Date createdDate;
    
    @ManyToOne
    @JoinColumn(name = "chauffId")
    private Chauffeur chauffeur;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;
    
    public Notification(Long id, float nbreEtoile, String observation, Chauffeur chauffeur) {
        this.id = id;
        this.nbreEtoile = nbreEtoile;
        this.observation = observation;
        this.createdDate = new Date();
        this.chauffeur = chauffeur;
    }
    
}

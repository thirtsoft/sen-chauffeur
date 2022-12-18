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
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "newsleter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Newsleter implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "emailVisiteur post should not null")
    @Column(name = "emailVisiteur", length = 80)
    private String emailVisiteur;
    
    @Column(name = "createdDate", length = 80)
    private Date createdDate;
    
}

package com.chauffeur.models;

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
@Table(name = "typeAnnonce")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAnnonce {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "code", length = 50)
    private String code;

    @NotNull(message = "libelle should not null")
    @Column(name = "libelle", length = 100)
    private String libelle;
    
    @Column(name = "createdDate", length = 80)
    private Date createdDate;
    
}

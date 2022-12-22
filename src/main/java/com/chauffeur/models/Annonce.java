package com.chauffeur.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Folio9470m
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "annonce", uniqueConstraints = {
        @UniqueConstraint(columnNames = "reference")
})
public class Annonce implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", unique = true, length = 150)
    private String reference;
    //  private long reference;

    @NotNull(message = "libelle post should not null")
    @Column(name = "libelle", length = 120)
    private String libelle;

    @Column(name = "lieuPoste", length = 150)
    private String lieuPoste;

    @Column(name = "salaire", length = 120)
    private String salaire;

    @Email
    @Column(name = "emailPoste", length = 80)
    private String emailPoste;

    @NotNull(message = "time post should not null")
    @Column(name = "time", length = 90)
    private String time;

    @NotNull(message = "proExperience post should not null")
    @Column(name = "proExperience", length = 70)
    private String proExperience;

    @NotNull(message = "typeContrat post should not null")
    @Column(name = "typeContrat", length = 50)
    private String typeContrat;

    @Column(name = "isSelected", length = 5)
    private boolean selected;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "dateCandidature", length = 120)
    private Date dateCandidature;

    @NotNull(message = "dateCloture post should not null")
    @Column(name = "dateCloture", length = 120)
    private Date dateCloture;

    @ManyToOne
    @JoinColumn(name = "permId", nullable = false)
    private Permis permis;


    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Utilisateur utilisateur;


    @ManyToOne
    @JoinColumn(name = "addresseId", nullable = false)
    private Addresse addresse;


}

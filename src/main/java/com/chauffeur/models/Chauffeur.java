package com.chauffeur.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "chauffeur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chauffeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "reference shouldn't be null")
    @Column(name = "reference", length = 200)
    private String reference;

    @NotNull(message = "firstname shouldn't be null")
    @Column(name = "firstName", length = 90)
    private String firstName;

    @NotNull(message = "lastname shouldn't be null")
    @Column(name = "lastName", length = 90)
    private String lastName;

    @Column(name = "sexe", length = 30)
    private String sexe;

    @Column(name = "email", length = 150)
    private String email;

    @NotNull(message = "phoneChauffeur shouldn't be null")
    @Column(name = "phoneChauffeur", length = 30)
    private String phoneChauffeur;

    @NotNull(message = "experience shouldn't be null")
    @Column(name = "experience", length = 100)
    private String nbreAnneeExperience;

    @Column(name = "pretentionSalaire", length = 90)
    private double pretentionSalaire;

    @Column(name = "mobilite", length = 255)
    private String mobilite;

    @NotNull(message = "disponibility shouldn't be null")
    @Column(name = "disponibility", length = 100)
    private String disponibity;

    @Column(name = "description", length = 230)
    private String description;

    private boolean selected;

    @Column(name = "cvChauffeur", length = 250)
    private String cvChauffeur;

    @Column(name = "photoChauffeur", length = 250)
    private String photoChauffeur;

    @Column(name = "dateInscription", length = 70)
    private Date dateInscription;

    @Column(name = "obtainedDate", length = 70)
    private Date obtainedDate;

    @ManyToOne
    @JoinColumn(name = "permId")
    private Permis permis;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Addresse addresse;


    public Chauffeur(Long id, String reference, String firstName, String lastName,
                     String phoneChauffeur, String nbreAnneeExperience,
                     double pretentionSalaire, String mobilite, String disponibity,
                     boolean selected, String cvChauffeur, String photoChauffeur, Date dateInscription, String description,
                     Permis permis, Addresse addresse) {
        this.id = id;
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneChauffeur = phoneChauffeur;
        this.nbreAnneeExperience = nbreAnneeExperience;
        this.pretentionSalaire = pretentionSalaire;
        this.mobilite = mobilite;
        this.disponibity = disponibity;
        this.selected = selected;
        this.cvChauffeur = cvChauffeur;
        this.photoChauffeur = photoChauffeur;
        this.dateInscription = dateInscription;
        this.description = description;
        this.permis = permis;
        this.addresse = addresse;
    }

    public Chauffeur(Long id, String reference, String firstName, String lastName, String sexe,
                     String email, String phoneChauffeur, String nbreAnneeExperience, double pretentionSalaire, String mobilite,
                     String disponibity, boolean selected, String cvChauffeur, String photoChauffeur, Permis permis,
                     Addresse addresse) {
        this.id = id;
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.email = email;
        this.phoneChauffeur = phoneChauffeur;
        this.nbreAnneeExperience = nbreAnneeExperience;
        this.pretentionSalaire = pretentionSalaire;
        this.mobilite = mobilite;
        this.disponibity = disponibity;
        this.selected = selected;
        this.cvChauffeur = cvChauffeur;
        this.photoChauffeur = photoChauffeur;
        this.permis = permis;
        this.addresse = addresse;
    }


}

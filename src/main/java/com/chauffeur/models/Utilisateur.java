package com.chauffeur.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@Table(name = "utilisateur", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    @NotNull(message = "name shouldn't be null")
    private String name;

    @Column(name = "username", unique = true, length = 90)
    @NotNull(message = "username shouldn't be null")
    @Size(min = 3, message = "username should have 8 character")
    private String username;

    @Column(name = "mobile", unique = true, length = 30)
    @Pattern(regexp = "\\d{10}$", message = "invalid mobile number entered")
    private String mobile;

    @Column(name = "nomEntreprise", unique = true, length = 90)
    private String nomEntreprise;

    @Column(name = "website", length = 90)
    private String website;

    @Column(name = "secteurActivite", length = 100)
    private String secteurActivite;

    @Column(name = "email", unique = true, nullable = false, length = 60)
    @Email
  //  @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotNull(message = "email shouldn't be null")
    private String email;

    @Column(name = "addressRecruteur", length = 60)
    private String addressRecruteur;

    @Column(name = "villeRecruteur", length = 60)
    private String villeRecruteur;

    @Lob
    @Column(name = "information")
    private String information;

    @Column(name = "password", nullable = false)
    @NotNull(message = "password shouldn't be null")
    @Size(min = 8, message = "Password should have 8 character")
    private String password;

    private String photo = "avatar.jpg";

    private boolean isActive;


    private Date dateInscription;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Utilisateur(String username,
                       String email,
                       String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(String name,
                       String username,
                       String email,
                       String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(String name,
                       String username,
                       String mobile,
                       String email,
                       String password) {
        this.name = name;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(Long id, String name, String username, String mobile,
                       String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Utilisateur(String name, String username,
                       String email, String password, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Utilisateur(Utilisateur user) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public String getAddressRecruteur() {
        return addressRecruteur;
    }

    public void setAddressRecruteur(String addressRecruteur) {
        this.addressRecruteur = addressRecruteur;
    }

    public String getVilleRecruteur() {
        return villeRecruteur;
    }

    public void setVilleRecruteur(String villeRecruteur) {
        this.villeRecruteur = villeRecruteur;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
}

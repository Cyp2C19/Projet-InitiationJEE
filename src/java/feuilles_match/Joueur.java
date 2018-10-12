/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 *
 * @author cyprien
 */
@Entity
@Table(name = "joueur")
@NamedQueries({
    @NamedQuery(name = "Joueur.findAll", query = "SELECT j FROM Joueur j")
    , @NamedQuery(name = "Joueur.findByNumeroLicence", query = "SELECT j FROM Joueur j WHERE j.numeroLicence = :numeroLicence")
    , @NamedQuery(name = "Joueur.findByNom", query = "SELECT j FROM Joueur j WHERE j.nom = :nom")
    , @NamedQuery(name = "Joueur.findByPrenom", query = "SELECT j FROM Joueur j WHERE j.prenom = :prenom")
    , @NamedQuery(name = "Joueur.findByDateNaissance", query = "SELECT j FROM Joueur j WHERE j.dateNaissance = :dateNaissance")
    , @NamedQuery(name = "Joueur.findByPoste", query = "SELECT j FROM Joueur j WHERE j.poste = :poste")
    , @NamedQuery(name = "Joueur.findByNumero", query = "SELECT j FROM Joueur j WHERE j.numero = :numero")})
public class Joueur implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroLicence")
    private Integer numeroLicence;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "nom")
    private String nom;
    @Size(min = 1, max = 55)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "poste")
    private String poste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "photo")
    private String photo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @JoinColumn(name = "idEquipe", referencedColumnName = "idEquipe")
    @ManyToOne(optional = false)
    private Equipe idEquipe;
    
    @Transient
    private String statut;

    public Joueur() {
    }

    public Joueur(Integer numeroLicence) {
        this.numeroLicence = numeroLicence;
    }

    public Joueur(Integer numeroLicence, String nom, String prenom, Date dateNaissance, String poste, String statut, int numero, String photo) {
        this.numeroLicence = numeroLicence;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.poste = poste;
        this.statut = statut;
        this.numero = numero;
        this.photo = photo;
    }
    
    @PostConstruct
    public void init(){
        this.statut = "Absent";
    }

    public Integer getNumeroLicence() {
        return numeroLicence;
    }

    public void setNumeroLicence(Integer numeroLicence) {
        this.numeroLicence = numeroLicence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        if (prenom != null)
            return prenom;
        else
            return "";
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Equipe getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Equipe idEquipe) {
        this.idEquipe = idEquipe;
    }
    
    public int getAge(){
        LocalDate birthdate = new LocalDate (this.dateNaissance);
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(birthdate, now);
        return age.getYears();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroLicence != null ? numeroLicence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Joueur)) {
            return false;
        }
        Joueur other = (Joueur) object;
        if ((this.numeroLicence == null && other.numeroLicence != null) || (this.numeroLicence != null && !this.numeroLicence.equals(other.numeroLicence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "feuilles_match.Joueur[ numeroLicence=" + numeroLicence + " ]";
    }
    
}

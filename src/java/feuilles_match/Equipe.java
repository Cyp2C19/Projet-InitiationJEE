/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author cyprien
 */
@Entity
@Table(name = "equipe")
@NamedQueries({
    @NamedQuery(name = "Equipe.findAll", query = "SELECT e FROM Equipe e")
    , @NamedQuery(name = "Equipe.findByIdEquipe", query = "SELECT e FROM Equipe e WHERE e.idEquipe = :idEquipe")
    , @NamedQuery(name = "Equipe.findByNom", query = "SELECT e FROM Equipe e WHERE e.nom = :nom")
    , @NamedQuery(name = "Equipe.findByCoach", query = "SELECT e FROM Equipe e WHERE e.coach = :coach")
    , @NamedQuery(name = "Equipe.findByLogo", query = "SELECT e FROM Equipe e WHERE e.logo = :logo")})
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEquipe")
    private Integer idEquipe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "coach")
    private String coach;
    @Size(max = 150)
    @Column(name = "logo")
    private String logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipe")
    private Collection<Joueur> joueurCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipeDomicile")
    private Collection<FeuilleMatch> feuilleMatchCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipeExterieur")
    private Collection<FeuilleMatch> feuilleMatchCollection1;

    public Equipe() {
    }

    public Equipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public Equipe(Integer idEquipe, String nom, String coach) {
        this.idEquipe = idEquipe;
        this.nom = nom;
        this.coach = coach;
    }
    
    public List<Joueur> getTitulaires(){
        List<Joueur> joueurs = new ArrayList<Joueur>();
        if(this.joueurCollection != null){
            for(Joueur j : this.getJoueurCollection()){
                if(j.getStatut() != null){
                    if(j.getStatut().equals("Titulaire")){
                        joueurs.add(j);
                    }
                }
            }
        }
        return joueurs;
    }
    
    public List<Joueur> getRemplacants(){
        List<Joueur> joueurs = new ArrayList();
        if(this.joueurCollection != null){
            for(Joueur j : this.joueurCollection){
                if(j.getStatut() != null){
                    if(j.getStatut().equals("Remplaçant")){
                        joueurs.add(j);
                    }
                }
            }
        }
        return joueurs;
    }
    
    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Collection<Joueur> getJoueurCollection() {
        return joueurCollection;
    }

    public void setJoueurCollection(Collection<Joueur> joueurCollection) {
        this.joueurCollection = joueurCollection;
    }

    public Collection<FeuilleMatch> getFeuilleMatchCollection() {
        return feuilleMatchCollection;
    }

    public void setFeuilleMatchCollection(Collection<FeuilleMatch> feuilleMatchCollection) {
        this.feuilleMatchCollection = feuilleMatchCollection;
    }

    public Collection<FeuilleMatch> getFeuilleMatchCollection1() {
        return feuilleMatchCollection1;
    }

    public void setFeuilleMatchCollection1(Collection<FeuilleMatch> feuilleMatchCollection1) {
        this.feuilleMatchCollection1 = feuilleMatchCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipe != null ? idEquipe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        if ((this.idEquipe == null && other.idEquipe != null) || (this.idEquipe != null && !this.idEquipe.equals(other.idEquipe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(idEquipe);
    }
    
}

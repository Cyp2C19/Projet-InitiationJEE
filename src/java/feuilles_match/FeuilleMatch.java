/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;

import java.io.Serializable;
import java.util.Date;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author cyprien
 */
@Entity
@Table(name = "feuillematch")
@NamedQueries({
    @NamedQuery(name = "FeuilleMatch.findAll", query = "SELECT f FROM FeuilleMatch f")
    , @NamedQuery(name = "FeuilleMatch.findByIdFeuilleMatch", query = "SELECT f FROM FeuilleMatch f WHERE f.idFeuilleMatch = :idFeuilleMatch")
    , @NamedQuery(name = "FeuilleMatch.findByDateMatch", query = "SELECT f FROM FeuilleMatch f WHERE f.dateMatch = :dateMatch")
    , @NamedQuery(name = "FeuilleMatch.findByHoraireMatch", query = "SELECT f FROM FeuilleMatch f WHERE f.horaireMatch = :horaireMatch")
    , @NamedQuery(name = "FeuilleMatch.findByStade", query = "SELECT f FROM FeuilleMatch f WHERE f.stade = :stade")
    , @NamedQuery(name = "FeuilleMatch.findByArbitre", query = "SELECT f FROM FeuilleMatch f WHERE f.arbitre = :arbitre")})
public class FeuilleMatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFeuilleMatch")
    private Integer idFeuilleMatch;
    @Basic(optional = false)
    @NotNull(message = "Veuillez sélectionner un stade")
    @Size(min = 1, message = "Veuillez sélectionner un stade")
    @Column(name = "stade")
    private String stade;
    @Basic(optional = false)
    @NotNull(message = "Veuillez sélectionner un arbitre")
    @Size(min = 1, message = "Veuillez sélectionner un arbitre")
    @Column(name = "arbitre")
    private String arbitre;
    @Basic(optional = false)
    @NotNull(message = "Veuillez entrer une date de match")
    @Column(name = "dateMatch")
    @Temporal(TemporalType.DATE)
    private Date dateMatch;
    @Basic(optional = false)
    @NotNull(message = "Veuillez entrer un horaire de match")
    @Column(name = "horaireMatch")
    @Temporal(TemporalType.TIME)
    private Date horaireMatch;
    @JoinColumn(name = "equipeDomicile", referencedColumnName = "idEquipe")
    @ManyToOne(optional = false)
    private Equipe equipeDomicile;
    @JoinColumn(name = "equipeExterieur", referencedColumnName = "idEquipe")
    @ManyToOne(optional = false)
    private Equipe equipeExterieur;
    

    public FeuilleMatch() {
    }

    public FeuilleMatch(Integer idFeuilleMatch) {
        this.idFeuilleMatch = idFeuilleMatch;
    }

    public FeuilleMatch(Integer idFeuilleMatch, Date dateMatch, Date horaireMatch, String stade, String arbitre) {
        this.idFeuilleMatch = idFeuilleMatch;
        this.dateMatch = dateMatch;
        this.horaireMatch = horaireMatch;
        this.stade = stade;
        this.arbitre = arbitre;
    }

    public Integer getIdFeuilleMatch() {
        return idFeuilleMatch;
    }

    public void setIdFeuilleMatch(Integer idFeuilleMatch) {
        this.idFeuilleMatch = idFeuilleMatch;
    }


    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public String getArbitre() {
        return arbitre;
    }

    public void setArbitre(String arbitre) {
        this.arbitre = arbitre;
    }

    public Equipe getEquipeDomicile() {
        return equipeDomicile;
    }

    public void setEquipeDomicile(Equipe equipeDomicile) {
        this.equipeDomicile = equipeDomicile;
    }

    public Equipe getEquipeExterieur() {
        return equipeExterieur;
    }

    public void setEquipeExterieur(Equipe equipeExterieur) {
        this.equipeExterieur = equipeExterieur;
    }
    
        public Date getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(Date dateMatch) {
        this.dateMatch = dateMatch;
    }

    public Date getHoraireMatch() {
        return horaireMatch;
    }

    public void setHoraireMatch(Date horaireMatch) {
        this.horaireMatch = horaireMatch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFeuilleMatch != null ? idFeuilleMatch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeuilleMatch)) {
            return false;
        }
        FeuilleMatch other = (FeuilleMatch) object;
        if ((this.idFeuilleMatch == null && other.idFeuilleMatch != null) || (this.idFeuilleMatch != null && !this.idFeuilleMatch.equals(other.idFeuilleMatch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(idFeuilleMatch);
    }
    
}

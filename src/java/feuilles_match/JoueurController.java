/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author cyprien
 */
@Named(value = "joueurController")
@ViewScoped
public class JoueurController implements Serializable{
    @EJB
    private JoueurDAO dao;
    private Joueur joueurSelect;
    /**
     * Creates a new instance of JoueurController
     */
    public JoueurController() {
    }

    public JoueurDAO getDao() {
        return dao;
    }

    public void setDao(JoueurDAO dao) {
        this.dao = dao;
    }

    public Joueur getJoueurSelect() {
        return joueurSelect;
    }

    public void setJoueurSelect(Joueur joueurSelect) {
        this.joueurSelect = joueurSelect;
    }
    
    public void modifJoueur(Joueur joueur){
        dao.modifJoueur(joueur);
    }
}

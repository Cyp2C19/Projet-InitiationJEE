/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author cyprien
 */
@Named(value = "equipeController")
@ViewScoped
public class EquipeController implements Serializable{
    
    @EJB
    private EquipeDAO dao;
    /**
     * Creates a new instance of EquipeController
     */
    public EquipeController() {
    }

    public EquipeDAO getDao() {
        return dao;
    }

    public void setDao(EquipeDAO dao) {
        this.dao = dao;
    }

    public void modifEquipe(Equipe equipe){
        dao.modifEquipe(equipe);
    }
    
    public List<Equipe> getEquipes(){
        return dao.getAll();
    }
    
    public Equipe getEquipeById(int idEquipe){
        return dao.getEquipeById(idEquipe);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cyprien
 */
@Stateless
public class JoueurDAO {
    
    @PersistenceContext(unitName = "ProjetPU")
    protected EntityManager em;

    public void modifJoueur(Joueur joueur){
        em.merge(joueur);
        em.flush();
    }    
}

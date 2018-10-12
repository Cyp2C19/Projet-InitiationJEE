/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feuilles_match;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cyprien
 */
@Stateless
public class FeuilleMatchDAO {

    @PersistenceContext(unitName = "ProjetPU")
    protected EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public FeuilleMatch creerFeuilleMatch(FeuilleMatch feuille){
        feuille = em.merge(feuille);
        em.persist(feuille);
        em.flush();
        return feuille;
    }
    
    public List<FeuilleMatch> getAll(){
        Query query = em.createNamedQuery("FeuilleMatch.findAll");
        return query.getResultList();
    }
    
    public void suppFeuilleMatch(FeuilleMatch fm){
            fm = em.merge(fm);
            em.remove(fm);
            em.flush();
        }
}

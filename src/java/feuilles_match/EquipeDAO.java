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
public class EquipeDAO {
    
    @PersistenceContext(unitName = "ProjetPU")
    protected EntityManager em;
    
    public void modifEquipe(Equipe equipe){
        em.merge(equipe);
        em.flush();
    }
    
    public List<Equipe> getAll(){
     Query query = em.createNamedQuery("Equipe.findAll");
     return query.getResultList();
    }
    
    public Equipe getEquipeById(int idEquipe){
        Query query = em.createNamedQuery("Equipe.findByIdEquipe");
        return (Equipe) query.setParameter("idEquipe", idEquipe).getSingleResult();
    }
}

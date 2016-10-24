package ttma.services.impl;


import java.util.List;





import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;









import ttma.Entities.*;

import ttma.services.Interfaces.*;





/**
 * Session Bean implementation class GestionProjet
 */
@Stateless
//@Deprecated :


public class GestionConge implements GestionCongeRemote, GestionCongeLocal {

    /**
     * Default constructor. 
     */
    public GestionConge() {
        // TODO Auto-generated constructor stub
    }

    @PersistenceContext(unitName="pidev-project")	
    private EntityManager entityManager;

    @Override
	public Boolean addConge(Conge hotel) {
		try {
			entityManager.persist(hotel);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public Boolean updateConge(Conge hotel) {
		try {
			entityManager.merge(hotel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deleteConge(Conge hotel) {
		try {
			entityManager.remove(entityManager.merge(hotel));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	@Override
	public List<Conge> findAllConges() {
		Query query=entityManager.createQuery("select h from Conge h");
		return query.getResultList();
	}

	
    
	
}

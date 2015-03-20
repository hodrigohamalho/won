package won.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import won.model.DC;

@Stateless
public class DCRepository {

	@Inject
	private EntityManager em;
	
	public List<DC> activeDCS(){
        try{
            Query query = em.createQuery("SELECT d FROM DC d where d.active = true");
            return (List<DC>) query.getResultList();
        }catch(NullPointerException nre){
            return new ArrayList<DC>();
        }
    }
	
	public boolean isAnyRegistered(){
		try{
			em.createQuery("select 1 from DC").setMaxResults(1).getSingleResult();
			return true;
		}catch(NoResultException nre){
			return false;
		}
	}

	public void save(DC dc) throws Exception{
		em.persist(dc);
		findAll();
	}
	
	@SuppressWarnings("unchecked")
	public List<DC> findAll(){
        try{
            Query query = em.createQuery("SELECT d FROM DC d");
            return (List<DC>) query.getResultList();
        }catch(NullPointerException nre){
            return new ArrayList<DC>();
        }
	}
	
	public DC find(Integer id){
		return em.find(DC.class, id);
	}
	
	
	public void remove(Integer id){
		DC dc = em.find(DC.class, id);

        if (dc == null)
            throw new NoResultException();

        em.remove(dc);
	}

}

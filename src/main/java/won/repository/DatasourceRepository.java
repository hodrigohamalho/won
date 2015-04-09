package won.repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import won.model.Datasource;

@Stateless
public class DatasourceRepository {
	
	@Inject
	private EntityManager em;
	
	public Datasource findByNameAndHost(String name, String host){
		Query query = em.createQuery("SELECT d FROM Datasource d where d.name = :name AND host = :host");
		query.setParameter("name", name);
		query.setParameter("host", host);
		
		return (Datasource) query.getSingleResult();
	}

}

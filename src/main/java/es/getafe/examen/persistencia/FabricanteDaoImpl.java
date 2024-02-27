package es.getafe.examen.persistencia;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import es.getafe.examen.modelo.Fabricante;

public class FabricanteDaoImpl implements FabricanteDao{

	private EntityManager em;
	
	
	@Override
	public void save(Fabricante fabricante) {
		em = EMF.getInstance().createEntityManager();
		
		em.getTransaction().begin();
		em.merge(fabricante);
		em.getTransaction().commit();
		
		em.close();
		
	}

	@Override
	public Fabricante findByIdLazy(int idFabricante) {
		em = EMF.getInstance().createEntityManager();
		
		Fabricante fab= em.find(Fabricante.class, idFabricante);
		em.close();
		
		return fab;
	}

	@Override
	public Fabricante findById(int idFabricante) {
		em = EMF.getInstance().createEntityManager();
		
		String jpql = "select f from Fabricante f "
				+ "left join fetch f.productos where f.id_fabricante = :id";
		
		TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
		q.setParameter("id", idFabricante);
		
		Fabricante fab = q.getSingleResult();
		
		Fabricante buscado;
		try {
			buscado = q.getSingleResult();
		}catch (NoResultException e) {
			buscado = null;
		}
		em.close();
		return buscado;
	}

	@Override
	public Set<Fabricante> findOnlyActive() {
		
		String jpql = "select f from Fabricante f "
				+ "join f.productos";
		
		TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
		Set<Fabricante>fab = new HashSet<Fabricante>(q.getResultList());
		em.close();
		
		return fab;
	}

	@Override
	public Set<Fabricante> findAll() {
		String jpql = "select f from Fabricante f ";
		
		TypedQuery<Fabricante> q = em.createQuery(jpql, Fabricante.class);
		Set<Fabricante>fab = new HashSet<Fabricante>(q.getResultList());
		em.close();
		
		return fab;
	}

}

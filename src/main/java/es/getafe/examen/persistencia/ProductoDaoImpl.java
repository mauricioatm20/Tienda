package es.getafe.examen.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.getafe.examen.modelo.Producto;

public class ProductoDaoImpl implements ProductoDao{

	private EntityManager em;
	
	@Override
	public Producto findById(int idProducto) {
		em = EMF.getInstance().createEntityManager();
		
		Producto pro = em.find(Producto.class, idProducto);
		em.close();
		return pro;
	}

	@Override
	public List<Producto> findByDescripcion(String descripcion) {
		em = EMF.getInstance().createEntityManager();
		
		String jpql = "select p from Producto p "
				+ "where p.producto like :desc";
		
		TypedQuery<Producto> q = em.createQuery(jpql, Producto.class);
		q.setParameter("desc", "%"+descripcion+"%");
		
		List<Producto> pro = q.getResultList();
		em.close();
		return pro;
	}

	@Override
	public List<Producto> findAll() {
		
		em = EMF.getInstance().createEntityManager();
		
		String jpql = "select p from Producto p ";
		
		TypedQuery<Producto> q = em.createQuery(jpql, Producto.class);
		
		List<Producto> pro = q.getResultList();
		em.close();
		return pro;
	}

	@Override
	public void save(Producto p) {
		em = EMF.getInstance().createEntityManager();
		
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		
		em.close();
		
	}

}

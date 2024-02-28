package es.getafe.examen.negocio;

import java.text.Collator;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.getafe.examen.modelo.Fabricante;
import es.getafe.examen.modelo.Producto;
import es.getafe.examen.persistencia.EMF;
import es.getafe.examen.persistencia.FabricanteDao;
import es.getafe.examen.persistencia.FabricanteDaoImpl;
import es.getafe.examen.persistencia.ProductoDao;
import es.getafe.examen.persistencia.ProductoDaoImpl;

public class TiendaImpl implements Tienda{

	private FabricanteDao fDao;
	private ProductoDao pDao;
	
	public TiendaImpl() {
		fDao = new FabricanteDaoImpl();
		pDao = new ProductoDaoImpl();
		
	}
	
	
//***********************COMPARATOR PARA FABRICANTE*************	
	private Comparator<Fabricante> getComFab(){
		Collator col = Collator.getInstance(new Locale("es"));
		return new Comparator<Fabricante>() {
			
			@Override
			public int compare(Fabricante f1, Fabricante f2) {
				return col.compare(f1.getFabricante() + f1.getIdFabricante() , f2.getFabricante() + f2.getIdFabricante() );
				
			}
		};
		
	}
	
	
	
	@Override
	public Set<Producto> getProductos() {
	
		
		Set<Producto> pro = new TreeSet<Producto>(new Comparator<Producto>() {// se crea un comparator aqui solo porque lo necesito una ves en este metodo

			@Override
			public int compare(Producto p1, Producto p2) {
				Collator col = Collator.getInstance(new Locale("es"));
				return col.compare(p1.getProducto() + p1.getIdProducto(),  p2.getProducto() + p2.getIdProducto()); 
				//si hay dos productos que se llaman igual en el treeset se guarda solo 1 por eso se concatena con el id
				
			}
		});

		pro.addAll(pDao.findAll());
		
		return pro;
	}

	@Override
	public Set<Producto> getProductos(String descripcion) {
		
		Set<Producto> pro = new TreeSet<Producto>(pDao.findByDescripcion(descripcion));
//		if(pro.size() > 0) {
//			return pro;
//		}else {
//			return null;
//		}
		
//		el return es lo mismo que esta escrito en el if
		return pro.size() > 0 ? pro : null;
	}

	@Override
	public double getMediaPrecioProductosByFabricante(int idFabricante) {
		
		Fabricante fab = fDao.findById(idFabricante);
		if (fab == null) {
			return 0;
		}
		
		double suma = 0 ;
		
		for(Producto producto : fab.getProductos()) {
			suma += producto.getPrecio();
		}
		return suma / fab.getProductos().size() ;
	}

	@Override
	public void addFabricante(Fabricante fabricante) {
		fDao.save(fabricante);
		
	}

	@Override
	public void addProducto(Producto producto) {
		pDao.save(producto);
	}

	@Override
	public Set<Fabricante> getFabricantes() {
		
		Set<Fabricante> fab = new TreeSet<Fabricante>(getComFab());
		fab.addAll(fDao.findAll());
		return fab;
	}

	@Override
	public Set<Fabricante> getFabricantesActivos() {
		Set<Fabricante> fab = new TreeSet<Fabricante>(getComFab());
		fab.addAll(fDao.findOnlyActive());
		return fab;
	}

	@Override
	public Fabricante getFabricante(int idFabricante) {
		
		return fDao.findByIdLazy(idFabricante);
	}

	@Override
	public Fabricante getFabricanteConProductos(int idFabricante) {
		
		return fDao.findById(idFabricante);
	}

}

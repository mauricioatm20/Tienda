package es.getafe.examen.vista;

import java.io.IOException;

import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.getafe.examen.modelo.Fabricante;
import es.getafe.examen.modelo.Producto;
import es.getafe.examen.negocio.Tienda;
import es.getafe.examen.negocio.TiendaImpl;


@WebServlet("/productos_fabricante")
public class ProductosFabricante extends HttpServlet{

	private Tienda tienda;
	
	@Override
	public void init() throws ServletException { // escribir init + contol + espacio
		tienda = new TiendaImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Set<Fabricante> fabs = tienda.getFabricantesActivos();
		req.getSession().setAttribute("fabs", fabs);
		
		req.getRequestDispatcher("/WEB-INF/vistas/productos_fabricante.jsp").forward(req, resp);
		
		req.getSession().removeAttribute("prods");
		req.getSession().removeAttribute("fabs");
		req.getSession().removeAttribute("fab_actual");
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idFabricanteString = req.getParameter("idFabricante");
		if(idFabricanteString != null) {
			int idFabricante;
			try {
				idFabricante = Integer.parseInt(idFabricanteString);
				Fabricante actual = tienda.getFabricanteConProductos(idFabricante);
				Set<Producto> prod = actual.getProductos();
				
				HttpSession sesion = req.getSession();
				sesion.setAttribute("prods", prod);
				sesion.setAttribute("fab_actual", actual);
				
				resp.sendRedirect("productos_fabricante");
			}catch (NumberFormatException e) {
				//cerramos sesion
				//envio a login
			}
		}else {
			//cerramos sesion
			//envio a login
		}
		
		
		
	}
}

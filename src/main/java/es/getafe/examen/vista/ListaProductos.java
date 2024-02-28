package es.getafe.examen.vista;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.getafe.examen.modelo.Producto;
import es.getafe.examen.negocio.Tienda;
import es.getafe.examen.negocio.TiendaImpl;


@WebServlet("/listado_productos")
public class ListaProductos extends HttpServlet{

	private Tienda tienda;
	
	@Override
	public void init() throws ServletException { // escribir init + contol + espacio
		tienda = new TiendaImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("/WEB-INF/vistas/listado_productos.jsp").forward(req, resp);
		req.getSession().removeAttribute("prods");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String descripcion = req.getParameter("descripcion");
		Set<Producto> prod = tienda.getProductos(descripcion);
		HttpSession sesion = req.getSession();
		sesion.setAttribute("prods", prod);
		
		resp.sendRedirect("listado_productos");
		
	}
}

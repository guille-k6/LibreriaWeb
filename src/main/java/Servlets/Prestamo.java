package Servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.EjemplarCantidad;
import Entities.Libro;
import Entities.Socio;
import Logic.LibroLogic;
import Logic.SocioLogic;

/**
 * Servlet implementation class Prestamo
 */
@WebServlet("/prestamo")
public class Prestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Prestamo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> librosCantidades = new HashMap<>();
		Enumeration<String> parametros = request.getParameterNames();
		while (parametros.hasMoreElements()) {
			String parametro = parametros.nextElement();
			if (parametro.startsWith("cantidadLibros-")) {
				String idLibro = parametro.substring("cantidadLibros-".length());
				String cantidad = request.getParameter(parametro);
				if (Integer.parseInt(cantidad) > 0) {
					librosCantidades.put(idLibro, cantidad);
				}
			}
		}
		LinkedList<EjemplarCantidad> ejemplaresCantidadSeleccionados = new LinkedList<>();
		LibroLogic liblog = new LibroLogic();
		for (String idLibro : librosCantidades.keySet()) {
			Libro libro = liblog.getOneById(new Libro(Integer.parseInt(idLibro)));
			EjemplarCantidad ejemplarCantidad = new EjemplarCantidad(Integer.parseInt(librosCantidades.get(idLibro)),
					libro);
			ejemplaresCantidadSeleccionados.add(ejemplarCantidad);
		}
		String idSocioDeudor = (String) request.getParameter("socioDeudor");
		SocioLogic soclog = new SocioLogic();
		Socio socioDeudor = soclog.getOneById(new Socio(Integer.parseInt(idSocioDeudor)));
		// Si entra por este if significa que no selecciono ningun libro
		if (ejemplaresCantidadSeleccionados.size() == 0) {
			request.setAttribute("socioDeudor", socioDeudor);
			request.setAttribute("mensaje", "Debe seleccionar al menos un libro");
			request.getRequestDispatcher("WEB-INF/pages/admin/EjemplaresDisponibles.jsp").forward(request, response);
		}
		request.setAttribute("socioDeudor", socioDeudor);
		request.setAttribute("librosCantidad", ejemplaresCantidadSeleccionados);
		request.getRequestDispatcher("WEB-INF/pages/admin/ConfirmarPrestamoSocio.jsp").forward(request, response);
	}

}

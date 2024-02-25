package Servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.PrestamoLogic;
import Logic.SocioLogic;

/**
 * Servlet implementation class confirmarPrestamo
 */
@WebServlet("/confirmarPrestamo")
public class confirmarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public confirmarPrestamo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("cancelar") != null) {
			request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);
			return;
		}
		SocioLogic soclog = new SocioLogic();
		PrestamoLogic plogic = new PrestamoLogic();
		Optional<String> mensaje;
		Map<String, String> librosCantidades = new HashMap<>();
		Enumeration<String> parametros = request.getParameterNames();
		while (parametros.hasMoreElements()) {
			String parametro = parametros.nextElement();
			if (parametro.startsWith("cantidadLibros-")) {
				String idLibro = parametro.substring("cantidadLibros-".length());
				String cantidad = request.getParameter(parametro);
				librosCantidades.put(idLibro, cantidad);
			}
		}
		// Debería validar que el socio pueda alquilar libros de acuerdo a mi politica
		// de prestamo
		String socioIdString = request.getParameter("socioId");
		int socioId = Integer.parseInt(socioIdString);
		Socio socioDeudor = soclog.getOneById(new Socio(socioId));
		// Valido que el socio pueda efectuar un prestamo
		Optional<String> mensajePrestamo = PrestamoLogic.isUserCapableOfLoan(socioDeudor);
		if (mensajePrestamo.isPresent()) {
			request.setAttribute("message", mensajePrestamo.get());
			request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);
			return;
		}
		if (mensajePrestamo.isEmpty()) {
			mensaje = plogic.generatePrestamo(socioDeudor, librosCantidades);
			request.setAttribute("mensaje", mensaje.get());
			request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);
			return;
		}

	}

}

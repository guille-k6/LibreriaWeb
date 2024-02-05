package Servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.EjemplarLogic;
import Logic.PrestamoLogic;
import Logic.SocioLogic;

/**
 * Servlet implementation class confirmarPrestamo
 */
@WebServlet("/confirmarPrestamo")
public class confirmarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public confirmarPrestamo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("cancelar") != null) {
			request.getRequestDispatcher("WEB-INF/pages//menuAdmin.jsp").forward(request, response);
			return;
		}
		String mensaje;
		EjemplarLogic ejLogic = new EjemplarLogic();
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
		String socioId = request.getParameter("socioId");
		SocioLogic soclog = new SocioLogic();
		Socio socioDeudor = soclog.getOneById(new Socio(Integer.parseInt(socioId)));
		// Valido que el socio pueda efectuar un prestamo
		if (PrestamoLogic.isUserCapableOfLoan(socioDeudor).isPresent()) {
			mensaje = PrestamoLogic.isUserCapableOfLoan(socioDeudor).get();
		}
		if (PrestamoLogic.isUserCapableOfLoan(socioDeudor).isEmpty()) {
			// Puede efectuar un prestamo
			if (ejLogic.updateEjemplaresAvailables(librosCantidades)) {
				// TODO: Crear un objeto prestamo..
				int idPrestamo = 1;
				mensaje = "Prestamo generado con id = " + idPrestamo;
			} else {
				mensaje = "No hay suficientes ejemplares disponibles.";
			}
		}

	}

}

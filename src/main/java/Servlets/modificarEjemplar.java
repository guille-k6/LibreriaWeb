package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Ejemplar;
import Entities.Libro;
import Logic.EjemplarLogic;
import Logic.LibroLogic;

/**
 * Servlet implementation class modificarEjemplar
 */
@WebServlet("/modificarEjemplar")
public class modificarEjemplar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public modificarEjemplar() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EjemplarLogic ejelog = new EjemplarLogic();
		LibroLogic liblog = new LibroLogic();

		String opc = request.getParameter("opcion");
		int id = Integer.parseInt(request.getParameter("id"));
		Ejemplar ejemplar = new Ejemplar(id);
		if (opc.equals("editar")) {
			int idLibro = Integer.parseInt(request.getParameter("idLibro"));
			Libro libro = new Libro();
			libro.setIdLibro(idLibro);
			libro = liblog.getOneById(libro);

			ejemplar.setIdEjemplar(id);
			ejemplar.setLibro(libro);
			try {
				ejelog.update(ejemplar);
				request.setAttribute("mensaje", "Modificacion existosa");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al modificar el ejemplar");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
			}
		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
		}
	}

}

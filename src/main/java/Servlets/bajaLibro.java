package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Libro;
import Logic.LibroLogic;

/**
 * Servlet implementation class bajaLibro
 */
@WebServlet("/bajaLibro")
public class bajaLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public bajaLibro() {
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
		String opc = request.getParameter("opcion");
		String elId = request.getParameter("id");
		int id = Integer.parseInt(elId);
		Libro libro = new Libro(id);

		LibroLogic liblog = new LibroLogic();
		if (opc.equals("eliminar")) {
			try {
				liblog.remove(libro);
				request.setAttribute("mensaje", "Baja existosa");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al eliminar Libro: " + libro.getIdLibro());
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
			}

		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
		}
	}

}

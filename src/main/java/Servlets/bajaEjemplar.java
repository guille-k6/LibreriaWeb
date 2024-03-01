package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Ejemplar;
import Logic.EjemplarLogic;

/**
 * Servlet implementation class bajaEjemplar
 */
@WebServlet("/bajaEjemplar")
public class bajaEjemplar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public bajaEjemplar() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		if (opc.equals("eliminar")) {
			String elId = request.getParameter("id");
			int id = Integer.parseInt(elId);
			Ejemplar ejemplar = new Ejemplar(id);
			EjemplarLogic ejelog = new EjemplarLogic();
			try {
				ejelog.remove(ejemplar);
				request.setAttribute("mensaje", "Baja exitosa");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("mensaje", "No se pudo eliminar ejemplar con id: " + ejemplar.getIdEjemplar());
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
			}
		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
		}
	}

}

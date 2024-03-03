package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.SocioLogic;

/**
 * Servlet implementation class bajaSocio
 */
@WebServlet("/bajaSocio")
public class bajaSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public bajaSocio() {
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
		Socio Socio = new Socio(id);

		SocioLogic soclog = new SocioLogic();
		if (opc.equals("eliminar")) {
			try {
				soclog.remove(Socio);
				request.setAttribute("mensaje", "Baja existosa");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al eliminar Socio: " + Socio.getIdSocio());
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
			}

		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
		}
	}

}

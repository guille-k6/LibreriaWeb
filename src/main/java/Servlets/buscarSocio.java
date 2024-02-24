package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.SocioLogic;

/**
 * Servlet implementation class buscarSocio
 */
@WebServlet("/buscarSocio")
public class buscarSocio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public buscarSocio() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SocioLogic soclog = new SocioLogic();
		String borrar = request.getParameter("borrar");
		if (borrar != null && !borrar.isBlank()) {
			List<Socio> allSocios = soclog.getAll();
			request.setAttribute("socios", allSocios);
			request.getRequestDispatcher("WEB-INF/pages/admin/BuscarSocio.jsp").forward(request, response);
			request.setAttribute("ultimaBusqueda", "");
			return;
		}
		String socioBuscado = request.getParameter("nombreSocio");
		if (socioBuscado != null && !socioBuscado.isBlank()) {
			List<Socio> sociosBuscados = soclog.getAllSociosThatMatch(socioBuscado);
			if (sociosBuscados.size() == 0) {
				request.setAttribute("mensaje", "No se encontraron socios");
			}
			request.setAttribute("socios", sociosBuscados);
			request.setAttribute("ultimaBusqueda", socioBuscado);
			request.getRequestDispatcher("WEB-INF/pages/admin/BuscarSocio.jsp").forward(request, response);
		}
		request.getRequestDispatcher("WEB-INF/pages/admin/BuscarSocio.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

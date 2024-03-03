package Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.PoliticaPrestamo;
import Logic.PoliticaPrestamoLogic;
import utils.LoggerError;

/**
 * Servlet implementation class modificarPoliticaPrestamo
 */
@WebServlet("/modificarPoliticaPrestamo")
public class modificarPoliticaPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public modificarPoliticaPrestamo() {
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
		if (opc == null || opc.isBlank()) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarPoliticaPrestamos.jsp").forward(request,
					response);
			return;
		}
		if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);
			return;
		}
		PoliticaPrestamoLogic ppLogic = new PoliticaPrestamoLogic();
		int politicaPrestamoId = Integer.parseInt(request.getParameter("id"));
		PoliticaPrestamo politicaPrestamo = ppLogic.getOneById(new PoliticaPrestamo(politicaPrestamoId));
		String fechaDesde = request.getParameter("fechaDesde");
		String cantMaxLibrosPend = request.getParameter("cantMaxLibrosPend");
		if (fechaDesde == null || fechaDesde.isBlank()) {
			request.setAttribute("mensaje", "Complete fecha desde");
			request.setAttribute("PoliticaPrestamoModificar", politicaPrestamo);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarPoliticaPrestamos.jsp").forward(request,
					response);
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = null;
		try {
			parsed = sdf.parse(fechaDesde);
		} catch (ParseException e1) {
			e1.printStackTrace();
			request.setAttribute("mensaje", "Fecha desde incorrecta");
			request.setAttribute("PoliticaPrestamoModificar", politicaPrestamo);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarPoliticaPrestamos.jsp").forward(request,
					response);
			return;
		}
		if (cantMaxLibrosPend == null || cantMaxLibrosPend.isBlank()) {
			request.setAttribute("mensaje", "Complete maximo libros pendientes");
			request.setAttribute("PoliticaPrestamoModificar", politicaPrestamo);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarPoliticaPrestamos.jsp").forward(request,
					response);
			return;
		}

		try {
			java.sql.Date data = new java.sql.Date(parsed.getTime());
			politicaPrestamo.setFechaDesde(data);
			politicaPrestamo.setCantMaxLibrosPend(Integer.parseInt(cantMaxLibrosPend));
			ppLogic.update(politicaPrestamo);
			request.setAttribute("estado", "Modificacion existosa");
		} catch (Exception e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			politicaPrestamo = ppLogic.getOneById(new PoliticaPrestamo(politicaPrestamoId));
			request.setAttribute("PoliticaPrestamoModificar", politicaPrestamo);
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarPoliticaPrestamos.jsp").forward(request,
					response);
		}
		request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);
	}

}

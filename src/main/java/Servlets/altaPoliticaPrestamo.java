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
 * Servlet implementation class altaPoliticaPrestamo
 */
@WebServlet("/altaPoliticaPrestamo")
public class altaPoliticaPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public altaPoliticaPrestamo() {
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
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaPoliticaPrestamos.jsp").forward(request, response);
			return;
		}
		if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);
			return;
		}
		String fechaDesde = request.getParameter("fechaDesde");
		String cantMaxLibrosPend = request.getParameter("cantMaxLibrosPend");
		if (fechaDesde == null || fechaDesde.isBlank()) {
			request.setAttribute("mensaje", "Complete fecha desde");
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaPoliticaPrestamos.jsp").forward(request, response);
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = null;
		try {
			parsed = sdf.parse(fechaDesde);
		} catch (ParseException e1) {
			e1.printStackTrace();
			request.setAttribute("mensaje", "Fecha desde incorrecta");
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaPoliticaPrestamos.jsp").forward(request, response);
			return;
		}
		if (cantMaxLibrosPend == null || cantMaxLibrosPend.isBlank()) {
			request.setAttribute("mensaje", "Complete maximo libros pendientes");
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaPoliticaPrestamos.jsp").forward(request, response);
			return;
		}

		PoliticaPrestamo PoliticaPrestamo = new PoliticaPrestamo();
		PoliticaPrestamoLogic ppLogic = new PoliticaPrestamoLogic();
		try {
			java.sql.Date data = new java.sql.Date(parsed.getTime());
			PoliticaPrestamo.setFechaDesde(data);
			PoliticaPrestamo.setCantMaxLibrosPend(Integer.parseInt(cantMaxLibrosPend));
			ppLogic.add(PoliticaPrestamo);
			String estado = "Alta existosa";
			request.setAttribute("estado", estado);
		} catch (Exception e) {
			LoggerError.log(e.getStackTrace(), e.getMessage());
			request.setAttribute("estado", e.getMessage());
		}
		request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);
	}

}

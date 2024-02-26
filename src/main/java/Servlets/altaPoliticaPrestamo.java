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
import Entities.Socio;
import Logic.PoliticaPrestamoLogic;
import utils.LoggerError;

/**
 * Servlet implementation class altaPoliticaPrestamo
 */
@WebServlet("/altaPoliticaPrestamo")
public class altaPoliticaPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public altaPoliticaPrestamo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recupero el usuario y veo que opcion eligi�
		Socio socio = new Socio();
		socio = (Socio) request.getSession().getAttribute("usuario");
		String opc = request.getParameter("opcion");

		switch (opc) {
		// CREAR
		case ("crearPoliticaPrestamo"):
			// Recupero el nombre y el apellido del form.
			String fechaDesde = request.getParameter("fechaDesde");
			String cantMaxLibrosPend = request.getParameter("cantMaxLibrosPend");
			// Creo un PoliticaPrestamo y lo cargo a la DB.
			PoliticaPrestamoLogic autlog = new PoliticaPrestamoLogic();
			PoliticaPrestamo PoliticaPrestamo = new PoliticaPrestamo();
			// Parseo la fecha de edicicon como Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// se usa java.util.date porque el parse es exclusivo de este pero luego se
			// transfarma de util.date a sql.date
			java.util.Date parsed = null;
			try {
				parsed = sdf.parse(fechaDesde);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			java.sql.Date data = new java.sql.Date(parsed.getTime());
			PoliticaPrestamo.setFechaDesde(data);
			PoliticaPrestamo.setCantMaxLibrosPend(Integer.parseInt(cantMaxLibrosPend));
			try {
				autlog.add(PoliticaPrestamo);
				String estado = "Alta existosa";
				request.setAttribute("estado", estado);
			} catch (Exception e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);
			break;

		// CANCELAR
		case ("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);
			break;
		}
	}

}

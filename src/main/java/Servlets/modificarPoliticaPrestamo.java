package Servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.PoliticaPrestamo;
import Logic.PoliticaPrestamoLogic;

/**
 * Servlet implementation class modificarPoliticaPrestamo
 */
@WebServlet("/modificarPoliticaPrestamo")
public class modificarPoliticaPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarPoliticaPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Traigo mi ID de PoliticaPrestamo y la opci�n elegida.

		String opc = request.getParameter("opcion");
		String elId = request.getParameter("id");
		int id = Integer.parseInt(elId);
		PoliticaPrestamo PoliticaPrestamo = new PoliticaPrestamo();
		PoliticaPrestamo.setIdPoliticaPrestamo(id);


		PoliticaPrestamoLogic pollog = new PoliticaPrestamoLogic();
		// Cargo la opcion y confirmo si lo quiere eliminar o no.
		if(opc.equals("editar")){
			// Guardo el atributo PoliticaPrestamoModificar para que se carguen los datos en la recarga
			PoliticaPrestamo PoliticaPrestamoModificar = pollog.getOneById(PoliticaPrestamo);
			request.setAttribute("PoliticaPrestamoModificar", PoliticaPrestamoModificar);
			// Traigo los valores de los inputs
			String fechaDesde = request.getParameter("fechaDesde");
			String cantMaxLibrosPend = request.getParameter("cantMaxLibrosPend");
			// Parseo la fecha de edicicon como Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// se usa java.util.date porque el parse es exclusivo de este pero luego se transfarma de util.date a sql.date
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
			
			// Updateo el PoliticaPrestamo con sus nuevos datos (nombre y apellido).
			try {
			pollog.update(PoliticaPrestamo);
			String estado = "Modificacion existosa";
			request.setAttribute("estado", estado);
			}catch (Exception e) {
	            e.printStackTrace();
			}
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);

		}else if(opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);
		}
	}

}

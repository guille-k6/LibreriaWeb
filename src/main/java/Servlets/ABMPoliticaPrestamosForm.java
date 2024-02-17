package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DataPoliticaPrestamo;
import Entities.PoliticaPrestamo;
import Entities.Socio;

/**
 * Servlet implementation class ABMPoliticaPrestamoesForm
 */
@WebServlet("/ABMPoliticaPrestamosForm")
public class ABMPoliticaPrestamosForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMPoliticaPrestamosForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero opci�n y usuario.
		Socio socio = new Socio();
		socio = (Socio)request.getSession().getAttribute("usuario");
		String opc = request.getParameter("opcion");
		String edit = request.getParameter("editar");
		String eliminar = request.getParameter("eliminar");

		if(opc!=null){
			// Lo mando a la secci�n alta
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaPoliticaPrestamos.jsp").forward(request, response);
		}else if(edit!=null){
			// Recupero el ID y lo mando a la secci�n modificar
			String idModificar = request.getParameter("editar");
			request.setAttribute("idModificar", idModificar);
			DataPoliticaPrestamo pollog = new DataPoliticaPrestamo();
			PoliticaPrestamo PoliticaPrestamo = new PoliticaPrestamo();

			//Busco el PoliticaPrestamo con ese ID y lo mando como par�metro al PoliticaPrestamo a la p�gina de modificar.
			PoliticaPrestamo.setIdPoliticaPrestamo(Integer.parseInt(idModificar));
			PoliticaPrestamo PoliticaPrestamoModificar = pollog.getById(PoliticaPrestamo);
			request.setAttribute("PoliticaPrestamoModificar", PoliticaPrestamoModificar);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarPoliticaPrestamos.jsp").forward(request, response);
		}else if(eliminar!=null){
			String idBaja = request.getParameter("eliminar");
			DataPoliticaPrestamo autlog = new DataPoliticaPrestamo();
			PoliticaPrestamo PoliticaPrestamo = new PoliticaPrestamo();

			//Busco el PoliticaPrestamo con ese ID y lo mando como par�metro al PoliticaPrestamo a la p�gina de eliminar.
			PoliticaPrestamo.setIdPoliticaPrestamo(Integer.parseInt(idBaja));
			PoliticaPrestamo PoliticaPrestamoBaja = autlog.getById(PoliticaPrestamo);
			request.setAttribute("PoliticaPrestamoBaja", PoliticaPrestamoBaja);
			request.getRequestDispatcher("WEB-INF/pages/admin/BajaPoliticaPrestamos.jsp").forward(request, response);
		}
	}

}

package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.SocioLogic;

/**
 * Servlet implementation class ABMLibrosForm
 */
@WebServlet("/BusquedaSocioForm")
public class BusquedaSocioForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BusquedaSocioForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * get se utiliza para cuando elegimos efectivamente un Socio
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Busco el socio al que le voy a efectuar un prestamo
		String idSocio = request.getParameter("elegir");
		if (idSocio != null) {
			SocioLogic soclog = new SocioLogic();
			Socio socioDeudor = new Socio();
			socioDeudor.setIdSocio(Integer.parseInt(idSocio));
			socioDeudor = soclog.getOneById(socioDeudor);
			request.setAttribute("socioDeudor", socioDeudor);
			request.getRequestDispatcher("WEB-INF/pages/admin/EjemplaresDisponibles.jsp").forward(request, response);
		}
	}

	/**
	 * post se utiliza para realizar una busqueda por apellido de socio.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		SocioLogic soclog = new SocioLogic();
		Socio socio = new Socio();
		socio = (Socio) request.getSession().getAttribute("usuario");
		String apellido = request.getParameter("apellido");
		Socio socioABuscar = new Socio();
		socioABuscar.setApellido(apellido);
		LinkedList<Socio> sociosApellido = new LinkedList<>();
		sociosApellido = soclog.getAllByApellido(socioABuscar);
		request.setAttribute("sociosApellido", sociosApellido);
		// Busco los socios que tengan un apellido y lo envio a la pagina anterior.
		request.getRequestDispatcher("WEB-INF/pages/admin/BuscarSocio.jsp").forward(request, response);
	}

}

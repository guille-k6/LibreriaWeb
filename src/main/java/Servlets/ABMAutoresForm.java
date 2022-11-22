package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Data.DataAutor;
import Entities.Autor;
import Entities.Socio;

/**
 * Servlet implementation class ABMAutoresForm
 */
@WebServlet("/ABMAutoresForm")
public class ABMAutoresForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMAutoresForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero opci�n y usuario.
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("opcion");
		
		switch(opc) {
		
		case ("alta"):
			// Lo mando a la secci�n alta
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaAutores.jsp").forward(request, response);
			break;
		case("editar"):
			// Recupero el ID y lo mando a la secci�n modificar
			String idModificar = request.getParameter("data-id");
			request.getSession().setAttribute("idModificar", idModificar);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarAutores.jsp").forward(request, response);
			break;
		case("eliminar"):
			// Recupero el ID
			String idBaja = request.getParameter("data-id");
			DataAutor autlog = new DataAutor();
			Autor autor = new Autor();
			
			// Busco el autor con ese ID y lo mando como par�metro al autor a la p�gina de eliminar.
			//autor.setIdAutor(Integer.parseInt(idBaja));
			//Autor autorBaja = autlog.getById(autor);
			request.setAttribute("idFalopa", idBaja);
			request.getRequestDispatcher("WEB-INF/pages/admin/BajaAutores.jsp").forward(request, response);
			break;
		}
		
		// Mando al socio para que se quede logeado.
		request.getSession().setAttribute("usuario", socio);
	}

}
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero opción y usuario.
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("opcion");
		String edit = request.getParameter("editar");
		String eliminar = request.getParameter("eliminar");
		
		if(opc!=null){
			// Lo mando a la sección alta
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaAutores.jsp").forward(request, response);			
		}else if(edit!=null){
			// Recupero el ID y lo mando a la sección modificar
			String idModificar = request.getParameter("editar");
			request.setAttribute("idModificar", idModificar);
			DataAutor autlog = new DataAutor();
			Autor autor = new Autor();
			
			//Busco el autor con ese ID y lo mando como parámetro al autor a la página de modificar.
			autor.setIdAutor(Integer.parseInt(idModificar));
			Autor autorModificar = autlog.getById(autor);
			request.setAttribute("autorModificar", autorModificar);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarAutores.jsp").forward(request, response);			
		}else if(eliminar!=null){
			String idBaja = request.getParameter("eliminar");
			DataAutor autlog = new DataAutor();
			Autor autor = new Autor();
			
			//Busco el autor con ese ID y lo mando como parámetro al autor a la página de eliminar.
			autor.setIdAutor(Integer.parseInt(idBaja));
			Autor autorBaja = autlog.getById(autor);
			request.setAttribute("autorBaja", autorBaja);
			request.getRequestDispatcher("WEB-INF/pages/admin/BajaAutores.jsp").forward(request, response);			
		}
	}

}

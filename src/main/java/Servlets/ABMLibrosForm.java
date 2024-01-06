package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Libro;
import Entities.Socio;
import Logic.LibroLogic;

/**
 * Servlet implementation class ABMLibrosForm
 */
@WebServlet("/ABMLibrosForm")
public class ABMLibrosForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMLibrosForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero opci�n y usuario.
		Socio socio = new Socio();
		socio = (Socio)request.getSession().getAttribute("usuario");
		String opc = request.getParameter("opcion"); // Asi se llama el name del alta.
		String edit = request.getParameter("editar");
		String eliminar = request.getParameter("eliminar");

		if(opc!=null){
			// Lo mando a la secci�n alta
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaLibros.jsp").forward(request, response);
		}else if(edit!=null){
			// Recupero el ID y lo mando a la secci�n modificar
			String idModificar = request.getParameter("editar");
			LibroLogic liblog = new LibroLogic();
			Libro libro = new Libro();

			request.setAttribute("idModificar", idModificar);

			//Busco el libro con ese ID y lo mando como par�metro al autor a la p�gina de modificar.
			libro.setIdLibro(Integer.parseInt(idModificar));
			Libro libroModificar = liblog.getOneById(libro);
			request.setAttribute("libroModificar", libroModificar);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarLibros.jsp").forward(request, response);
		}else if(eliminar!=null){
			String idBaja = request.getParameter("eliminar");
			LibroLogic liblog = new LibroLogic();
			Libro libro = new Libro();

			//Busco el libro con ese ID y lo mando como par�metro al autor a la p�gina de eliminar.
			libro.setIdLibro(Integer.parseInt(idBaja));
			Libro libroBaja = liblog.getOneById(libro);
			request.setAttribute("libroBaja", libroBaja);
			request.getRequestDispatcher("WEB-INF/pages/admin/BajaLibros.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

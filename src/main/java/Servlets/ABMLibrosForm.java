package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Libro;
import Logic.LibroLogic;

/**
 * Servlet implementation class ABMLibrosForm
 */
@WebServlet("/ABMLibrosForm")
public class ABMLibrosForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ABMLibrosForm() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		String edit = request.getParameter("editar");
		String eliminar = request.getParameter("eliminar");
		String libroBuscado = request.getParameter("nombreLibro");

		if (libroBuscado != null && !libroBuscado.isBlank() && opc != null && opc.equals("buscar")) {
			LibroLogic libroLogic = new LibroLogic();
			List<Libro> librosBuscados = libroLogic.getAllLibrosThatMatch(libroBuscado);
			if (librosBuscados.size() == 0) {
				request.setAttribute("mensaje", "No se encontraron libros");
			}
			request.setAttribute("libros", librosBuscados);
			request.setAttribute("ultimaBusqueda", libroBuscado);
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
		}
		if (opc != null && !opc.equals("buscar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaLibros.jsp").forward(request, response);
		} else if (edit != null) {
			String idModificar = request.getParameter("editar");
			LibroLogic liblog = new LibroLogic();
			Libro libro = new Libro();

			request.setAttribute("idModificar", idModificar);

			// Busco el libro con ese ID y lo mando como par�metro al autor a la p�gina de
			// modificar.
			libro.setIdLibro(Integer.parseInt(idModificar));
			Libro libroModificar = liblog.getOneById(libro);
			request.setAttribute("libroModificar", libroModificar);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarLibros.jsp").forward(request, response);
		} else if (eliminar != null) {
			String idBaja = request.getParameter("eliminar");
			LibroLogic liblog = new LibroLogic();
			Libro libro = new Libro();
			libro.setIdLibro(Integer.parseInt(idBaja));
			Libro libroBaja = liblog.getOneById(libro);
			request.setAttribute("libroBaja", libroBaja);
			request.getRequestDispatcher("WEB-INF/pages/admin/BajaLibros.jsp").forward(request, response);
		}
		request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

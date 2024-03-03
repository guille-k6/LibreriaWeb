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

@WebServlet("/verLibrosDisponibles")
public class verLibrosDisponibles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public verLibrosDisponibles() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String libroBuscado = request.getParameter("nombreLibro");
		if (libroBuscado != null && !libroBuscado.isBlank()) {
			LibroLogic libroLogic = new LibroLogic();
			List<Libro> librosBuscados = libroLogic.getAllLibrosDisponiblesThatMatch(libroBuscado);
			if (librosBuscados.size() == 0) {
				request.setAttribute("mensaje", "No se encontraron libros disponibles");
			}
			request.setAttribute("libros", librosBuscados);
			request.setAttribute("ultimaBusqueda", libroBuscado);
			request.getRequestDispatcher("WEB-INF/pages/user/verLibrosDisponibles.jsp").forward(request, response);
		}
		request.getRequestDispatcher("WEB-INF/pages/user/verLibrosDisponibles.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

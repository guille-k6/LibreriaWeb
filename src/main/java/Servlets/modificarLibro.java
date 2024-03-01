package Servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Entities.Libro;
import Logic.AutorLogic;
import Logic.LibroLogic;

/**
 * Servlet implementation class modificarLibro
 */
@WebServlet("/modificarLibro")
public class modificarLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public modificarLibro() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		Libro libro = new Libro(id);

		LibroLogic liblog = new LibroLogic();
		AutorLogic autlog = new AutorLogic();
		if (opc.equals("editar")) {
			Libro libroModificar = liblog.getOneById(libro);
			request.setAttribute("libroModificar", libroModificar);
			String isbn = request.getParameter("isbn");
			String titulo = request.getParameter("titulo");
			String editorial = request.getParameter("editorial");
			String fechaEdicion = request.getParameter("fechaEdicion");
			String maxDias = request.getParameter("maxDias");
			String idAutor = request.getParameter("autor");

			libro.setIsbn(isbn);
			libro.setTitulo(titulo);
			libro.setEditorial(editorial);
			libro.setAutor(new Autor(Integer.parseInt(idAutor)));

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date parsedDate = dateFormat.parse(fechaEdicion);
				java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
				libro.setFechaEdicion(sqlDate);
			} catch (Exception e) {
				request.setAttribute("listaErrores",
						new LinkedList<String>(List.of("Fecha tiene un formato no valido")));
				request.getRequestDispatcher("WEB-INF/pages/admin/ModificarLibros.jsp").forward(request, response);
			}

			LinkedList<String> errores = liblog.validar(libro, maxDias);
			if (!errores.isEmpty()) { // Hay errores
				request.setAttribute("listaErrores", errores);
				request.getRequestDispatcher("WEB-INF/pages/admin/ModificarLibros.jsp").forward(request, response);
				return;
			}
			libro.setCantDiasMaxPrestamo(Integer.parseInt(maxDias));
			try { // Si esta todo bien, ejecuto el update
				liblog.update(libro);
				request.setAttribute("mensaje", "Modificacion existosa");
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("mensaje", "Error al modificar el Libro: " + libro.getIdLibro());
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
			}

		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
		}
	}

}

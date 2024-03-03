package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Logic.AutorLogic;
import utils.LoggerError;

@WebServlet("/modificarAutor")
public class modificarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public modificarAutor() {
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
		Autor autor = new Autor();
		AutorLogic autlog = new AutorLogic();
		String opc = request.getParameter("opcion");
		String elId = request.getParameter("id");
		int id = Integer.parseInt(elId);
		autor.setIdAutor(id);

		if (opc.equals("editar")) {
			// Guardo el atributo autorModificar para que se carguen los datos en la recarga
			Autor autorModificar = autlog.getOneById(autor);
			request.setAttribute("autorModificar", autorModificar);
			// Traigo los valores de los inputs
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			autor.setApellido(apellido);
			autor.setNombre(nombre);
			LinkedList<String> errores = autlog.validar(autor);
			if (!errores.isEmpty()) { // HAY ERRORES
				request.setAttribute("listaErrores", errores);
				request.getRequestDispatcher("WEB-INF/pages/admin/ModificarAutores.jsp").forward(request, response);
				return;
			}
			// Updateo el autor con sus nuevos datos (nombre y apellido).
			try {
				autlog.update(autor);
				request.setAttribute("estado", "Modificacion existosa");
			} catch (Exception e) {
				request.setAttribute("estado", "Error al modificar el autor: " + autor.getIdAutor());
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);

		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
		}
	}

}

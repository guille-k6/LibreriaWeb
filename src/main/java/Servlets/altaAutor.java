package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Entities.Socio;
import Logic.AutorLogic;
import utils.LoggerError;

/**
 * Servlet implementation class altaAutor
 */
@WebServlet("/altaAutor")
public class altaAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public altaAutor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recupero el usuario y veo que opcion eligi�
		Socio socio = new Socio();
		socio = (Socio) request.getSession().getAttribute("usuario");
		String opc = request.getParameter("opcion");

		switch (opc) {
		// CREAR
		case ("crearAutor"):
			// Recupero el nombre y el apellido del form.
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			// Creo un autor y lo cargo a la DB.
			AutorLogic autlog = new AutorLogic();
			Autor autor = new Autor();
			autor.setNombre(nombre);
			autor.setApellido(apellido);
			LinkedList<String> errores = autlog.validar(autor);
			if (!errores.isEmpty()) { // HAY ERRORES
				request.setAttribute("listaErrores", errores);
				request.getRequestDispatcher("WEB-INF/pages/admin/AltaAutores.jsp").forward(request, response);
				return;
			}
			// NO HAY ERRORES, AGREGO EL AUTOR
			try {
				autlog.add(autor);
				String estado = "Alta existosa";
				request.setAttribute("estado", estado);
			} catch (Exception e) {
				LoggerError.log(e.getStackTrace(), e.getMessage());
			}
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
			break;

		// CANCELAR
		case ("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
			break;
		}
	}

}

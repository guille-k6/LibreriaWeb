package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Logic.AutorLogic;

/**
 * Servlet implementation class bajaAutor
 */
@WebServlet("/bajaAutor")
public class bajaAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public bajaAutor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		String idAutorString = request.getParameter("id");
		int idAutor = Integer.parseInt(idAutorString);
		Autor autor = new Autor();
		autor.setIdAutor(idAutor);

		AutorLogic autlog = new AutorLogic();
		if (opc.equals("eliminar")) {
			try {
				autlog.remove(autor);
				request.setAttribute("estado", "Baja existosa");
			} catch (Exception e) {
				request.setAttribute("estado", "Error al eliminar Autor: " + autor.getIdAutor());
				e.printStackTrace();
			}
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
		} else if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
		}

	}

}

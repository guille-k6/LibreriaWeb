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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Traigo mi ID de autor y la opci�n elegida.

		String opc = request.getParameter("opcion");
		String elId = request.getParameter("id");
		int id = Integer.parseInt(elId);
		Autor autor = new Autor();
		autor.setIdAutor(id);

		AutorLogic autlog = new AutorLogic();
		// Cargo la opcion y confirmo si lo quiere eliminar o no.
		if(opc.equals("eliminar")){

				autlog.remove(autor);
				String estado = "Baja existosa";
				request.setAttribute("estado", estado);
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);

		}else if(opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
		}

	}

}

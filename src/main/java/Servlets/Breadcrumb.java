package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/breadcrumb")
public class Breadcrumb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Breadcrumb() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * Toma el parametro de la request "page" y lo envia hacia esa pagina en
	 * especifico. Por ejemplo, si quiere ir hacia la pagina de ABM de autores, page
	 * debera tener el valor "admin/ABMAutores.jsp"
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("WEB-INF/pages/" + page).forward(request, response);
	}

}

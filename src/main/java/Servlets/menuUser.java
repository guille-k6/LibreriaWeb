package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menuUser")
public class menuUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public menuUser() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		switch (opc) {
		case ("verCuotasImpagas"):
			request.getRequestDispatcher("WEB-INF/pages/user/pagarCuotas.jsp").forward(request, response);
			break;
		case ("verLibros"):
			request.getRequestDispatcher("WEB-INF/pages/user/verLibrosDisponibles.jsp").forward(request, response);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

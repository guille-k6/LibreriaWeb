package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menuAdmin")
public class menuAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public menuAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		switch (opc) {
		case ("abmAutores"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
			break;
		case ("abmLibros"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMLibros.jsp").forward(request, response);
			break;
		case ("abmEjemplares"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
			break;
		case ("cobrarCuotas"):
			request.getRequestDispatcher("WEB-INF/pages/admin/UsuariosAConfirmar.jsp").forward(request, response);
			break;
		case ("prestarEjemplar"):
			request.getRequestDispatcher("WEB-INF/pages/admin/EjemplaresDisponibles.jsp").forward(request, response);
			break;
		case ("buscarSocio"):
			request.getRequestDispatcher("WEB-INF/pages/admin/BuscarSocio.jsp").forward(request, response);
			break;
		case ("abmPoliticaPrestamos"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMPoliticaPrestamos.jsp").forward(request, response);
			break;
		case ("verPrestamos"):
			request.getRequestDispatcher("WEB-INF/pages/admin/VerPrestamos.jsp").forward(request, response);
			break;
		case ("verErrores"):
			request.getRequestDispatcher("WEB-INF/pages/admin/LogErrores.jsp").forward(request, response);
			break;
		}

	}

}

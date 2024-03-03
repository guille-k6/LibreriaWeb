package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pagarCuotasForm
 */
@WebServlet("/pagarCuotasForm")
public class pagarCuotasForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public pagarCuotasForm() {
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

		if (opc.equals("pagar")) {
			String cuotasPagar[] = request.getParameterValues("idcheck"); // Array con los ID de las cuotas a pagar.
			if (cuotasPagar == null) {
				String error = "Selecciona como minimo una cuota";
				request.setAttribute("error", error);
				request.getRequestDispatcher("WEB-INF/pages/user/pagarCuotas.jsp").forward(request, response);
				return;
			}
			request.setAttribute("cuotasPagar", cuotasPagar);
			request.getRequestDispatcher("WEB-INF/pages/user/confirmarPagoCuotas.jsp").forward(request, response);
		}
		if (opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/menuUser.jsp").forward(request, response);
			return;
		}
	}

}

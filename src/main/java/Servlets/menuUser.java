package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;

/**
 * Servlet implementation class menuUser
 */
@WebServlet("/menuUser")
public class menuUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public menuUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Ac� recupero el socio y el par�metro del men�.
		Socio socio = new Socio();
		socio = (Socio)request.getSession().getAttribute("usuario");
		String opc = request.getParameter("opcion");
		// Dependiendo de la opci�n que haya elegido hago algo.
		switch(opc) {
		case("verCuotasImpagas"):
			request.getRequestDispatcher("WEB-INF/pages/user/pagarCuotas.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

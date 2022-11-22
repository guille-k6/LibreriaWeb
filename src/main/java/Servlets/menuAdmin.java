package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.Socio;
import Logic.Login;

/**
 * Servlet implementation class menuAdmin
 */
@WebServlet("/menuAdmin")
public class menuAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public menuAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Ac� recupero el socio y el par�metro del men�.
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("opcion");
		// Dependiendo de la opci�n que haya elegido hago algo.
		switch(opc) {
		case("abmAutores"):
			request.getSession().setAttribute("usuario", socio);
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
			break;
		}
		
	}

}

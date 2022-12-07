package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;

/**
 * Servlet implementation class headerForm
 */
@WebServlet("/headerForm")
public class headerForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public headerForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		Socio elSocio = (Socio)request.getSession().getAttribute("usuario");
		
		
		switch(opc) {
		case("menu"):
			if(elSocio.getAdmin()) {
				request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);					
			}
			else {
				request.getRequestDispatcher("WEB-INF/pages/menuUser.jsp").forward(request, response);	
			}
		
		case("logout"):
			response.sendRedirect("index.html");
			request.getSession().invalidate(); // acá creo que borra el atributo de sesion "usuario"
		}
	}

}

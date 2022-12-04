package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Entities.Socio;
import Logic.AutorLogic;

/**
 * Servlet implementation class pagarCuotasForm
 */
@WebServlet("/pagarCuotasForm")
public class pagarCuotasForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pagarCuotasForm() {
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
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("opcion");
		
		switch(opc) {
		case("pagar"):
			// Recupero el nombre y el apellido del form.
			String valores = request.getParameter("idcheck");

			response.getWriter().append("Served at: ").append(valores);
			break;
		case("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/admin/menuUser.jsp").forward(request, response);
			break;
		}
	}

}

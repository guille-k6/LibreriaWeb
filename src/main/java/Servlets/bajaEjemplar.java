package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Ejemplar;
import Logic.EjemplarLogic;

/**
 * Servlet implementation class bajaEjemplar
 */
@WebServlet("/bajaEjemplar")
public class bajaEjemplar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bajaEjemplar() {
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
		// Traigo mi ID de Libro y la opción elegida.
		
		String opc = request.getParameter("opcion");
		String elId = request.getParameter("id");
		int id = Integer.parseInt(elId);
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setIdEjemplar(id);
		
		EjemplarLogic ejelog = new EjemplarLogic();
		// Cargo la opcion y confirmo si lo quiere eliminar o no.
		if(opc.equals("eliminar")){

				ejelog.remove(ejemplar);
				String estado = "Baja existosa";
				request.setAttribute("estado", estado);
				request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);

		}else if(opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
		}
	}

}

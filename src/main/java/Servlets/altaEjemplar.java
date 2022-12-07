package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Entities.Libro;
import Entities.Ejemplar;
import Entities.Socio;
import Logic.AutorLogic;
import Logic.LibroLogic;
import Logic.EjemplarLogic;

/**
 * Servlet implementation class altaEjemplar
 */
@WebServlet("/altaEjemplar")
public class altaEjemplar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaEjemplar() {
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

		String opc = request.getParameter("opcion");
		
		switch(opc) {
		case("crearEjemplar"):
			// Recupero los datos del ejemplar del form.
			String idLibro = request.getParameter("titulo");
			// Recupero el libro.
			LibroLogic liblog = new LibroLogic();
			Libro libro = new Libro();
			libro.setIdLibro(Integer.parseInt(idLibro));
			libro = liblog.getOneById(libro);
			// Creo un ejemplar con los datos del form.
			EjemplarLogic ejelog = new EjemplarLogic();
			Ejemplar ejemplar = new Ejemplar();
			ejemplar.setLibro(libro);
				
			
			try {
				ejelog.add(ejemplar);
				String estado = "Alta existosa";
				request.setAttribute("estado", estado);	
			}catch (Exception e) {
	            e.printStackTrace();
			}		
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
			break;
		case("cancelar"):
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
			break;
		}
	}

}

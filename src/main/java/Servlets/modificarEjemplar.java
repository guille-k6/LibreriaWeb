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
import Logic.AutorLogic;
import Logic.LibroLogic;
import Logic.EjemplarLogic;

/**
 * Servlet implementation class modificarEjemplar
 */
@WebServlet("/modificarEjemplar")
public class modificarEjemplar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarEjemplar() {
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
		// Traigo mi ID de autor y la opción elegida.
		
		String opc = request.getParameter("opcion");
		String elId = request.getParameter("id");
		int id = Integer.parseInt(elId);
		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setIdEjemplar(id);
		
		EjemplarLogic ejelog = new EjemplarLogic();
		LibroLogic liblog = new LibroLogic();
		// Cargo la opcion y confirmo si lo quiere eliminar o no.
		if(opc.equals("editar")){
			// Recupero el id de libro y lo inicializo con sus valores
			int idLibro = Integer.parseInt(request.getParameter("idLibro"));
			Libro libro = new Libro();
			libro.setIdLibro(idLibro);
			libro = liblog.getOneById(libro);
			
			// Lleno los datos del ejemplar a modificar con sus respectivos valores
			ejemplar.setIdEjemplar(id);
			ejemplar.setLibro(libro);
			
			
			// Updateo el autor con sus nuevos datos (nombre y apellido).
			ejelog.update(ejemplar);
			String estado = "Modificacion existosa";
			request.setAttribute("estado", estado);
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);

		}else if(opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
		}
	}

}

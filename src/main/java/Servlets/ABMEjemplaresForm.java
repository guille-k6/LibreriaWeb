package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Ejemplar;
import Entities.Socio;
import Logic.EjemplarLogic;

/**
 * Servlet implementation class ABMEjemplaresForm
 */
@WebServlet("/ABMEjemplaresForm")
public class ABMEjemplaresForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMEjemplaresForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupero opción y usuario.
		Socio socio = new Socio();		
		socio = (Socio)request.getSession().getAttribute("usuario");		
		String opc = request.getParameter("opcion"); // Asi se llama el name del alta.
		String edit = request.getParameter("editar");
		String eliminar = request.getParameter("eliminar");
		
		if(opc!=null){
			// Lo mando a la sección alta
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaEjemplares.jsp").forward(request, response);			
		}else if(edit!=null){
			// Recupero el ID y lo mando a la sección modificar
			String idModificar = request.getParameter("editar");
			EjemplarLogic ejelog = new EjemplarLogic();
			Ejemplar ejemplar = new Ejemplar();
			
			request.setAttribute("idModificar", idModificar);
			
			//Busco el ejemplar con ese ID y lo mando como parámetro al autor a la página de modificar.
			ejemplar.setIdEjemplar(Integer.parseInt(idModificar));
			Ejemplar ejemplarModificar = ejelog.getOneById(ejemplar);
			request.setAttribute("ejemplarModificar", ejemplarModificar);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarEjemplares.jsp").forward(request, response);			
		}else if(eliminar!=null){
			String idBaja = request.getParameter("eliminar");
			EjemplarLogic ejelog = new EjemplarLogic();
			Ejemplar ejemplar = new Ejemplar();
			
			//Busco el ejemplar con ese ID y lo mando como parámetro al autor a la página de eliminar.
			ejemplar.setIdEjemplar(Integer.parseInt(idBaja));
			Ejemplar ejemplarBaja = ejelog.getOneById(ejemplar);
			request.setAttribute("ejemplarBaja", ejemplarBaja);
			request.getRequestDispatcher("WEB-INF/pages/admin/BajaEjemplares.jsp").forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

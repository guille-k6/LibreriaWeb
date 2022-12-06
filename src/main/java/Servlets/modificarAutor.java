package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Autor;
import Logic.AutorLogic;

/**
 * Servlet implementation class modificarAutor
 */
@WebServlet("/modificarAutor")
public class modificarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modificarAutor() {
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
		Autor autor = new Autor();
		autor.setIdAutor(id);

		
		AutorLogic autlog = new AutorLogic();
		// Cargo la opcion y confirmo si lo quiere eliminar o no.
		if(opc.equals("editar")){
			// Guardo el atributo autorModificar para que se carguen los datos en la recarga
			Autor autorModificar = autlog.getOneById(autor);
			request.setAttribute("autorModificar", autorModificar);
			// Traigo los valores de los inputs
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			autor.setApellido(apellido);
			autor.setNombre(nombre);
			LinkedList<String> errores = autlog.validar(autor);
			if(!errores.isEmpty()) { // HAY ERRORES
				request.setAttribute("listaErrores", errores);
				request.getRequestDispatcher("WEB-INF/pages/admin/ModificarAutores.jsp").forward(request, response);		
				return;
			}
			// Updateo el autor con sus nuevos datos (nombre y apellido).
			try {
			autlog.update(autor);
			String estado = "Modificacion existosa";
			request.setAttribute("estado", estado);
			}catch (Exception e) {
	            e.printStackTrace();
			}	
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);

		}else if(opc.equals("cancelar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMAutores.jsp").forward(request, response);
		}
	}

}

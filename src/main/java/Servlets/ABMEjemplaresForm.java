package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Ejemplar;
import Logic.EjemplarLogic;

@WebServlet("/ABMEjemplaresForm")
public class ABMEjemplaresForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ABMEjemplaresForm() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		String edit = request.getParameter("editar");
		String eliminar = request.getParameter("eliminar");
		String ejemplarBuscado = request.getParameter("nombreEjemplar");

		if (ejemplarBuscado != null && !ejemplarBuscado.isBlank() && opc != null && opc.equals("buscar")) {
			EjemplarLogic ejlog = new EjemplarLogic();
			List<Ejemplar> ejemplaresBuscados = ejlog.getAllEjemplaresThatMatch(ejemplarBuscado);
			if (ejemplaresBuscados.size() == 0) {
				request.setAttribute("mensaje", "No se encontraron ejemplares");
			}
			request.setAttribute("ejemplares", ejemplaresBuscados);
			request.setAttribute("ultimaBusqueda", ejemplarBuscado);
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
		}

		if (opc != null && !opc.equals("buscar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaEjemplares.jsp").forward(request, response);
		} else if (edit != null) {
			String idModificar = request.getParameter("editar");
			EjemplarLogic ejelog = new EjemplarLogic();
			Ejemplar ejemplar = new Ejemplar();
			request.setAttribute("idModificar", idModificar);
			ejemplar.setIdEjemplar(Integer.parseInt(idModificar));
			Ejemplar ejemplarModificar = ejelog.getOneById(ejemplar);
			request.setAttribute("ejemplarModificar", ejemplarModificar);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarEjemplares.jsp").forward(request, response);
		} else if (eliminar != null) {
			String idBaja = request.getParameter("eliminar");
			EjemplarLogic ejelog = new EjemplarLogic();
			Ejemplar ejemplar = new Ejemplar();
			ejemplar.setIdEjemplar(Integer.parseInt(idBaja));
			Ejemplar ejemplarBaja = ejelog.getOneById(ejemplar);
			request.setAttribute("ejemplarBaja", ejemplarBaja);
			request.getRequestDispatcher("WEB-INF/pages/admin/BajaEjemplares.jsp").forward(request, response);
		}
		request.getRequestDispatcher("WEB-INF/pages/admin/ABMEjemplares.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

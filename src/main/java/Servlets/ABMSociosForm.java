package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.SocioLogic;

/**
 * Servlet implementation class ABMSociosForm
 */
@WebServlet("/ABMSociosForm")
public class ABMSociosForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ABMSociosForm() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opc = request.getParameter("opcion");
		String edit = request.getParameter("editar");
		String eliminar = request.getParameter("eliminar");
		String SocioBuscado = request.getParameter("nombreSocio");

		if (SocioBuscado != null && !SocioBuscado.isBlank() && opc != null && opc.equals("buscar")) {
			SocioLogic SocioLogic = new SocioLogic();
			List<Socio> SociosBuscados = SocioLogic.getAllSociosThatMatch(SocioBuscado);
			if (SociosBuscados.size() == 0) {
				request.setAttribute("mensaje", "No se encontraron Socios");
			}
			request.setAttribute("Socios", SociosBuscados);
			request.setAttribute("ultimaBusqueda", SocioBuscado);
			request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
		}
		if (opc != null && !opc.equals("buscar")) {
			request.getRequestDispatcher("WEB-INF/pages/admin/AltaSocios.jsp").forward(request, response);
		} else if (edit != null) {
			String idModificar = request.getParameter("editar");
			request.setAttribute("idModificar", idModificar);
			request.getRequestDispatcher("WEB-INF/pages/admin/ModificarSocios.jsp").forward(request, response);
		} else if (eliminar != null) {
			String idBaja = request.getParameter("eliminar");
			SocioLogic liblog = new SocioLogic();
			Socio Socio = new Socio();
			Socio.setIdSocio(Integer.parseInt(idBaja));
			Socio SocioBaja = liblog.getOneById(Socio);
			request.setAttribute("SocioBaja", SocioBaja);
			request.getRequestDispatcher("WEB-INF/pages/admin/BajaSocios.jsp").forward(request, response);
		}
		request.getRequestDispatcher("WEB-INF/pages/admin/ABMSocios.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

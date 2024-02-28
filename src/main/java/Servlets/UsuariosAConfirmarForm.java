package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Socio;
import Logic.SocioLogic;

@WebServlet("/UsuariosAConfirmarForm")
public class UsuariosAConfirmarForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuariosAConfirmarForm() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idACobrarString = request.getParameter("idSocioCobrar");
		int idSocioCobrar = Integer.parseInt(idACobrarString);
		SocioLogic soclog = new SocioLogic();
		Socio socioACobrar = soclog.getOneById(new Socio(idSocioCobrar));
		request.setAttribute("socioACobrar", socioACobrar);

		request.getRequestDispatcher("WEB-INF/pages/admin/CobrarSocio.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

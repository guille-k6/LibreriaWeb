package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.StringUtils;

import Entities.Socio;
import Logic.Login;

/**
 * Servlet implementation class login
 */
@WebServlet({ "/login", "/Login", "/LogIn", "/LOGIN" }) //
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public login() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Socio socio = new Socio();
		Login login = new Login();
		String error = null;
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		socio.setUsuario(usuario);
		socio.setContrasenia(password);

		if (StringUtils.isNullOrEmpty(usuario.trim()) || StringUtils.isNullOrEmpty(password.trim())) {
			error = "Debe completar usuario y contraseña.";
			request.setAttribute("loginMessage", error);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

		socio = login.validate(socio);

		if (socio != null) {
			request.getSession().setAttribute("usuario", socio);
			if (socio.getAdmin()) {
				request.getRequestDispatcher("WEB-INF/pages/menuAdmin.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("WEB-INF/pages/menuUser.jsp").forward(request, response);
			}
		} else {
			error = "Usuario y/o contraseña no válido.";
			request.setAttribute("loginMessage", error);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}
}

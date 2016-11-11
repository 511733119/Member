package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegisterDao;
import daoImpl.RegisterDaoImpl;

@WebServlet(name = "RegisterCheckEmailAction", urlPatterns = "/registerCheckEmailAction")
public class RegisterCheckEmailAction extends HttpServlet {

	private RegisterDao registerDao = new RegisterDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String email = request.getParameter("email").trim();
		boolean bool = registerDao.checkEmail(email);
		if (bool) {
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
		System.out.println(email);
		System.out.println(bool);
	}

}

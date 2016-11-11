package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.AddPersonalMessageDao;

@WebServlet(name = "CompletePersonalSuccessAction", urlPatterns = "/completePersonalSuccessAction")
public class CompletePersonalSuccessAction extends HttpServlet {

	private AddPersonalMessageDao addPersonalDao = new AddPersonalMessageDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String uname = request.getParameter("uname");
		String sex = request.getParameter("sex");
		String date = request.getParameter("birthday");
		Date birthday = java.sql.Date.valueOf(date);
		String zjlb = request.getParameter("zjlb");
		String zjhm = request.getParameter("zjhm");
		String szsf = request.getParameter("szsf");
		String city = request.getParameter("city");
		System.out.println(city);
		String addr = request.getParameter("addr");
		String yzbm = request.getParameter("yzbm");
		String Tel = request.getParameter("Tel");
		String hompage = request.getParameter("hompage");
		String qq = request.getParameter("qq");
		JsonObject result = addPersonalDao.CompletePersonalMessage(username, uname, sex,
				birthday, zjlb, zjhm, szsf, city, addr,yzbm,Tel,hompage,qq);
		PrintWriter writer = response.getWriter();
		writer.print(result);
		writer.flush();
		request.getRequestDispatcher("/PersonalMember/PersonalMembers_Home.jsp").forward(request, response);
	}

}

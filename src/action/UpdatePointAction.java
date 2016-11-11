package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.MemberDao;

@WebServlet(name = "UpdatePointAction", urlPatterns = "/updatePointAction")
public class UpdatePointAction extends HttpServlet {

	private MemberDao PointDao = new MemberDao();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取编号
		String username = request.getParameter("username");
		System.out.println(username);
		// 获取积分
		Integer point = Integer.valueOf(request.getParameter("point"));
		System.out.println(point);
		JsonObject update = MemberDao.updatepoint(point,username);

		PrintWriter writer = response.getWriter();
		writer.print(update);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}

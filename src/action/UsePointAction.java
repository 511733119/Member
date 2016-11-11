package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.jspsmart.upload.SmartUpload;

import dao.MemberDao;

@WebServlet(name = "UsePointAction", urlPatterns = "/usePointAction")
public class UsePointAction extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		//获取用户名
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("adminName");
		System.out.println(username);
		// 获取商品积分
		Integer point = Integer.valueOf(request.getParameter("point"));
		System.out.println(point);
		JsonObject update =MemberDao.usepoint(point,username);
		PrintWriter writer = response.getWriter();
		writer.print(update);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}

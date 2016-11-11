package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.ProductDao;

@WebServlet(name = "UpdateBoundAction", urlPatterns = "/updateBoundAction")
public class UpdateBoundAction extends HttpServlet {

	private ProductDao BoundDao = new ProductDao();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取编号
		Integer id = Integer.valueOf(request.getParameter("id"));
		// 获取积分
		Integer bound = Integer.valueOf(request.getParameter("bound"));
		JsonObject update = BoundDao.updatebound(bound,id);

		PrintWriter writer = response.getWriter();
		writer.print(update);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}

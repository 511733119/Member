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

@WebServlet(name = "DeleteProductAction", urlPatterns = "/deleteProductAction")
public class DeleteProductAction extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 取得ID
		String ids = request.getParameter("ids");
		JsonObject result = ProductDao.delete(ids);
		PrintWriter writer = response.getWriter();
		writer.print(result);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("非法访问!!!");
	}
}

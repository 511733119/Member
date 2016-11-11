package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.AnnouncementDao;

@WebServlet(name = "DeleteAnnouncementAction", urlPatterns = "/deleteAnnouncementAction")
public class DeleteAnnouncementAction extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 取得ID
		String ids = request.getParameter("ids");
		JsonObject result = AnnouncementDao.delete(ids);
		PrintWriter writer = response.getWriter();
		writer.print(result);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("非法访问!!!");
	}
}

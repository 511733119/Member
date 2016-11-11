package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateAnnouncementAction", urlPatterns = "/updateAnnouncementAction")
public class UpdateAnnouncementAction extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
        //获取编号
        int aid= Integer.valueOf(request.getParameter("id"));
        System.out.println(aid);
        String announcement = request.getParameter("announcement");
        System.out.println(announcement);
//		JsonObject update = AnnouncementDao.update(announcement);
//
//		PrintWriter writer = response.getWriter();
//		writer.print(update);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}

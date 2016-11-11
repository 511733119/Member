package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import dao.AnnouncementDao;

@WebServlet(name = "AddAnnouncementAction", urlPatterns = "/addAnnouncementAction")
public class AddAnnouncementAction extends HttpServlet {

	private AnnouncementDao addAnnouncementDao = new AnnouncementDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		HttpSession session = request.getSession();
		String people = (String) session.getAttribute("adminName");
		String date = request.getParameter("pubdate");
		Date pubdate = Date.valueOf(date);
		String announcement = request.getParameter("announcement");
		JsonObject result = addAnnouncementDao.add(title, people, pubdate,announcement);
		PrintWriter writer = response.getWriter();
		writer.print(result);
		request.getRequestDispatcher("/Manager/ManageAnnouncement.jsp").forward(request, response);
	}

}

package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.AnnouncementDao;

@WebServlet(name="AddCommentAction",urlPatterns="/addCommentAction")
public class AddCommentAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("action");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		
		int aid = Integer.parseInt(request.getParameter("aid"));
		String username = (String) session.getAttribute("adminName");
		String content = request.getParameter("contentArea");
		//添加评论
		AnnouncementDao.addComment(aid, username, content);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("content", content);
		map.put("commentTime", new Date());
		Gson gson = new Gson();
		String s = gson.toJson(map);
		System.out.println(s);
		writer.println(s);
		
	}

}

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

@WebServlet(name = "UpdateNumberAction", urlPatterns = "/updateNumberAction")
public class UpdateNumberAction extends HttpServlet {

	private MemberDao memberDao = new MemberDao();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取会员编号
		String usernumber =request.getParameter("usernumber");
		String username = request.getParameter("username");
		System.out.println(usernumber);
		// 获取会员组编号
		String GroupNumber = request.getParameter("GroupNumber");
		System.out.println(GroupNumber);
		String shenfen ="";
		//1表示个人会员，2表示企业会员
		if("1".equals(GroupNumber)){
			shenfen="个人会员";
		}
		if("2".equals(GroupNumber)){
			shenfen="企业会员";
		}
		JsonObject update = MemberDao.update(GroupNumber,shenfen,usernumber);
		//如果修改个人会员到企业会员组，则删除个人会员表的个人信息
		if("2".equals(GroupNumber)){
			JsonObject delete = MemberDao.deleteUserinfo(username);
		}
		//如果修改企业会员到个人会员组，则删除企业会员表的个人信息
		if("1".equals(GroupNumber)){
			JsonObject delete = MemberDao.deleteCompanyinfo(username);
		}
		PrintWriter writer = response.getWriter();
		writer.print(update);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}

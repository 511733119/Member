package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import dao.AddMemberMessageDao;
import dao.Maps;

@WebServlet(name = "CompleteCompanySuccessAction", urlPatterns = "/completeCompanySuccessAction")
public class CompleteCompanySuccessAction extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();

		// 用户名
		String username = (String) session.getAttribute("adminName");
		String C_Name = request.getParameter("C_Name");
		String C_PostCode = request.getParameter("C_PostCode");
		String C_Province = request.getParameter("C_Province");
		String C_City = request.getParameter("C_City");
		String C_Address = request.getParameter("C_Address");
		String C_ConactName = request.getParameter("C_ConactName");
		String C_Vocation = request.getParameter("C_Vocation");
		String C_WebSite = request.getParameter("C_WebSite");
		String C_size = request.getParameter("C_size");
		String C_Tel = request.getParameter("C_Tel");
		String C_BankName = request.getParameter("C_BankName");
		String C_Capital = request.getParameter("C_Capital")+"万";
		
		AddMemberMessageDao adDao = new AddMemberMessageDao();
		JsonObject json = adDao.addCompanyMessage(Maps.getAddCompanyMessageSql(), 13, username, C_Name, C_PostCode, C_Province, C_City, C_Address, C_ConactName, C_Vocation, C_WebSite, C_size, C_Tel, C_BankName, C_Capital);
		writer.print(json);
		writer.flush();
		request.getRequestDispatcher("/CompanyMember/CompanyMembers_Home.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		PrintWriter writer = response.getWriter();
		writer.print("禁止非法访问");
	}
}

package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Random;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import dao.CardDao;

@WebServlet(name = "RechargeAction", urlPatterns = "/rechargeAction")
public class RechargeAction extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();

		String num = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			int number = random.nextInt(num.length());
			sb.append(num.charAt(number));
		}

		String password = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer sb2 = new StringBuffer();
		for (int i = 0; i < 7; i++) {
			int number = random.nextInt(password.length());
			sb2.append(password.charAt(number));
		}

		String CardNumber = sb.toString();
		String CardPassword = sb2.toString();

		// 充值点数
		int Point = Integer.valueOf(request.getParameter("Point"));

		// 有效期，设置为7天
		Timestamp TimeOutDate = new Timestamp(System.currentTimeMillis() + 24
				* 60 * 60 * 1000 * 7);

		Timestamp AddTime = new Timestamp(System.currentTimeMillis());

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("adminName");

		// 输出字符
		JsonObject json = CardDao.Recharge(CardNumber, CardPassword, Point,
				username, TimeOutDate, AddTime);
		session.setAttribute("zhanghao", CardNumber);
		session.setAttribute("pwd", CardPassword);
		writer.print(json);
		writer.flush();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("禁止非法访问");
	}
}

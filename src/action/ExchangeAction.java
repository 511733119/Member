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
import com.google.gson.JsonPrimitive;

import dao.CardDao;

@WebServlet(name = "ExchangeAction", urlPatterns = "/exchangeAction")
public class ExchangeAction extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();

		// 点卡的点数
		int Point = Integer.valueOf(request.getParameter("Point"));
		// 用户的积分
		int point = 10 * Point;

		HttpSession session = request.getSession();
		// 用户名
		String username = (String) session.getAttribute("adminName");

		String CardNumber = request.getParameter("CardNumber");
//		System.out.println(CardNumber);
		String CardPassword = request.getParameter("CardPassword");
//		System.out.println(CardPassword);
		//如果账号密码正确
		if (CardDao.checkCardNumber(CardNumber, CardPassword)) {
			// 如果兑换点数合理
			if(CardDao.showCurrentPoint(CardNumber)>=Point){
				JsonObject json = CardDao.exchange(CardNumber,point, Point, username);

				writer.print(json);
				writer.flush();
			}else {
				// 如果兑换点数不合理
				JsonObject json = new JsonObject();

				json.add("errorMsg",
						new JsonPrimitive("点卡点数不足，请先充值"));

				writer.print(json);
				writer.flush();
			}
			
		} else {
			// 输出字符
			JsonObject json = new JsonObject();

			json.add("errorMsg",
					new JsonPrimitive("请输入正确的卡号和密码"));

			writer.print(json);
			writer.flush();
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("禁止非法访问");
	}
}

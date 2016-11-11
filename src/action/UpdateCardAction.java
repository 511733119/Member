package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.CardDao;

@WebServlet(name = "UpdateCardAction",urlPatterns = "/updateCardAction")
public class UpdateCardAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	//设置编码格式，防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获取编号
        int CardID = Integer.valueOf(request.getParameter("id"));
        //点卡号
        String CardNumber = request.getParameter("CardNumber");
        //点卡密码
        String CardPassword=request.getParameter("CardPassword");
        //点数
        int Point = Integer.valueOf(request.getParameter("Point"));
        //过期时间
        String TimeOut=request.getParameter("TimeOutDate");

        JsonObject update = CardDao.update(CardID,CardNumber,CardPassword,Point,TimeOut);

        PrintWriter writer = response.getWriter();
        writer.print(update);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

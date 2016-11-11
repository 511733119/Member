package action;

import dao.CardDao;

import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddCardAction",urlPatterns = "/addCardAction")
public class AddCardAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回的消息
        String result="";
        //设置编码格式，防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        //点卡号
        String CardNumber = request.getParameter("CardNumber");;
        //点卡密码
        String CardPassword=request.getParameter("CardPassword");
        //点数
        Integer Point = Integer.valueOf(request.getParameter("Point"));
        //过期时间
        String TimeOut=request.getParameter("TimeOutDate");
//        System.out.println(TimeOut);
        Timestamp TimeOutDate = new Timestamp(System.currentTimeMillis());
        TimeOutDate = Timestamp.valueOf(TimeOut); 
        //添加时间
        Timestamp AddTime = new Timestamp(System.currentTimeMillis());
        //输出字符
        JsonObject json = CardDao.add(CardNumber,CardPassword,Point,TimeOutDate,AddTime);
        writer.print(json);
        writer.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print("禁止非法访问");
    }
}

package action;

import dao.Maps;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import utils.JdbcUtils;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "DisplayCardAction",urlPatterns = "/displayCardAction")
public class DisplayCardAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 Integer page = Integer.valueOf(request.getParameter("page"));
         Integer rows = Integer.valueOf(request.getParameter("rows"));

         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         //先从数据库取出记录条数
         int num=0;
         try {
             ResultSet resultSet = JdbcUtils.getConnection().createStatement().executeQuery(Maps.getCardCount());
             while (resultSet.next()){
                 num=resultSet.getInt(1);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }

         PrintWriter writer = response.getWriter();
         //通过GSON将数据转化成目标JSON格式数据
         HashMap<String, Object> map = new HashMap<String, Object>();

         map.put("rows",JsonUtils.getJsonCarddata(Maps.getQueryCard(), (page-1)*rows, rows));

         map.put("total",num);

         Gson gson = new Gson();

         String s = gson.toJson(map);
         //输出，前台接受结果
         writer.print(s);
//         System.out.println(s);
         writer.flush();

    }
}

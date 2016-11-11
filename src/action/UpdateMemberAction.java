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

@WebServlet(name = "UpdateMemberAction",urlPatterns = "/updateMemberAction")
public class UpdateMemberAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	//设置编码格式，防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获取编号
        String usernumber = request.getParameter("usernumber");
        //安全问题
        String question = request.getParameter("question");
        //答案
        String answer=request.getParameter("answer");
        //安全码
        String safecode = request.getParameter("safecode");

        JsonObject update = MemberDao.updateMember(question,answer,safecode,usernumber);

        PrintWriter writer = response.getWriter();
        writer.print(update);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}

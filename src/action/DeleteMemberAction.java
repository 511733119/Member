package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JdbcUtils;
import utils.JsonUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import dao.MemberDao;

/*
 * 可以多行同时删除的类
 */
@WebServlet(name = "DeleteMemberAction", urlPatterns = "/deleteMemberAction")
public class DeleteMemberAction extends HttpServlet {

	private MemberDao memberDao = new MemberDao();
	Connection connection = JdbcUtils.getConnection();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String usernumbers = request.getParameter("usernumbers");

		//进行字符串分割
		String[] result = usernumbers.split(",");

		String param = "\'" + result[0] + "\'";
		//如果选中多行
		if(result.length>1){
			for (int i = 1; i < result.length; i++) {
				param += ",\'" + result[i] + "\'";
			}
		}
		String sql = "delete from user where usernumber in(" + param + ")";
		deleteMember(sql);

		
		// 若成功，返回相应信息
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("success", new JsonPrimitive(true));
		
		response.getWriter().print(jsonObject);
		response.getWriter().flush();

	}

	// 将所选中的会员删除
	private void deleteMember(String sql) {
		try {
			connection.prepareStatement(sql).execute();
		} catch (SQLException e) {
			e.printStackTrace();
			JsonObject jsonObject = new JsonObject();
			// 若失败，输入指定格式json给前台处理
			jsonObject.add("errorMsg",
					new JsonPrimitive("Some errors occured."));
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("非法访问!!!");
	}
}

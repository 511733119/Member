package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JdbcUtils;
import utils.JsonUtils;

import com.google.gson.Gson;

import dao.Maps;

@WebServlet(name = "DisplayPersonalMemberAction", urlPatterns = "/displayPersonalMemberAction")
public class DisplayPersonalMemberAction extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer rows = Integer.valueOf(request.getParameter("rows"));

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 先从数据库取出记录条数
		int num = 0;
		PrintWriter writer;
		HashMap<String, Object> map = null;
		String user = request.getParameter("user");
		if ("".equals(user) || user == null) {
			try {
				ResultSet resultSet = JdbcUtils.getConnection()
						.createStatement()
						.executeQuery(Maps.getAllpersonalmember());
				while (resultSet.next()) {
					num = resultSet.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			writer = response.getWriter();
			// 通过GSON将数据转化成目标JSON格式数据
			map = new HashMap<String, Object>();

			map.put("rows", JsonUtils.getJsondata(
					Maps.getQuerypersonalmember(), (page - 1) * rows, rows));

			map.put("total", num);

		} else {
			// 计算符合条件的记录数
			String singlePersonalMember = "SELECT count(*) FROM user where GroupNumber=\'1\' and username like \'%"
					+ user + "%\'";
			String querySinglePersonalMember = "SELECT usernumber,username,email,question,answer,safecode FROM user where GroupNumber=\'1\' and username like \'%"
					+ user + "%\' limit ?,?";
			try {

				System.out.println("输入参数时");
				Connection connection = JdbcUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(singlePersonalMember);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					num = resultSet.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			writer = response.getWriter();
			// 通过GSON将数据转化成目标JSON格式数据
			map = new HashMap<String, Object>();

			map.put("rows",
					JsonUtils.getJsondata(querySinglePersonalMember, (page - 1)
							* rows, rows));

			map.put("total", num);

		}
		Gson gson = new Gson();

		String s = gson.toJson(map);
		// 输出，前台接受结果
		writer.print(s);
		// System.out.println(s);
		writer.flush();
	}

}

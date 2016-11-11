package action;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.jspsmart.upload.SmartUpload;

import dao.ProductDao;
import utils.JdbcUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UpdateProductAction", urlPatterns = "/updateProductAction")
public class UpdateProductAction extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置编码格式，防止乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取编号
		int id = Integer.valueOf(request.getParameter("id"));
		// 产品产品编号
		String ProductID = request.getParameter("ProductID");
		// 产品版本
		String Version = request.getParameter("Version");
		// 产品类型
		String PType = request.getParameter("PType");
		// 产品描述
		String Content = request.getParameter("Content");

		PrintWriter writer = response.getWriter();
		JsonObject json = ProductDao.update(id, ProductID, Version, PType,
				Content);
		writer.print(json);
		writer.flush();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
}

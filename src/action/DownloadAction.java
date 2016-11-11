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

import dao.Maps;
import dao.ProductDao;

@WebServlet(name="DownloadAction",urlPatterns="/downloadAction")
public class DownloadAction extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		int point = Integer.valueOf(request.getParameter("point"));
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("adminName");
		ProductDao.updatePoint(point, username);
		String ProductID = request.getParameter("ProductID");
		int id = ProductDao.getProductid(Maps.getProductid(),ProductID);
		System.out.println("id:"+id);
		ProductDao.addToBuyProduct(username, id);
		JsonObject json = ProductDao.updateProductUseNum(id);
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		
	}

}

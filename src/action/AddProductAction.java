package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.jspsmart.upload.SmartUpload;

import dao.ProductDao;

@WebServlet(name = "AdProductAction", urlPatterns = "/addProductAction")
public class AddProductAction extends HttpServlet {

	private ServletConfig config;

	// 初始化Servlet
	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// System.out.println("进入product页面");
		// 变量定义
		int count = 0;
		String path = request.getRealPath("/") + "product/";
		// System.out.println(path);
		PrintWriter writer = response.getWriter();

		// 文件路径
		String filename = "";
		// 产品编号
		String ProductID = "";
		// 产品版本
		String Version = "";
		// 产品类型
		String PType = "";
		// 产品描述
		String Content = "";
		// 所需积分
		int bound = 0;
		// 添加时间
		Date addTime = new Date(System.currentTimeMillis());
		// 输出字符

		SmartUpload mySmartUpload = new SmartUpload();
		// 初始化
		mySmartUpload.initialize(config, request, response);

		try {

			// mySmartUpload.setAllowedFilesList("jpg,png,gif");
			// mySmartUpload.setDeniedFilesList("exe,bat,jsp,htm,html,,");
			// 上载
			mySmartUpload.upload();
			// System.out.println(mySmartUpload.getFiles().getFile(0));
			filename = System.currentTimeMillis() + mySmartUpload.getFiles().getFile(0).getFileName();
			// 保存上载文件到指定目录
			// PATH为form表单提交过来的
			mySmartUpload.getFiles().getFile(0)
					.saveAs(path + System.currentTimeMillis() + mySmartUpload.getFiles().getFile(0).getFileName());
			// 一定要放到mySmartUpload.save(path);后
			ProductID = mySmartUpload.getRequest().getParameter("ProductID");
			Version = mySmartUpload.getRequest().getParameter("Version");
			PType = mySmartUpload.getRequest().getParameter("PType");
			Content = mySmartUpload.getRequest().getParameter("Content");
			bound = Integer.valueOf(mySmartUpload.getRequest().getParameter("bound"));
			// 显示处理结果
			System.out.println(count + "文件已上传至：" + path);

			JsonObject json = ProductDao.add(ProductID, Version, PType, Content, bound, filename, addTime);
			writer.print(json);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print("禁止非法访问");
	}
}

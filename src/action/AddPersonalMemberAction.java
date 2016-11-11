package action;

import dao.MemberDao;
import dao.RegisterDao;
import daoImpl.RegisterDaoImpl;

import com.google.gson.JsonObject;
import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.JdbcUtils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddPersonalMemberAction", urlPatterns = "/addPersonalMemberAction")
public class AddPersonalMemberAction extends HttpServlet {

	private ServletConfig config;

	// 初始化Servlet
	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码格式，防止乱码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 变量定义
		int count = 0;
		String path = request.getRealPath("/") + "upload/";

		PrintWriter writer = response.getWriter();

		String filename = null;
		String username = "";
		String password = "";

		String question = "";
		String answer = "";
		String safecode = "";
		String email = "";

		SmartUpload mySmartUpload = new SmartUpload();
		// 初始化
		mySmartUpload.initialize(config, request, response);

		try {

			mySmartUpload.setAllowedFilesList("jpg,png,gif");
			mySmartUpload.setDeniedFilesList("exe,bat,jsp,htm,html,,");
			// 上载
			mySmartUpload.upload();
			filename = System.currentTimeMillis() + mySmartUpload.getFiles().getFile(0).getFileName();
			// 保存上载文件到指定目录
			// PATH为form表单提交过来的
			mySmartUpload.getFiles().getFile(0)
					.saveAs(path + System.currentTimeMillis() + mySmartUpload.getFiles().getFile(0).getFileName());
			// 一定要放到mySmartUpload.save(path);后
			username = mySmartUpload.getRequest().getParameter("username");
			password = mySmartUpload.getRequest().getParameter("password");
			String md5password = JdbcUtils.MD5(password);
			question = mySmartUpload.getRequest().getParameter("question");
			answer = mySmartUpload.getRequest().getParameter("answer");
			safecode = mySmartUpload.getRequest().getParameter("safecode");
			email = mySmartUpload.getRequest().getParameter("email");
			String GroupNumber = "1";
			String shenfen = "个人会员";
			RegisterDao registerDao = new RegisterDaoImpl();
			String usernumber = registerDao.getNewId();
			// 显示处理结果
			System.out.println(count + "文件已上传至：" + path);

			JsonObject json = MemberDao.add(usernumber, username, md5password, question, answer, safecode, filename,
					email, GroupNumber, shenfen);

			writer.print(json);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		PrintWriter writer = response.getWriter();
		writer.print("禁止非法访问");
	}
}

package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartFiles;
import com.jspsmart.upload.SmartUpload;

import dao.RegisterDao;
import daoImpl.RegisterDaoImpl;

@WebServlet(name = "RegisterSuccessAction", urlPatterns = "/registerSuccessAction")
public class RegisterSuccessAction extends HttpServlet {

	private RegisterDao registerDao = new RegisterDaoImpl();

	private ServletConfig config;

	// 初始化Servlet
	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 变量定义
		int count = 0;
		String path = request.getRealPath("/") + "upload/";
		String username = "";
		String password = "";

		String question = "";
		String answer = "";
		String safecode = "";
		String email = "";
		String shenfen = "";
		String filename = null;
		SmartUpload mySmartUpload = new SmartUpload();
		// 初始化
		mySmartUpload.initialize(config, request, response);

		try {
			
			mySmartUpload.setAllowedFilesList("jpg,png,gif");
			mySmartUpload.setDeniedFilesList("exe,bat,jsp,htm,html,,");
			// 上载
			mySmartUpload.upload();
			filename =System.currentTimeMillis() + mySmartUpload.getFiles().getFile(0).getFileName();
			// 保存上载文件到指定目录
			// PATH为form表单提交过来的
			mySmartUpload.getFiles().getFile(0).saveAs(path+System.currentTimeMillis() + mySmartUpload.getFiles().getFile(0).getFileName());
			// 一定要放到mySmartUpload.save(path);后
			username = mySmartUpload.getRequest().getParameter("username");
			password = mySmartUpload.getRequest().getParameter("password");
			String md5password = registerDao.MD5(password);
			question = mySmartUpload.getRequest().getParameter("question");
			answer = mySmartUpload.getRequest().getParameter("answer");
			safecode = mySmartUpload.getRequest().getParameter("safecode");
			email = mySmartUpload.getRequest().getParameter("email");
			shenfen = mySmartUpload.getRequest().getParameter("shenfen");
			int GroupNumber = 0;
			if ("个人会员".equals(shenfen)) {
				GroupNumber = 1;
			} else if ("企业会员".equals(shenfen)) {
				GroupNumber = 2;
			}
			// 显示处理结果
			System.out.println(count + "文件已上传至：" + path);

			int result = registerDao.addUser(username, md5password, question,
					answer, safecode, filename, email, GroupNumber, shenfen);

			HttpSession session = request.getSession();
			session.setAttribute("adminName", username);
			if (GroupNumber == 1) {
				request.getRequestDispatcher("/PersonalMember/PersonalMembers_Home.jsp")
						.forward(request, response);
			} else {
				request.getRequestDispatcher("/CompanyMember/CompanyMembers_Home.jsp")
						.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

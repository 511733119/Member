package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import utils.JdbcUtils;
import dao.LoginDao;
import dao.MemberDao;
import dao.RegisterDao;
import daoImpl.RegisterDaoImpl;

@WebServlet(name = "loginCheckAction", urlPatterns = "/loginCheckAction")
public class LoginCheckAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private LoginDao loginDao = new LoginDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 获取所填验证码
		String code = request.getParameter("checkcode");
		// 获取图片中验证码
		String sessioncode = (String) session.getAttribute("sRand");

		// 如果验证码正确
		if (code.equals(sessioncode)) {

			try {
				ResultSet rs = loginDao.ManagerLogin(username);
				ResultSet rs2 = loginDao.PersonalMemberLogin(username);
				ResultSet rs3 = loginDao.CompanyMemberLogin(username);
				// 如果存在该用户名
				if (rs.next()) {
					// 判断用户输入密码，与 数据库检索密码账号是否一致
					if (JdbcUtils.MD5(password).equals(rs.getString(1))) {

						// 如果登录成功
						// 首先判断用户是否点击了checkbox记住登录状态
						String s = request.getParameter("remember");
						if (s != null) {
							Cookie[] c = saveCookie(username, password);
							response.addCookie(c[0]);
							response.addCookie(c[1]);
						}

						session.setAttribute("adminName", rs.getString(2));
						// 跳转到主页
						request.getRequestDispatcher("/Manager/Managers_Home.jsp")
								.forward(request, response);
					} else {
						// 如果用户名和密码不匹配
						// 跳转至login。jsp页面
						request.getRequestDispatcher(
								"/login.jsp?errorMessage=用户名或密码错误!").forward(
								request, response);
					}

				} else if (rs2.next()) { // 如果输入的用户名是存在的会员的用户名

					if (JdbcUtils.MD5(password).equals(rs2.getString(1))) {
						//session名称为username，记录该会员登录的时间
						if (session.getAttribute(username) == null) {
							session.setAttribute(username, new Date().getTime());
							session.setAttribute("adminName", rs2.getString(2));
							// 每登录一次，会员加2个积分
							System.out.println("logindao:"+username);
							loginDao.update(username);
							// 跳转到主页
							request.getRequestDispatcher(
									"/PersonalMembers_Home.jsp").forward(
									request, response);
						} else {
							long time = ((new Date().getTime()) - ((Long) (session
									.getAttribute(username)))) / 1000;
							System.out.println("time:" + time);
							// 如果半小时内已经登录过，则不能再登录
							if (time < 10) {
								request.getRequestDispatcher(
										"/login.jsp?errorMessage=不能在半小时内连续登录两次!")
										.forward(request, response);
							} else {
								// 如果登录成功
								// 首先判断用户是否点击了checkbox记住登录状态
								String s = request.getParameter("remember");
								if (s != null) {
									Cookie[] c = saveCookie(username, password);
									response.addCookie(c[0]);
									response.addCookie(c[1]);
								}

								session.setAttribute("adminName",
										rs2.getString(2));

								// 每登录一次，会员加2个积分
								loginDao.update(username);
								// 跳转到主页
								request.getRequestDispatcher(
										"/PersonalMember/PersonalMembers_Home.jsp").forward(
										request, response);
							}
						}

					} else {
						// 如果用户名和密码不匹配
						// 跳转至login。jsp页面
						request.getRequestDispatcher(
								"/login.jsp?errorMessage=用户名或密码错误!").forward(
								request, response);
					}
				} else if (rs3.next()) { // 如果输入的用户名是存在的会员的用户名

					if (JdbcUtils.MD5(password).equals(rs3.getString(1))) {
						//session名称为username，记录该会员登录的时间
						if (session.getAttribute(username) == null) {
							session.setAttribute(username, new Date().getTime());
							session.setAttribute("adminName", rs3.getString(2));
							// 每登录一次，会员加2个积分
							loginDao.update(username);
							// 跳转到主页
							request.getRequestDispatcher(
									"/CompanyMember/CompanyMembers_Home.jsp").forward(
									request, response);
						} else {
							int time = (int) (((new Date().getTime()) - ((Long) (session
									.getAttribute(username)))) / 1000);
							System.out.println("time:" + time);
							// 如果半小时内已经登录过，则不能再登录(根据情况修改)
							if (time < 10) {
								request.getRequestDispatcher(
										"/login.jsp?errorMessage=不能在半小时内连续登录两次!")
										.forward(request, response);
							} else {
								// 如果登录成功
								// 首先判断用户是否点击了checkbox记住登录状态
								String s = request.getParameter("remember");
								if (s != null) {
									Cookie[] c = saveCookie(username, password);
									response.addCookie(c[0]);
									response.addCookie(c[1]);
								}

								session.setAttribute("adminName",
										rs3.getString(2));
								// 每登录一次，会员加2个积分
								loginDao.update(username);
								// 跳转到主页
								request.getRequestDispatcher(
										"/CompanyMember/CompanyMembers_Home.jsp").forward(
										request, response);
							}
						}

					} else {
						// 如果用户名和密码不匹配
						// 跳转至login。jsp页面
						request.getRequestDispatcher(
								"/login.jsp?errorMessage=用户名或密码错误!").forward(
								request, response);
					}
				} else {
					// 如果查询不到此用户名
					// 跳转至login。jsp页面
					request.getRequestDispatcher(
							"/login.jsp?errorMessage=没有该用户名!").forward(request,
							response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 如果验证码错误
			// 跳转至login。jsp页面
			request.getRequestDispatcher("/login.jsp?errorMessage=验证码错误!")
					.forward(request, response);
		}
	}

	/*
	 * 保持用户名和密码保存在cookies中,使用URLEncoder解决无法在cookie中保持中文的问题,先转码，在解码
	 */
	private Cookie[] saveCookie(String username, String password) {
		String usern;
		String pwd;
		Cookie usernamecookie = null;
		Cookie passwordcookie = null;
		try {
			usern = URLEncoder.encode(username, "utf-8");
			pwd = URLEncoder.encode(password, "utf-8");
			usernamecookie = new Cookie("username", usern);
			passwordcookie = new Cookie("password", pwd);
			usernamecookie.setMaxAge(7 * 24 * 60 * 60);// 设置最长生存时间为7天
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Cookie[] c = new Cookie[2];
		c[0] = usernamecookie;
		c[1] = passwordcookie;
		return c;
	}

}

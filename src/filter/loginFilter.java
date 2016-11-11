package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginFilter implements Filter {
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("adminName");
		//获取请求中的Servletpath
        String servletpath = req.getServletPath();
        //如果用户登录的是首页或者是login或者是执行登录操作的话
        // 将请求直接转发给下一个组件进行处理
        //对于其他请求则都进行权限校验
        if ((servletpath.equals("/login.jsp"))
        		|| (servletpath.equals("/loginCheckAction"))
				||(servletpath.contains(".css"))
				||servletpath.contains(".jpg")||servletpath.contains(".png")){
            chain.doFilter(req, resp);
        }else  if (username != null && !"".equals(username)){
            chain.doFilter(req, resp);
            //登录失败,则跳转到登录界面
        } 
      //用户处于登录状态则可以直接进行访问
         else {
        	response.sendRedirect("login.jsp");
		}
		
//		if(username==null || "".equals(username)){
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
//			dispatcher.forward(request, resp);
			
//		}else {
//			chain.doFilter(req, resp);
//		}
	}

	public void destroy() {
		
	}
}

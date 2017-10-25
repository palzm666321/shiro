package cn.mldn.mldnshiro.servlet;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

@SuppressWarnings("serial")
@WebServlet("/login.action") 
public class MemberLoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/login.jsp" ;
		String mid = request.getParameter("mid") ;	// 表单发送来的mid参数
		String password = request.getParameter("password") ;	// 表单发送来的信息
		// 在Shiro里面，如果要进行用户名和密码的验证，则需要将数据包装在一个认证的Token类里面
		AuthenticationToken token = new UsernamePasswordToken(mid, password) ;
		try {	// 如果没有异常，表示登录成功，登录成功之后应该跳转到一个欢迎页面
			SecurityUtils.getSubject().login(token);
			path = "/pages/welcome.jsp" ;	// 欢迎首页
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher(path).forward(request, response); 
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}

package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import DBTool.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



@WebServlet(name = "login_servlet", urlPatterns = { "/login_servlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	Logger logger=Logger.getLogger(LoginServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		session.setAttribute("username",username);
		session.setAttribute("password",password);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection conn = DBUtil.getConnection(username,password);
			if(conn == null) {
				String a = URLEncoder.encode("用户名或密码错误！请重新输入", "UTF-8");
				out.print(
						"<script>alert(decodeURIComponent('" + a + "') );window.location.href='login.jsp'</script>");
				out.flush();
				out.close();
			}
			Statement st = conn.createStatement();
			String sql = "select * from user where username='" + username + "' and password='" + password
					+ "'";
			ResultSet rs = st.executeQuery(sql);
			// 用户存在且为普通用户
			if (rs.next()) {
				if (rs.getInt("admin") == 0) {
					logger.info("普通用户"+username+"登录成功。");
					// 重定向和传参
					request.setAttribute("username", username);
					request.setAttribute("password", password);
					request.getRequestDispatcher("/booksale.jsp").forward(request, response);
				}
				// 用户存在且为管理员
				else if (rs.getInt("admin") == 1) {
					logger.info("管理员"+username+"登录成功。");
					String a = URLEncoder.encode("您将进入管理员页面", "UTF-8");
					out.print("<script>alert(decodeURIComponent('" + a
							+ "') );window.location.href='admin.jsp'</script>");
					out.flush();
					out.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.Close();
		}
	}
}
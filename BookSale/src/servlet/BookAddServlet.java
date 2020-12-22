package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
//import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import DBTool.DBUtil;

@WebServlet(name = "book_add_servlet", urlPatterns = { "/bookadd" })
public class BookAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BookAddServlet() {
		super();
	}
	Logger logger=Logger.getLogger(BookAddServlet.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 向服务器发送请求获取到参数
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String remain = request.getParameter("remain");
		String username = "";
		String password = "";
		HttpSession session = request.getSession();
		username = (String)session.getAttribute("username");
		password = (String)session.getAttribute("password");
		try {
			Connection conn = DBUtil.getConnection(username, password);
			if(conn == null) {
				String a = URLEncoder.encode("请登录！", "UTF-8");
				out.print(
						"<script>alert(decodeURIComponent('" + a + "') );window.location.href='login.jsp'</script>");
				out.flush();
				out.close();
			}
			Statement st = conn.createStatement();
			String sql = "select * from booklist where name='" + name + "'";
			String sql1 = "insert into booklist(name,price,remain) values('" + name + "','" + price + "','"
					+ remain + "')";
			ResultSet rs = st.executeQuery(sql);
			// 该书已存在
			if (rs.next()) {
				String a = URLEncoder.encode("该书已存在，无需添加！", "UTF-8");
				logger.info("书籍"+ name + "已存在，无需添加。");
				out.print(
						"<script>alert(decodeURIComponent('" + a + "') );window.location.href='booksale.jsp'</script>");
				out.flush();
				out.close();

			}
			// 该书尚未添加
			else {
				st.execute(sql1);
				String a = URLEncoder.encode("添加成功！", "UTF-8");
				logger.info("添加书籍"+ name +"成功,该书价格为"+price+"，库存"+remain+"本。");
				out.print(
						"<script>alert(decodeURIComponent('" + a + "') );window.location.href='booksale.jsp'</script>");
				out.flush();
				out.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.Close();
		}
	}
}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import DBTool.DBUtil;
import database.Book;

@WebServlet(name = "book_search_servlet", urlPatterns = { "/booksearch" })
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookSearchServlet() {
		super();
	}
	Logger logger=Logger.getLogger(BookSearchServlet.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> booklist = new ArrayList<Book>();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String username = "";
		String password = "";
		HttpSession session = request.getSession();
		username = (String)session.getAttribute("username");
		password = (String)session.getAttribute("password");
		System.out.print(username);
		System.out.print(password);
		try {
			Connection conn = DBUtil.getConnection(username,password);
			if(conn == null) {
				String a = URLEncoder.encode("请登录！", "UTF-8");
				out.print(
						"<script>alert(decodeURIComponent('" + a + "') );window.location.href='login.jsp'</script>");
				out.flush();
				out.close();
			}
			Statement st = conn.createStatement();
			String sql = "select* from booklist";
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				/* System.out.print("找到！！"); */
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setName(rs.getString("name"));
				b.setPrice(rs.getFloat("price"));
				b.setRemain(rs.getInt("remain"));
				booklist.add(b);
			}
			if(booklist.size()==0) {
				logger.info("库存暂无书籍。");
				String a = URLEncoder.encode("暂无书籍", "UTF-8");
				out.print("<script>alert(decodeURIComponent('" + a
						+ "');</script>");
				out.flush();
				out.close();
			}
			else {
				logger.info("执行查询操作，成功返回库存所有书籍的信息。");
				request.setAttribute("booklist", booklist);
				request.getRequestDispatcher("/booksale.jsp").forward(request, response);
			}		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.Close();
		}
	}
}
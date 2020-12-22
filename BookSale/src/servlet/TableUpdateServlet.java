package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
//import java.io.Writer;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import DBTool.DBUtil;

@WebServlet(name = "table_add_servlet", urlPatterns = { "/table_update" })
public class TableUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public TableUpdateServlet() {
		super();
	}

	Logger logger = Logger.getLogger(TableUpdateServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 向服务器发送请求获取到参数
		String User = request.getParameter("User");
		String operation = request.getParameter("operation");

		String priv_0 = request.getParameter("priv");
		String priv = "";// 权限
		switch (priv_0) {
		case "s":
			priv = "Select";
			break;
		case "i":
			priv = "Insert";
			break;
		case "u":
			priv = "Update";
			break;
		case "si":
			priv = "Select,Insert";
			break;
		case "su":
			priv = "Select,Update";
			break;
		case "iu":
			priv = "Insert,Update";
			break;
		case "siu":
			priv = "Select,Insert,Update";
			break;
		}
		String username = "";
		String password = "";
		HttpSession session = request.getSession();
		username = (String) session.getAttribute("username");
		password = (String) session.getAttribute("password");
		try {
			Connection conn = DBUtil.getConnection(username, password);
			if (conn == null) {
				String a = URLEncoder.encode("请登录！", "UTF-8");
				out.print("<script>alert(decodeURIComponent('" + a + "') );window.location.href='login.jsp'</script>");
				out.flush();
				out.close();
			}
			Statement st = conn.createStatement();
			String sql="";
			if (operation.equals("添加")) {
				sql = "grant "+ priv + " on booklist to '" + User + "'@'localhost'";
			} else if (operation.equals("删除")) {
				sql = "revoke "+ priv + " on booklist from '" + User + "'@'localhost'";
			} else {
				String a = URLEncoder.encode("输入格式错误！", "UTF-8");
				out.print("<script>alert(decodeURIComponent('" + a + "') );window.location.href='admin.jsp'</script>");
				out.flush();
				out.close();
			}
			System.out.print(sql);
			st.execute(sql);
			logger.info(operation + "用户" + User + "对表格booklist的权限"+priv);
			String a = URLEncoder.encode("权限"+operation+"成功！", "UTF-8");
			out.print("<script>alert(decodeURIComponent('" + a + "') );window.location.href='admin.jsp'</script>");
			out.flush();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.Close();
		}
	}
}

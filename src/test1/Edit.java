package test1;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		//requestをHttpServletRequest型にキャスト
		HttpServletRequest req = (HttpServletRequest) request;
		String update = req.getParameter("update");
		String userid = request.getParameter("id");

		Dao dao = null;
		try {
			dao = new Dao();
			dao.editData(update,userid);
		} catch (SQLException e) {
			//TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=UTF-8");
		ServletContext con = getServletContext();
		RequestDispatcher a = con.getRequestDispatcher("/Time");
		a.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

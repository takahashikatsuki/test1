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
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		ServletContext con = getServletContext();
		RequestDispatcher a = con.getRequestDispatcher("/Time");
		a.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		//requestをHttpServletRequest型にキャスト
		HttpServletRequest req = (HttpServletRequest) request;
		String hour = req.getParameter("hour");
		String minute = req.getParameter("minute");
		String time = hour + ":" + minute;
		session.setAttribute("time", time);
		String date = (String) session.getAttribute("date");
		String yotei = request.getParameter("schedule");
		System.out.println(yotei);

		Dao dao = null;

		try {
			dao = new Dao();
			dao.insertData(date, time, yotei);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		doGet(request, response);
	}

}

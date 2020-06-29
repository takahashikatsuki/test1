package test1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import dto.dto;

/**
 * Servlet implementation class Time
 */
@WebServlet("/Time")
public class Time extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub


		response.setContentType("text/html; charset=UTF-8");
		ServletContext con = getServletContext();
		RequestDispatcher time = con.getRequestDispatcher("/schedule.jsp");
		time.forward(request, response);
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(false);
	//calender.javaでsetした年月をsessionから受け取る
	int year = (int) session.getAttribute("YEAR");
	int month = (int) session.getAttribute("MONTH");
	//calender.jspで押された番号の数字をget
	String day = request.getParameter("day");
	//session.setAttribute("DAY", day);
	String date = null;

	//dayがおされなかったらsessionからdateを受け取る
	if (day == null) {
		date = (String) session.getAttribute("date");
	}else {
		date = year + "-" + month + "-" + day;
		session.setAttribute("date", date);
	}

	Dao dao = null;

	ArrayList<dto> sche = null;
	try {
		dao = new Dao(); //SQLにアクセス
		sche = dao.getSchedule(date);
	} catch (SQLException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
	request.setAttribute("list", sche);

	ArrayList<String> intList = new ArrayList<String>();
	ArrayList<String> intList2 = new ArrayList<String>();

	for (int i = 0; i < 24; i++) {
		String a = String.valueOf(i);
		if (a.length() == 1) {
			intList.add("0" + a);
		} else {
			intList.add(a);
		}
	}
	request.setAttribute("hour", intList);

	for (int n = 0; n < 60; n++) {
		String b = String.valueOf(n);
		if (b.length() == 1) {
			intList2.add("0" + b);
		} else {
			intList2.add(b);
		}
	}
	request.setAttribute("minute", intList2);
	doGet(request,response);
}
}
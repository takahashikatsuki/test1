package test1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/calendar"})
public class calender extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		request.setCharacterEncoding("utf-8");
	// セッションの取得(なければnullが返ってくる)
		HttpSession session = request.getSession(true);
	// ここに処理を記入してください
	// セッションの破棄
		//if(session != null) session.invalidate();
	// ここに処理を記入してください

		if(request.getParameter("year") == "" || request.getParameter("month") == ""){
	// ログイン失敗時、ログアウト時、不正操作時以外の場合
				request.setAttribute("message", "Please enter the year and month");//messageがnullの場合

				response.setContentType("text/html; charset=UTF-8");
				ServletContext context = getServletContext();
				RequestDispatcher dis = context.getRequestDispatcher("/input.jsp");
				dis.forward(request, response);

		}else {
		//1月から12月までの末日を格納する配列を用意する
		int lastDayTbl[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		//日月火水木金土を表示する可変配列を用意する
		List<Object> list1 = new ArrayList<Object>();
		//カレンダーの日にちを格納する可変配列を用意する
		List<Object> list2 = new ArrayList<Object>();
		//日月火水木金土を格納する配列を用意する
		String dayOfWeekTbl[] = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		try{
			//カレンダークラスのインスタンスを作る
			Calendar calendar = Calendar.getInstance();
			//カレンダークラスのインスタンスから月の初日を取り出します
			int day = calendar.get(Calendar.DATE);
			//int y = Integer.parseInt("2020");
			int y = Integer.parseInt(request.getParameter("year"));
			//「YEAR」というリクエストオブジェクトにカレンダーを表示したい年を格納
			session.setAttribute("YEAR",y);
			//int m = Integer.parseInt("1");
			int m = Integer.parseInt(request.getParameter("month"));
			//「MONTH」というリクエストオブジェクトにカレンダーを表示したい月を格納
			session.setAttribute("MONTH",m);
			//月の最初の日の曜日を取得
			int dayofweek = getDayOfWeek(y,m,1);
			//月の最後の日を取得
			int lastDay = lastDayTbl[m-1];
			//2月でかつ閏年だったら月の最終日を29日とする
			if(m==2&&isLeapYear(y) != 0){
				lastDay = 29;
			}
			//カレンダーの最初の行に日月火水木金土を表示
			for(int w=0;w<7;w++){
				list1.add("<td>" + dayOfWeekTbl[w] + "</td>");
			}
			//カレンダーの最初の行を</tr>で閉じる
			list1.add("</tr>");
			//「space1」というリクエストオブジェクトに日月火水木金土を格納
			request.setAttribute("space1", list1);
			//カレンダーの日にちが始まる行を<tr>で始める
			list2.add("<tr>");
			//最初の日にちまでスペースで埋める
			for(int u=0;u<dayofweek;u++){
				list2.add("</td><td>");
			}
			//
			//表示する年月の1日から最後の日まで表示する
			for(day =1;day <=lastDay;day++,dayofweek++){
				list2.add("<td><input class=\"submit\" type=\"submit\" name=\"day\" value=\""+ day + "\"></td>");
				//土曜日になったら</tr></tr>で改行
				if(dayofweek%7==6){
					list2.add("</tr><tr>");
				}
			}
			//「space2」というリクエストオブジェクトに表示する年月の1日から最後の日までを格納
			request.setAttribute("space2", list2);
		}catch(Exception e){
			throw new ServletException(e);
		}
		//calendar.jspに「space1」というオブジェクトと「space2」というオブジェクトを渡す
		request.getRequestDispatcher("/calendar.jsp").forward(request, response);
	}
	}
	//月の最初の日の曜日をかえす
	private int getDayOfWeek(int year,int month,int day){
		//何曜日かを表す変数
		int dayofweek;
		//1月と2月の場合
		if(month < 3){
			year--;
			month += 12;
		}
		//月の最初の日の曜日を表す公式
		dayofweek = (year + year/4 -year/100 + year/400 + (13*month+8)/5 +day)%7;
		return dayofweek;
	}
	//入力された年が閏年だったら1を返して閏年でなかったら0をかえす
	private int isLeapYear(int year){
		int leepYear = 0;
		//2011年は平年		4で割り切れません
		//2012年はうるう年	4で割り切れ、100では割り切れません
		//2000年はうるう年	4で割り切れ、100で割り切れますが、400で割り切れます
		//2100年は平年 		4で割り切れ、100で割り切れ、400では割り切れません
		if (year%4==0 && year%100 !=0 || year%400==0){
			leepYear =1;
		}
		return leepYear;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int n = 0;

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession(true);


		if(n > 0) {
			response.sendRedirect("http://localhost:8080/database/DBServlet");
		}else {
			request.setAttribute("message", "スケジュールが表示できませんでした");
			doGet(request, response);
		}
	}
}

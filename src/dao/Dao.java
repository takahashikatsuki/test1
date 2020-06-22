package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import dto.dto;

public class Dao {
	private Connection con;
	private String sql;

	/**
	 * DB接続コンストラクタ<br>
	 * インスタンス化時にDB接続が行われる
	 * @throws SQLException
	 */
	public Dao() throws SQLException {//Daoクラスのコンストラクタ/データーベースに接続するためのコンストラクタ
		// ここに処理を記入してください
		String url = "jdbc:mysql://localhost:3306/schedule?serverTimezone=UTC";//dataベースがある場所
		String user = "root";//ユーザー名
		String pass = "P@ssw0rd";//パスワード
		con = DriverManager.getConnection(url, user, pass);//3つの仮引数の情報を使ってデーターベースへアクセスする
		System.out.println("Connection success!");//接続成功するとコンソールに現れる
	}

	/**
	 *
	 */
	public void close() {
		try {
			if (con != null)
				con.close();//DB接続を切るためのメソッド
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<dto> getSchedule(String date) throws SQLException {

		PreparedStatement ps = null;
		sql = "select * from sd where date = ?";
		ps = con.prepareStatement(sql);//
		ps.setString(1, date);
		ArrayList<dto> list = search(ps);

	Comparator<dto> comparator = Comparator.comparing(dto::getTime);

	return(ArrayList<dto>)list.stream().sorted(comparator).collect(Collectors.toList());

	}

private ArrayList<dto> search(PreparedStatement ps) throws SQLException {

		ResultSet rs = null;
		ArrayList<dto> list = null;
        try {
            rs = ps.executeQuery();
            list = new ArrayList<>();
            dto dto;
            while(rs.next()) {
                dto = new dto();
                dto.setDate(rs.getString("date"));
                dto.setTime(rs.getString("time"));
                dto.setSchedule(rs.getString("schedule"));
                dto.setId(rs.getInt("id"));
                list.add(dto);
            }
        }finally {
            ps.close();
        }
        return list;
    }

	/**
	 * データ登録メソッド<br>
	 * SQL文とパラメータをexecuteUpdateメソッドに渡す
	 * @param input (受け取った入力値)
	 * @param request
	 * @return 成功件数
	 * @throws SQLException
	 */
	public int insertData(String date, String time, String yotei) throws SQLException {//取得したデータの登録するためのメソッド/String inputはユーザーが打ち込んだ内容/int型として戻ってくる
		PreparedStatement ps = null;//psSQLをどのデータベースにどのようなクエリを送るのか定義
		int n = 0;//トライブロックの中にいると戻り値として認識されない

		try {
			sql = "INSERT INTO sd (date, time, schedule)VALUES (?,?,?)";//?はユーザーが打ち込んだ値
			ps = con.prepareStatement(sql);
			ps.setString(1, date);//
			ps.setString(2, time);
			ps.setString(3, yotei);
			n = ps.executeUpdate();//sqlの実行文
		} finally {
			ps.close();
		}
		// ここに処理を記入してください
		return n;//コード認証が成功した数を返す戻り式
	}

	public int deleteData(String userid) throws SQLException {
		String sql = "delete from sd where id = ?";
		return executeUpdate(sql, userid);
		// ここに処理を記入してください
	}

	private int executeUpdate(String sql, String param) throws SQLException {
		// ここに処理を記入してください
		PreparedStatement ps = null;
		int n = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, param);
			n = ps.executeUpdate();
		} finally {
			ps.close();
		}
		return n;
	}

	public int editData(String userid) throws SQLException {
		String sql = "update from sd where id = ?";
		PreparedStatement ps = null;
		int n = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			n = ps.executeUpdate();
		} finally {
			ps.close();
		}
		return n;

	}

}

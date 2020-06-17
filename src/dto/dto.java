package dto;

/**
 * メッセージ情報をまとめて保持するクラス<br>
 * データ検索時に使用され、以下のフィールドを持つ<br>
 * ・投稿ID<br>
 * ・投稿内容<br>
 * ・投稿日時<br>
 * @author user
 *
 */
public class dto { //MessageDtoが一行分のデータを持っている

	/**
	 * 投稿ID
	 */
	String date;

	String time;

	String schedule;

	int id;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
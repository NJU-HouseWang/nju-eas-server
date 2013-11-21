package NJU.HouseWang.nju_eas_server.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

public class MysqlOperatorPool {

	private static int DEFAULT_CONNECT_NUM = 10;

	private static int MIN_CONNECT_NUM = 5;

	private int currentNum = 0;

	private Stack<MysqlOperator> pool = new Stack<MysqlOperator>();

	public void start() {
		initConnection();
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (currentNum < MIN_CONNECT_NUM) {
						initConnection();
					}
				}
			}
		});
		t.start();
	}

	private void initConnection() {
		for (int i = 0; i < DEFAULT_CONNECT_NUM; i++) {
			MysqlOperator sql = new MysqlOperator();
			try {
				sql.createConnection();
				currentNum++;
			} catch (Exception e) {
				e.printStackTrace();
			}
			pool.push(sql);
		}
	}

	public MysqlOperator getMysqlOperator() {
		currentNum--;
		return pool.pop();
	}

	public static void main(String[] args) {
		MysqlOperatorPool mop = new MysqlOperatorPool();
		mop.start();
		MysqlOperator mo = mop.getMysqlOperator();
		String sql = "select * from login_list";
		try {
			ResultSet rs = mo.operateSqlCommand(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2)
						+ "\t" + rs.getString(3));
			}
			mo.shutdownConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

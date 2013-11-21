package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlOperator {

	private final String driver = "com.mysql.jdbc.Driver";

	private final String url = "jdbc:mysql://localhost:3306/nju_eas";

	private final String user = "root";

	private final String password = "cf024680";

	private Connection conn = null;

	private Statement statement = null;

	private ResultSet rs = null;

	public void createConnection() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
	}

	public ResultSet operateSqlCommand(String sql) throws SQLException {
		if (!conn.isClosed()) {
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			return rs;
		}
		return null;
	}

	public void shutdownConnection() throws SQLException {

		if (rs != null) {
			rs.close();
		}

		if (conn != null) {
			conn.close();
		}
	}
}

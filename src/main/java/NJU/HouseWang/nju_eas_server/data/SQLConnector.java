package NJU.HouseWang.nju_eas_server.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {

	private final String driver = "com.mysql.jdbc.Driver";

	private final String url = "jdbc:mysql://localhost:3306/nju_eas";

	private final String user = "root";

	private final String password = "cf024680";

	private Connection conn = null;

	public void createConnection() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
	}

	public Connection getConnection() throws SQLException {
		if (!conn.isClosed()) {
			return conn;
		}
		return null;
	}

	public void shutdownConnection() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}

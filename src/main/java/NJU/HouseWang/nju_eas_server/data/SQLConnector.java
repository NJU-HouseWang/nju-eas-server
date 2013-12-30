package NJU.HouseWang.nju_eas_server.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {

	private final String driver = "com.mysql.jdbc.Driver";

	private final String url = "jdbc:mysql://localhost:3306/nju_eas";

	private String user = "root";

	private String password = "cf024680";

	private Connection conn = null;

	public SQLConnector() {
		readConf();
	}

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

	public void readConf() {
		try {
			File f = new File("./conf.ini");
			if (!f.exists()) {
				f.createNewFile();
				BufferedWriter bfw = new BufferedWriter(new FileWriter(f));
				bfw.write("user=");
				bfw.newLine();
				bfw.write("password=");
				bfw.flush();
				bfw.close();
			}
			BufferedReader bfr = new BufferedReader(
					new FileReader("./conf.ini"));
			String[] s1 = bfr.readLine().split("=");
			String[] s2 = bfr.readLine().split("=");
			if (s1.length == 2) {
				user = s1[1];
			}
			if (s2.length == 2) {
				password = s2[1];
			}
			bfr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

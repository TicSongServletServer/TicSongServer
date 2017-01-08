package module;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	//private Connection conn;
	public static final DBConnection dbConnection;
	static {
		dbConnection = new DBConnection();
	}
	public Connection getConn() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ticsongdb");
		
		return ds.getConnection();
	}
	public static DBConnection getInstance() {
		return dbConnection;
	}
	private DBConnection() {
		/*try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RECALLDB","recall","recallpwd");
			conn.setAutoCommit(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}*/
	}
	public static void main(String [] args) {
		DBConnection.getInstance();
	}
}
package Day1;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static String driver;
	private static String url;
	private static String userName;
	private static String password;
	private static int initialSize;
	private static int maxActive;
	private static BasicDataSource ds=null;
	static{
		try {
			Properties cfg=new Properties();
			InputStream inStream=DBUtilstest.class.getClassLoader().getResourceAsStream("db.properties");
			cfg.load(inStream);
			driver=cfg.getProperty("jdbc.driver");
			url=cfg.getProperty("jdbc.url");
			userName=cfg.getProperty("jdbc.userName");
			password=cfg.getProperty("jdbc.password");
			initialSize=Integer.parseInt(cfg.getProperty("initialSize"));
			maxActive=Integer.parseInt(cfg.getProperty("maxActive"));
			ds=new BasicDataSource();
			ds.setUrl(url);
			ds.setDriverClassName(driver);
			ds.setUsername(userName);
			ds.setPassword(password);
			ds.setInitialSize(initialSize);
			ds.setMaxActive(maxActive);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			
			Connection conn=ds.getConnection();
			return conn;
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.setAutoCommit(true);
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void rollback(Connection conn){
		if(conn!=null){
			try {
				conn.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

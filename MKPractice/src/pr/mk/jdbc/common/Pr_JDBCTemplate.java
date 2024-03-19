package pr.mk.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Pr_JDBCTemplate {

	//1. static을 사용하여 connection 객체를 생성해주기
	
	private static Connection conn =null;
	
	// Db 연결정보를 담고 있는 connection 객체 생성 및 반환하는 
	// getConnection() 메서드 만들기
	
	private static Connection getConnection() {
		//1. 예외가 발생할 것에 대해 미리 try~catch문으로 예외 처리하기
		try {
			//2. 현재 conn 안에 객체가 없을 경우에는 새롭게 새 커넥션 객체를 생성하기
			if(conn == null || conn.isClosed()) {
				
				// 3. Properties 객체를 만들어 주기
				Properties prop = new Properties();
			
				// 4. Properties 객체를 통해 만들어 둔 .xml 파일을 불러오기
				prop.loadFromXML(new FileInputStream("pratice.xml"));
				
				// 5. XML에서 읽어온 값을 모두 변수에 저장하기 
				// 이곳에 작성하기 전에 practice.xml 파일에 연결할 내용에 대해서 미리 작성을 해두고
				// 이를 불러오면 된다.
				
				String driver = prop.getProperty("driver");
				String url =prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				
				// 6. 커넥션을 생성해준다.
				Class.forName(driver);
				
				// 7. DriverManger를 이용하여 Connection 객체를 생성해준다.
				conn = DriverManager.getConnection(url,user,password);
				
				// 8. 현재 자동으로 커밋이 되는데 이를 방지하기 위해서 비활성화 시켜준다.
				conn.setAutoCommit(false);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//conn 을 반복해준다.
		return  conn; 
	}
	
	// Connection 객체 자원 반환 메서드 만들기 
	public static void close(Connection conn) {
		
		//1. 예외 발생 전 예외 발생을 위해 try~catch 만들기
	
		try {
			
			//2. conn에 객체가 없는 상태가 아니라면 닫아주기
			if(conn != null && conn.isClosed()) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Statement 객체 자원 반환 메서드 만들기
	public static void close(Statement stmt) {
		//위와 동일
		try {
			if(stmt != null && stmt.isClosed()) stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	//ResultSet 객체 자원 반환 메서드 만들기
		public static void close(ResultSet rs) {
			//위와 동일
			try {
				if(rs != null && rs.isClosed()) rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 트랜잭션 Commit 메서드
		public static void commit(Connection conn) {
			try {
				if(conn!=null && conn.isClosed())conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		 // 트랜잭션 Rollback 메서드
		public static void rollback(Connection conn) {
			try {
				if(conn!=null && conn.isClosed()) conn.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}

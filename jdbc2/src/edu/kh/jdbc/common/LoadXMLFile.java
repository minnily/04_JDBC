package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LoadXMLFile {
	public static void main(String[] args) {
		
		// XML 파일 읽어오기 (Properties, FikeInputStream)
		
		try {
			
			Properties prop = new Properties();
			
			//driver.xml 파일을 읽어오기 위한 InputStream 객체 생성
			FileInputStream fis = new FileInputStream("driver.xml");
			
			// 연결된 diver.xml 파일에 있는 내용을 모두 읽어와 
			// Properties 객체에 K:V 형식으로 저장
			prop.loadFromXML(fis);
			
			System.out.println(prop);
			
			// prop.getProperty("key") : key가 일치하는 속성(데이터 ==value)을 얻어옴
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password= prop.getProperty("password");
			
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,password);
			System.out.println(conn);
			
			/* 왜 XML 파일을 이용해서 DB 연결 정보를 읽어와야 할까?
			 * 
			 * 1. 코드 중복 제거
			 * 2. 별도 관리 용도
			 * 3. 재 컴파일을 진행하지 않기 위해서
			 * - 코드가 길수록 컴파일 소요 시간이 크다.
			 * -> 코드  수정으로 인한 컴파일 소요시간을 없앰.
			 * (파일의 내용을 읽어오는 코드만 작성해두면 Java 코드 수정 없이, 파일 내용만 수정하면 재컴파일은 수행되지 않는다)
			 * 4. XML 파일에 작성된 문자열 형태를 그대로 읽어오기 때문에 SQL 작성 시 좀 더 편리해진다.
			 * 
			 * */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

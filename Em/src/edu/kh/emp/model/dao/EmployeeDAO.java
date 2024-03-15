package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.model.vo.Employee;

public class EmployeeDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	private Properties prop;

	public EmployeeDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("query.xml"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int insert(Connection conn, Employee emp) throws SQLException{
		
		
		int result = 0;
		try {
			
			String sql = prop.getProperty("insert");
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, emp.getEmpId()); 
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDepartmentTitle());
			pstmt.setString(7, emp.getJobName());
			pstmt.setInt(8, emp.getSalary());
			
			
			result = pstmt.executeUpdate();
			
			
			
		} finally {
			
		}
		
		return result;
	
	}

	public int update(Connection conn, Employee emp) throws Exception{ 
		
		// 결과 저장용 변수
		int result = 0;
		
		try {
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, emp.getEmpId()); 
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDepartmentTitle());
			pstmt.setString(7, emp.getJobName());
			pstmt.setInt(8, emp.getSalary());
			
			result = pstmt.executeUpdate();
			
		}finally {
			
		}
		
		return result;
	}

	public int delete(Connection conn, int empId) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,empId);
			
			result = pstmt.executeUpdate(); // 결과값1/0
			
		} finally {

		}
		
		return result;
	}
}

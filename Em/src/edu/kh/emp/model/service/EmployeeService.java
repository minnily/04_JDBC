package edu.kh.emp.model.service;


import static edu.kh.emp.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.model.vo.Employee;


public class EmployeeService {

	private EmployeeDAO dao = new EmployeeDAO();

	public int insert(Employee emp) throws Exception{
		
		Connection conn = getConnection();
		
		int result = 0;
		
		result = dao.insert(conn, emp);
		
		if(result >0) {
			commit(conn); 
			result =1;
		}else{
			rollback(conn);
		}
		
		return result;
	}

	public int update(Employee emp) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.update(conn,emp);
		
		if(result >0) commit(conn);
		else         rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int delete(int empId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.delete(conn,empId);
		
		if(result > 0) commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		
		return result;
	}
	

	
	

}

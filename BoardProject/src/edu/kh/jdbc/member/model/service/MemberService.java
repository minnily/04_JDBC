package edu.kh.jdbc.member.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.close;
import static edu.kh.jdbc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	/**  회원 목록 조회 서비스
	 * @return memberList
	 */
	public List<Member> selectMemberList(){
		Connection conn = getConnection();
		 
		List<Member> memberList = new ArrayList<Member>();
		try {
			memberList = dao.selectMemberList(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			close(conn);
			
		}
		
		return memberList;
	}

	/** 회원 정보 수정 서비스
	 * @param memberName
	 * @param memberGender
	 * @param memberNo
	 * @return result
	 */
	public int updateMember(String memberName, String memberGender, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn,memberName,  memberGender,memberNo );
		
		// 트랜잭션 처리
		if(result > 0) commit(conn);
		else 		rollback(conn);
		
		close(conn);
		
		
		return result;
	}

	/** 비밀번호 변경 서비스
	 * @param current
	 * @param newPw1
	 * @param memberNo
	 * @return result
	 */
	public int updatePassword(String current, String newPw1, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result =dao.updatePassword(conn, current, newPw1, memberNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
}

package edu.kh.jdbc.member.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.close;
import static edu.kh.jdbc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	/** 숫자 6자리 보안코드 생성 서비스
	 * @return code
	 */
	public String createSecurityCode() {
		
		StringBuffer code = new StringBuffer(); 
		
		Random ran = new Random(); // 난수 생성 객체
		
		for(int i=0; i<6; i++) {
			int x = ran.nextInt(10);//0이상 10미만 정수
			code.append(x); // StringBuffer 마지막에 생성된 난수 x를 이어붙임
			
		
		}
		
		
		
		return code.toString();
	}

	/** 회원 탈퇴 서비스
	 * @param memberPw
	 * @param memberNo
	 * @return result
	 */
	public int unRegisterMember(String memberPw, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result=dao.unRegisterMember(conn,memberPw, memberNo);
		
		if(result > 0) commit(conn);
		else 		rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
}

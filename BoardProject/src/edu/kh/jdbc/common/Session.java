package edu.kh.jdbc.common;

import edu.kh.jdbc.member.model.dto.Member;

public class Session {

	// 로그인 : DB에 기록된 회원 정볼르 가지고 오는 것.
	//			-> 로그아웃을 할 때까지 프로그램에서 회원정보가 유지 되어야함.
	
	
	public static Member loginMember = null;
	
	//loginMember == null  ->  로그아웃 상태
	//loginMember != null  ->  로그인 상태
	
	
	
}

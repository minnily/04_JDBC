package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.board.view.BoardView;
import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.view.MemberView;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	private MainService service = new MainService();

	//회원 기능 화면 객체 생성
	private MemberView memberView = new MemberView();
	
	
	//게시판 기능 화면 객체 생성
	private BoardView boardView = new BoardView();
	
	/**
	 * 메인 메뉴 출력 View
	 * @throws Exception 
	 */
	public void mainMenu() {
		
		int input = 0; //메뉴 선택용 변수
		
		do {
			try {
				
				// 로그인 x
				if(Session.loginMember == null) {
				
				System.out.println("\u001B[32m"+"\n=====회원제 게시판 프로그램====\n"+"\u001B[30m");	
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("0. 프로그램 종료");
					
				System.out.println("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1 : login(); break;
				case 2 : signUp(); break;
				case 0 : System.out.println("\u001B[31m"+"\n====프로그램 종료====\n"+"\u001B[30m");
				default : System.out.println("\u001B[31m"+"\n===메뉴 번호만 입력해주세요===\n"+"\u001B[30m");
				}
				}else {
				//로그인 o	
					
					System.out.println("\u001B[34m"+"\n ==로그인 메뉴===\n"+"\u001B[30m");
					System.out.println("1.회원 기능");
					System.out.println("2.게시판 기능");
					System.out.println("3.로그아웃");
					System.out.println("0.프로그램 종료");
					
					
					
					System.out.println("\n메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					
					switch(input) {
					case 1 : memberView.memberMenu(); break;
					case 2 : boardView.boardMenu(); break;
					case 3 : System.out.println("\u001B[31m"+"\n====로그아웃 되었습니다.====\n"+"\u001B[30m");
							Session.loginMember = null; break;
							
					case 0: System.out.println("\u001B[31m"+"\n====프로그램 종료====\n"+"\u001B[30m");
					default : System.out.println("\u001B[31m"+"\n===메뉴 번호만 입력해주세요===\n"+"\u001B[30m");
					}
				}
				
			} catch (InputMismatchException e) {
				System.out.println("\u001B[31m"+"\n*** 입력 형식이 올바르지 않습니다 ***\n"+"\u001B[30m");
				sc.nextLine();//입력 버퍼에 잘못된 문자열 제거
				input = -1; //while문 종료 방지 
				
			}
			
		}while(input!=0);
		
		
	}

	/**
	 * 로그인
	 */
	public void login() {
		System.out.println("\n[로그인]\n");
		
		System.out.print("아이디 : ");
		String memberId =sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		try {
			// 로그인 서비스 호출 후 결과 반환 받기
			// -> 반환 받은 결과는 Session.loginMember에 저장
			Session.loginMember = service.login(memberId,memberPw);
			
			if(Session.loginMember == null) { //로그인 실패
				System.out.println("\u001B[31m"+"\n*** 아이디/비밀번호가 일치하지 않습니다 ***\n"+"\u001B[30m");
			}else {
				//로그인 성공
				System.out.printf("\n====%s님 환영합니다",memberId);
			}
			
		}catch (Exception e) {
			System.out.println("\u001B[31m"+"\n **** 로그인 중 예외 발생 **** \n"+"\u001B[30m");
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원가입
	 */
	public void signUp() {
		System.out.println("\n[회원가입]\n");

		// 아이디, 비밀번호, 비밀번호 확인, 이름, 성별(F/M)을 저장할 변수 선언
		String memberId = null;
		String memberPw = null;
		String pwConfirm = null; //비밀번호 확인용 변수
		String memberName = null;
		String memberGender = null;
		
		try {
			// 아이디 입력
			// - DB에 탈퇴하지 않은 회원 중 입력한 아이디와 같은 아이디가 존재하면 중복으로 판정
			// 그래서 중복이 입력되지 않을 때 까지 무한 반복
			
			while(true) {
				
				System.out.print("아이디 입력 : ");
				memberId = sc.next();
				
				// 아이디 중복 확인 서비스 호출
				// 중복인 경우 1, 아닌 경우 0 반환
				int result = service.idDuplicationCheck(memberId);
				
				// 중복 검사 결과에 따라 반복 제어
				if(result == 0) {
					System.out.println("\n 사용가능한 아이디 입니다 \n");
					break;//중복이 아니기에 While문 멈춰주기!
				}else {
					System.out.println("\u001B[31m"+"\n*** 이미 사용 중인 아이디 입니다 ***\n"+"\u001B[30m");
				}
	
			}
			
			// 비밀번호, 비밀번호 확인 입력을 받아서 같을 때까지 무한 반복
			while(true) {
				System.out.print("비밀번호 입력 : ");
				memberPw = sc.next();
				
				System.out.print("비밀번호 확인 : ");
				pwConfirm =sc.next();
				
				if(memberPw.equals(pwConfirm)) {
					System.out.println("\n ===비밀번호 일치=== \n");
					break;
				}else {
					System.out.println("\u001B[31m"+"\n *** 비밀번호가 일치하지 않습니다 ***"+"\u001B[30m");
				}
			}
			
			
			//이름 입력
			System.out.print("이름 : ");
			memberName = sc.next();
			
			// 성별 입력 
			//M/F가 입력될 때 까지 무한 반복
			while(true) {
				System.out.print("성별(M/F) : ");
				memberGender =sc.next().toUpperCase();
				
				//정상 입력
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break;
				}else {
					System.out.println("\u001B[31m"+"\n *** M 또는 F만 입력해주세요 *** \n"+"\u001B[30m");
				}
			}
			
			
			// Member 객체를 생성하여 입력 받은 값 세팅
			Member member = new Member();
			
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			member.setMemberName(memberName);
			member.setMemberGender(memberGender);
			
			//회원 가입 서비스 호출
			int result = service.signUp(member);
			
			if(result>0) {
				System.out.println("\n === 회원가입 성공 ===\n");
			}else {
				System.out.println("\n === 회원가입 실패 ===\n");
			}
			
			
			
		} catch (Exception e) {
			System.out.println("\u001B[31m"+"\n***회원 가입 중 예외 발생 ***\n"+"\u001B[30m");
			e.printStackTrace();
		
		}
		
	}
}

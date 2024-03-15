package edu.kh.emp.view;

import java.security.DomainCombiner;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.model.vo.Employee;

public class EmployeeView {
	 
	
	public EmployeeView() {

		
		
		
		/*String dp = null;
		
		Map<Integer, String> dpMap = new HashMap<Integer, String>();
		
		dpMap.put(1, "사장");
		dpMap.put(2, "부사장");
		dpMap.put(3, "팀장");
		dpMap.put(4, "대리");
		dpMap.put(5, "사원");*/
		
		
		
	}
	
	
	public void systemService() {
		
		Scanner sc = new Scanner(System.in);
		EmployeeService service = new EmployeeService();
		
		int serviceNum = 0;
		
		do {
			System.out.println("============================");
			System.out.println("MK_Company Employee System");
			
			System.out.println("1. 직원 추가");
			System.out.println("2. 직원 수정");
			System.out.println("3. 직원 삭제");
			System.out.println("0.  Service 종료");
			
			System.out.print("원하는 Service 번호 입력 >> ");
			try {
				serviceNum = sc.nextInt();
				
				switch(serviceNum) {
				case 1 : insert();
				case 2 : update();
				case 3 : delete();
				case 0 :
					System.out.println("=====================");
					System.out.println("Thank you ~~ Bye!");
				default : System.out.println("잘못된 번호를 입력하셨습니다.");
				}
				
				
			} catch (Exception e) {
				System.out.println("오류발생  >>>>>>>>>>>>>");
				e.printStackTrace();
				serviceNum = -1;
			}
				
			
		} while (serviceNum != 0);
	}
	public void insert() throws Exception{
	
		Scanner sc = new Scanner(System.in);
		EmployeeService service = new EmployeeService();
		
		try {
			
		System.out.println("==================================");
		System.out.println("--------[직원 추가 Service]-------");
		
		System.out.print("사원번호 입력 : ");
		int empId =sc.nextInt();
		
		System.out.print("직원명 입력 : ");
		String empName = sc.next();
		
		System.out.print("주민번호 입력 : ");
		String empNo = sc.next();
		
		System.out.print("이메일 입력 : ");
		String email = sc.next();
		
		System.out.print("전화번호 입력 : ");
		String phone = sc.next();
		
		System.out.print("부서명 입력 : ");
		String departmentTitle = sc.next();
		
		System.out.print("직급명 입력 : ");
		String jobName = sc.next();
		
		System.out.print("급여 입력 : ");
		int salary = sc.nextInt();
		
		
		
		//Employee e1 = new Employee(1, "이민경", "960118-2123456", "mk_1@compan.com", "010-1234-1234", "기획팀", "팀장", 8000000);
		// -부서명, 직급명, 급여는 map만들어서 넣고 싶음(이후 수정예정)
		
		Employee ep= new Employee(empId, empName, empNo , email, phone, departmentTitle, jobName, salary);
		
		int result = service.insert(ep);
		
			if(result > 0) {
				System.out.println("직원 명단에 추가되었습니다.");
			}else {
				System.out.println("명단 추가에 실패했습니다");
			}
			
		} catch (Exception e) {
			System.out.println("추가 중 예외가 발생했습니다.");
			e.printStackTrace();
		}
	
	}
	
	
	public void update() throws Exception{
		
		Scanner sc = new Scanner(System.in);
		EmployeeService service = new EmployeeService();
		
		
		try {
			
			System.out.println("==================================");
			System.out.println("--------[직원 정보 수정 Service]-------");
			
			System.out.print("사원번호 입력 : ");
			int empId =sc.nextInt();
			
			System.out.print("직원명 입력 : ");
			String empName = sc.next();
			
			System.out.print("주민번호 입력 : ");
			String empNo = sc.next();
			
			System.out.print("이메일 입력 : ");
			String email = sc.next();
			
			System.out.print("전화번호 입력 : ");
			String phone = sc.next();
			
			System.out.print("부서명 입력 : ");
			String departmentTitle = sc.next();
			
			System.out.print("직급명 입력 : ");
			String jobName = sc.next();
			
			System.out.print("급여 입력 : ");
			int salary = sc.nextInt();
			
			
			
			Employee emp= new Employee(empId, empName, empNo , email, phone, departmentTitle, jobName, salary);
			
			int result = service.update(emp);
			
				if(result > 0) {
					System.out.println(empName+" [ "+jobName+" ] 의 정보가 수정되었습니다.");
				}else {
					System.out.println("명단 수정에 실패했습니다");
				}
				
			} catch (Exception e) {
				System.out.println("수정 중 예외가 발생했습니다.");
				e.printStackTrace();
			}
	}
	public void delete() throws Exception{
		
		Scanner sc = new Scanner(System.in);
		EmployeeService service = new EmployeeService();
		
		try {
			
			System.out.println("==================================");
			System.out.println("--------[직원 삭제 Service]-------");
			
			System.out.print("사원번호 입력 : ");
			int empId =sc.nextInt();
			
			sc.nextLine();
			
			int result = service.delete(empId);
			
				if(result > 0) {
					System.out.println(" 의 정보가 전부 삭제되었습니다.");
				}else {
					System.out.println("직원의 정보를 삭제할 수 없습니다");
				}
				
			} catch (Exception e) {
				System.out.println("삭제 중 예외가 발생했습니다.");
				e.printStackTrace();
			}
	}
	
	
}

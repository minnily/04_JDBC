package pr.mk.jdbc.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class XMLFileCreate {
	
	//XML파일 생성해보기
	
	//1. 메인 메서드를 만든다.
	public static void main(String[] args) {
		
		
		//2. 예외가 발생할 수 있다는 것을 감안하고 먼저 try~catch문을 작성한다.
		
		try {
			//3. 입력한 이름을 바탕으로 파일을 생성할 것임. 따라서, 입력받을 scanner를 작성한다.
			Scanner sc = new Scanner(System.in);
			//4. xml 파일을 읽을 properties 객체가 필요함! 객체를 생성! 변수를 임의로 지정
			Properties prop = new Properties();
			
			//5. 입력할 print 구문 작성
			System.out.println("생성할 .xml 파일명 : ");
			String input = sc.next();
			
			// FileOutputStream?
			//주어진 File 객체가 가리키는 파일을 쓰기 위한 객체를 생성하는 것 
			//기존의 파일이 존재할 때는 그 내용을 지우고 새로운 파일을 생성.
			
			//6. FileOutputStream을 생성하기
			FileOutputStream fos = new FileOutputStream(input+".xml");
			//=> 여기서 .xml을 출력시 쓰지 않아도 자동으로 .xml파일로 생성될 수 있도록 넣어줬음
			// 만약 뒤에 .xml을 작성하지 않는다면 입력할 때 작성을 해야함.
			
			//7. Properties 객체를 이용해 xml 파일을 생성하기
			prop.storeToXML(fos, input);
			
			//8. print 구문으로 마지막에 파일 생성에 성공했다는 내용 띄워주기
			System.out.println(input+".xml 파일을 생성했습니다.");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

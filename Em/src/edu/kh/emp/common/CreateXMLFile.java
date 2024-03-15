package edu.kh.emp.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	
	public static void main(String[] args) {
		
		try {
			Scanner sc = new Scanner(System.in);
			
			
			Properties prop = new Properties();
			
			System.out.print("생성할 파일 이름 : ");
			String fileName =sc.next();
			
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			prop.storeToXML(fos, fileName);
			
			System.out.println("파일이 생성되었습니다");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

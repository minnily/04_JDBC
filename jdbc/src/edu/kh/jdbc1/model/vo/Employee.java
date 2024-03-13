
package edu.kh.jdbc1.model.vo;

public class Employee {
	
	private String empName;
	private String jobName;
	private long salary;
	private long annualIncome;
	
	private String hireDate;
	private char gender; // 남 M, 여자 F
	

	


	public String getHireDate() {
		return hireDate;
	}






	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}






	public char getGender() {
		return gender;
	}






	public void setGender(char gender) {
		this.gender = gender;
	}






	public String getEmpName() {
		return empName;
	}






	public void setEmpName(String empName) {
		this.empName = empName;
	}






	public String getJobName() {
		return jobName;
	}






	public void setJobName(String jobName) {
		this.jobName = jobName;
	}






	public long getSalary() {
		return salary;
	}






	public void setSalary(long salary) {
		this.salary = salary;
	}






	public long getAnnualIncome() {
		return annualIncome;
	}






	public void setAnnualIncome(long annualIncome) {
		this.annualIncome = annualIncome;
	}






	public Employee(String empName, String jobName, long salary, long annualIncome) {
		super();
		this.empName = empName;
		this.jobName = jobName;
		this.salary = salary;
		this.annualIncome = annualIncome;
	}






	public Employee() {}






	@Override
	public String toString() {
		return "empName :  " + empName + "/ jobName :  " + jobName + "/ salary : " + salary + "/ annualIncome : "
				+ annualIncome ;
	}
	
	
	
}

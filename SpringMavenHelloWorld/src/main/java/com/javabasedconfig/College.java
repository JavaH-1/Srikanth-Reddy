package com.javabasedconfig;

public class College {
	
	private String branch;
	private String stdName;
	private int stdId;
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public int getStdId() {
		return stdId;
	}
	public void setStdId(int stdId) {
		this.stdId = stdId;
	}
	
	public void stdData() {
		System.out.println("Branch Name:" + branch);
		System.out.println("Student Name:" + stdName);
		System.out.println("Student Id:" + stdId);
	}

}

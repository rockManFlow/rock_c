package com.kuark.tool.base.vo;

public class ControlPersonPojo {
	private Integer id;

	//姓名
	private String personName;

	//姓名全拼
	private String personPinyin;

	//部门
	private String department;



	public Integer getId() {
		 return id;
	}

	public void setId(Integer id) {
		 this.id=id;
	}

	public String getPersonName() {
		 return personName;
	}

	public void setPersonName(String personName) {
		 this.personName=personName;
	}

	public String getPersonPinyin() {
		 return personPinyin;
	}

	public void setPersonPinyin(String personPinyin) {
		 this.personPinyin=personPinyin;
	}

	public String getDepartment() {
		 return department;
	}

	public void setDepartment(String department) {
		 this.department=department;
	}

}
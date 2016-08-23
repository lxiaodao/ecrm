package com.billioncube.ecrm.company;

public class Company {
	
	private Integer id;
	private String code;
	private String name;
	private Integer pid;
	public Company(){
		
	}
	
	
	public Company(Integer id,String code, String name) {
		this.id=id;
		this.code = code;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	

}
